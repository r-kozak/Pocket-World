package com.kozak.pw.data.person

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kozak.pw.PwConstants
import com.kozak.pw.domain.person.PersonItem
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "person_items")
data class PersonItemEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val birthDate: LocalDateTime,
    val sex: PersonItem.Sex,
    var firstName: String = "",
    var lastName: String = "",
    var strength: Int = PwConstants.DEFAULT_PERSON_STRENGTH,
    var isFavorite: Boolean = false,
    var isAlive: Boolean = true,
    var deathDate: LocalDateTime? = null
)