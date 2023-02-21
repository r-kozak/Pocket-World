package com.kozak.pw.data

/**
 * Abstract class for all PwAny Entities
 */
abstract class PwAnyEntity : BaseEntity() {
    abstract val health: Int
    abstract val mass: Long
    abstract val name: String
    abstract val sizeWidth: Int?
    abstract val sizeHeight: Int?
}