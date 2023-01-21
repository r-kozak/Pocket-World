package com.kozak.pw.presentation

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.kozak.pw.databinding.ItemPersonFavoriteBinding
import com.kozak.pw.databinding.ItemPersonNormalBinding
import com.kozak.pw.domain.person.PersonItem

class PersonsListViewHolder(val binding: ViewBinding) :
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