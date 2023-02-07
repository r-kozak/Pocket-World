package com.kozak.pw.data.person

import com.kozak.pw.domain.person.Person
import kotlinx.datetime.LocalDateTime

class PersonMapper {

    fun mapEntityToItem(personEntity: PersonEntity): Person {
        return with(personEntity) {
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

    fun mapItemToEntity(person: Person): PersonEntity {
        return with(person) {
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

    fun mapEntitiesListToItemsList(entities: List<PersonEntity>) =
        entities.map { mapEntityToItem(it) }
}