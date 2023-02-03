package com.kozak.pw.data.person

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kozak.pw.domain.planet.Animal

@Entity(tableName = "persons")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    val birthDate: String,
    val sex: Animal.Sex,
    var firstName: String,
    var lastName: String,
    var strength: Int,
    var isFavorite: Boolean,
    var isAlive: Boolean,
    var deathDate: String? = null
)