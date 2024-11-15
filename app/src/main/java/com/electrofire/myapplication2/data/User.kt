package com.electrofire.myapplication2.data

import java.io.Serializable

data class User (
    val id: Long,
    val nickName: String,
    val password: String,
    val name: String,
    val surname: String,
    var money: Double,
    val createdDate: String
) : Serializable

