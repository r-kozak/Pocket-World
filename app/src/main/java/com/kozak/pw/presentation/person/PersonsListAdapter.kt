package com.kozak.pw.presentation.person

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.kozak.pw.databinding.PersonFavoriteBinding
import com.kozak.pw.databinding.PersonNormalBinding
import com.kozak.pw.domain.person.Person

class PersonsListAdapter :
    ListAdapter<Person, PersonsListViewHolder>(PersonDiffCallback()) {

    companion object {
        const val VIEW_TYPE_NORMAL = 23
        const val VIEW_TYPE_FAVORITE = 32
        const val MAX_POOL_SIZE = 30
    }

    var onPersonLongClickListener: ((Person) -> Unit)? = null
    var onPersonClickListener: ((Person) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonsListViewHolder {
        val binding = when (viewType) {
            VIEW_TYPE_NORMAL -> PersonNormalBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )

            VIEW_TYPE_FAVORITE -> PersonFavoriteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        return PersonsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonsListViewHolder, position: Int) {
        with(holder) {
            with(getItem(position)) {
                binding.root.setOnLongClickListener {
                    onPersonLongClickListener?.invoke(this)
                    true
                }
                binding.root.setOnClickListener {
                    onPersonClickListener?.invoke(this)
                }
                bind(this)
            }
        }
    }

    override fun getItemViewType(position: Int) =
        if (getItem(position).isFavorite) VIEW_TYPE_FAVORITE else VIEW_TYPE_NORMAL
}