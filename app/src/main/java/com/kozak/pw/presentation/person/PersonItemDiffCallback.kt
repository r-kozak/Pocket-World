package com.kozak.pw.presentation.person

import androidx.recyclerview.widget.DiffUtil
import com.kozak.pw.domain.person.PersonItem

class PersonItemDiffCallback : DiffUtil.ItemCallback<PersonItem>() {
    override fun areItemsTheSame(oldItem: PersonItem, newItem: PersonItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PersonItem, newItem: PersonItem): Boolean =
        oldItem == newItem
}