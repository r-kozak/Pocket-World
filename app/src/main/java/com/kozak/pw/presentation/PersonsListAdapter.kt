package com.kozak.pw.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.kozak.pw.databinding.ItemPersonFavoriteBinding
import com.kozak.pw.databinding.ItemPersonNormalBinding
import com.kozak.pw.domain.person.PersonItem

class PersonsListAdapter : RecyclerView.Adapter<PersonsListAdapter.PersonsListViewHolder>() {
    companion object {
        const val VIEW_TYPE_NORMAL = 23
        const val VIEW_TYPE_FAVORITE = 32
        const val MAX_POOL_SIZE = 30
    }

    inner class PersonsListViewHolder(val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(personItem: PersonItem) {
            if (binding is ItemPersonFavoriteBinding) {
                with(binding) {
                    tvName.text = personItem.fullName()
                    tvBirthDate.text = personItem.shortBirthDate()
                }
            } else if (binding is ItemPersonNormalBinding) {
                with(binding) {
                    tvName.text = personItem.fullName()
                    tvBirthDate.text = personItem.shortBirthDate()
                }
            }
        }
    }

    var onPersonItemLongClickListener: ((PersonItem) -> Unit)? = null
    var onPersonItemClickListener: ((PersonItem) -> Unit)? = null

    var personsList = listOf<PersonItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonsListViewHolder {
        val binding = when (viewType) {
            VIEW_TYPE_NORMAL -> ItemPersonNormalBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )

            VIEW_TYPE_FAVORITE -> ItemPersonFavoriteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        return PersonsListViewHolder(binding)
    }

    override fun getItemCount()
            : Int = personsList.size

    override fun onBindViewHolder(holder: PersonsListViewHolder, position: Int) {
        with(holder) {
            with(personsList[position]) {
                binding.root.setOnLongClickListener {
                    onPersonItemLongClickListener?.invoke(this)
                    true
                }
                binding.root.setOnClickListener {
                    onPersonItemClickListener?.invoke(this)
                }
                bind(this)
            }
        }
    }

    override fun getItemViewType(position: Int) =
        if (personsList[position].isFavorite) VIEW_TYPE_FAVORITE else VIEW_TYPE_NORMAL
}