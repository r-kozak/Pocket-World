package com.kozak.pw.data.person

import com.kozak.pw.domain.person.PersonItem
import kotlinx.datetime.LocalDateTime

class PersonItemMapper {

    fun mapEntityToItem(personItemEntity: PersonItemEntity): PersonItem {
        return with(personItemEntity) {
            PersonItem(
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

    fun mapItemToEntity(personItem: PersonItem): PersonItemEntity {
        return with(personItem) {
            PersonItemEntity(
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

    fun mapEntitiesListToItemsList(entities: List<PersonItemEntity>) =
        entities.map { mapEntityToItem(it) }
}