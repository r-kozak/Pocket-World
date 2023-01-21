package com.kozak.pw.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kozak.pw.R
import com.kozak.pw.domain.person.PersonItem
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class PersonsListAdapter: RecyclerView.Adapter<PersonsListAdapter.PersonsListViewHolder>() {
    companion object {
        const val VIEW_TYPE_NORMAL = 23
        const val VIEW_TYPE_FAVORITE = 32
        const val MAX_POOL_SIZE = 30
    }

    class PersonsListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        // TODO - replace with binder if it's possible
        val tvName: TextView = view.findViewById(R.id.tv_name)
        val tvBirthDate: TextView = view.findViewById(R.id.tv_birth_date)
    }

    var onPersonItemLongClickListener: ((PersonItem) -> Unit)? = null
    var onPersonItemClickListener: ((PersonItem) -> Unit)? = null

    var personsList = listOf<PersonItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonsListViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_NORMAL -> R.layout.item_person_normal
            VIEW_TYPE_FAVORITE -> R.layout.item_person_favorite
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return PersonsListViewHolder(view)
    }

    override fun getItemCount(): Int = personsList.size

    override fun onBindViewHolder(holder: PersonsListViewHolder, position: Int) {
        val personItem = personsList[position]
        holder.view.setOnLongClickListener {
            onPersonItemLongClickListener?.invoke(personItem)
            true
        }
        holder.view.setOnClickListener {
            onPersonItemClickListener?.invoke(personItem)
        }
        // set person name
        "${personItem.firstName} ${personItem.lastName}".also { holder.tvName.text = it }

        // set person birth date
        personItem.birthDate.toJavaLocalDateTime()
            .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))
            .also { holder.tvBirthDate.text = it }
    }

    override fun getItemViewType(position: Int): Int =
        if(personsList[position].isFavorite) VIEW_TYPE_FAVORITE else VIEW_TYPE_NORMAL
}