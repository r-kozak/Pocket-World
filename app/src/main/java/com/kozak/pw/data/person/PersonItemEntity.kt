package com.kozak.pw.data.person

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kozak.pw.domain.person.PersonItem

@Entity(tableName = "person_items")
data class PersonItemEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val birthDate: String,
    val sex: PersonItem.Sex,
    var firstName: String,
    var lastName: String,
    var strength: Int,
    var isFavorite: Boolean,
    var isAlive: Boolean,
    var deathDate: String? = null
)