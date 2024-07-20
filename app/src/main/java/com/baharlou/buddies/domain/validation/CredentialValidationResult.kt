package com.baharlou.buddies.domain.validation

sealed class CredentialValidationResult {
    object InvalidEmail : CredentialValidationResult()
    object InvalidPassword : CredentialValidationResult()
}