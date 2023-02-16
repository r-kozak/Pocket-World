package com.kozak.pw.data

/**
 * Base Entity for all Pw Entities
 * @link https://medium.com/@berryhuang/android-room-generic-dao-27cfc21a4912
 */
abstract class BaseEntity {

    abstract val id: Long
    abstract val createdAt: String
}