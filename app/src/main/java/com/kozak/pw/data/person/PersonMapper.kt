package com.kozak.pw.data.person

import com.kozak.pw.data.PwMapper
import com.kozak.pw.domain.person.Person
import kotlinx.datetime.LocalDateTime

class PersonMapper : PwMapper<PersonEntity, Person> {

    override fun mapEntityToItem(entity: PersonEntity): Person {
        return with(entity) {
            Person(
                id = id,
                birthDate = LocalDateTime.parse(birthDate),
                deathDate = deathDate?.let { LocalDateTime.parse(it) },
                firstName = firstName,
                isAlive = isAlive,
                isFavorite = isFavorite,
                lastName = lastName,
                sex = sex,
                strength = strength
            )
        }
    }

    override fun mapItemToEntity(item: Person): PersonEntity {
        return with(item) {
            PersonEntity(
                id = id,
                birthDate = birthDate.toString(),
                deathDate = deathDate?.toString(),
                firstName = firstName,
                isAlive = isAlive,
                isFavorite = isFavorite,
                lastName = lastName,
                sex = sex,
                strength = strength
            )
        }
    }
}