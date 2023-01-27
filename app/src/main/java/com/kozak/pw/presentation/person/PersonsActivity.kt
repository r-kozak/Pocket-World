package com.kozak.pw.presentation.person

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.kozak.pw.PwConstants.LOG_TAG
import com.kozak.pw.R
import com.kozak.pw.databinding.ActivityPersonsBinding

class PersonsActivity : AppCompatActivity(), PersonFragment.OnEditingFinishedListener {
    private lateinit var binding: ActivityPersonsBinding
    private lateinit var viewModel: PersonsViewModel
    private lateinit var personsListAdapter: PersonsListAdapter
    private var personContainer: FragmentContainerView? = null

    companion object {
        fun intentShowPersons(context: Context): Intent {
            return Intent(context, PersonsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        personContainer = binding.personContainer
        setupRecyclerView()
        viewModel = ViewModelProvider(this)[PersonsViewModel::class.java]
        viewModel.personItemsList.observe(this) { personItems ->
            // copy items because in PersonItemDiffCallback.areContentsTheSame() old and new items
            // will be the same
            // see: https://stackoverflow.com/questions/53156597/listadapter-with-diffutil-itemcallback-always-considers-objects-the-same
            personsListAdapter.submitList(personItems.map { it.copy() })
        }
    }

    private fun isNowPortraitMode() =
        resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    private fun launchPersonFragment(personId: Long) {
        binding.personContainer?.let {
            supportFragmentManager.popBackStack()
            supportFragmentManager.beginTransaction()
                .replace(it.id, PersonFragment.newInstance(personId))
                .addToBackStack(PersonFragment::class.java.toString())
                .commit()
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvPersons) {
            personsListAdapter = PersonsListAdapter()
            adapter = personsListAdapter
            recycledViewPool.setMaxRecycledViews(
                PersonsListAdapter.VIEW_TYPE_NORMAL,
                PersonsListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                PersonsListAdapter.VIEW_TYPE_FAVORITE,
                PersonsListAdapter.MAX_POOL_SIZE
            )
        }
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener()
    }

    private fun setupSwipeListener() {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                rv: RecyclerView, vh: RecyclerView.ViewHolder,
                t: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val personId = personsListAdapter.currentList[viewHolder.adapterPosition].id
                viewModel.killPerson(personId)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rvPersons)
    }

    private fun setupClickListener() {
        personsListAdapter.onPersonItemClickListener = {
            if (isNowPortraitMode()) {
                startActivity(PersonActivity.newEditPersonIntent(this, it.id))
            } else {
                launchPersonFragment(it.id)
            }
        }

        binding.buttonAddPerson.setOnClickListener {
            Log.d(LOG_TAG, "Creating new person.")
            viewModel.createNewPerson()
        }
    }

    private fun setupLongClickListener() {
        personsListAdapter.onPersonItemLongClickListener = {
            viewModel.togglePersonFavorite(it.id)
        }
    }

    override fun onEditingFinished() {
        Toast.makeText(this, R.string.person_saved_success, Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }
}