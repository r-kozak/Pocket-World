package com.kozak.pw.presentation.person

import androidx.recyclerview.widget.DiffUtil
import com.kozak.pw.domain.person.Person

class PersonDiffCallback : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean =
        oldItem == newItem
}