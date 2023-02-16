package com.kozak.pw.data

/**
 * Abstract class for all PwAny Entities
 */
abstract class PwAnyEntity : BaseEntity() {
    abstract var health: Int
    abstract var mass: Long
    abstract var name: String
}