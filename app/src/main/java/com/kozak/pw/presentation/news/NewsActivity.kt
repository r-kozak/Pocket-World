package com.kozak.pw.presentation.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.kozak.pw.databinding.ActivityNewsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Displays list of all news
 */
class NewsActivity : AppCompatActivity() {

    companion object {
        fun startIntent(context: Context): Intent {
            return Intent(context, NewsActivity::class.java)
        }
    }

    private lateinit var binding: ActivityNewsBinding

    val viewModel: NewsViewModel by viewModel()

    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        viewModel.newsList.observe(this) { news ->
            newsListAdapter.submitList(news)
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvNews) {
            newsListAdapter = NewsListAdapter()
            adapter = newsListAdapter
        }
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
                val newsId = newsListAdapter.currentList[viewHolder.adapterPosition].id
                viewModel.readNews(newsId)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rvNews)
    }

    private fun setupClickListener() {
        newsListAdapter.onNewsClickListener = {
            // TODO - start OneNewsActivity
            // startActivity(PersonActivity.newEditPersonIntent(this, it.id))
            Toast.makeText(this, "Someday you will be able to read the news", Toast.LENGTH_SHORT)
                .show()
        }
    }
}