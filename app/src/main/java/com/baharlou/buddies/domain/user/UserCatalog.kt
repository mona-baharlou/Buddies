package com.baharlou.buddies.domain.user

interface UserCatalog {

    fun createUser(
        email: String,
        password: String
    ) : User
}