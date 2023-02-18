package com.kozak.pw.presentation.person

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.kozak.pw.databinding.PersonFavoriteBinding
import com.kozak.pw.databinding.PersonNormalBinding
import com.kozak.pw.domain.person.Person
import com.kozak.pw.short

class PersonsListViewHolder(val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(person: Person) {
        if (binding is PersonFavoriteBinding) {
            with(binding) {
                tvName.text = person.name
                tvBirthDate.text = person.birthDate.short()
            }
        } else if (binding is PersonNormalBinding) {
            with(binding) {
                tvName.text = person.name
                tvBirthDate.text = person.birthDate.short()
            }
        }
    }
}