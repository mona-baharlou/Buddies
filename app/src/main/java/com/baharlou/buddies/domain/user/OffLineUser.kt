package com.baharlou.buddies.domain.user

import com.baharlou.buddies.domain.exceptions.DuplicateAccountException

open class OffLineUser(
    private val usersAndPasswords: MutableMap<String, MutableList<User>> = mutableMapOf(),
) {
    fun createUser(
        email: String,
        password: String,
    ): User {

        checkAccountExists(email)
        val userId = createIdForAccounts(email)
        val user = User(userId, email)
        saveUser(password, user)
        return user
    }

    private fun saveUser(password: String, user: User) {
        usersAndPasswords.getOrPut(password, ::mutableListOf).add(user)
    }

    private fun createIdForAccounts(email: String): String {
        val userId = email.takeWhile { it != '@' } + "Id"
        return userId
    }

    private fun checkAccountExists(email: String) {
        if (usersAndPasswords.values
                .flatten()
                .any { it.username == email } //if Already have a user with the given email
        ) {
            throw DuplicateAccountException()
        }
    }
}