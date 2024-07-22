package com.baharlou.buddies.app

import com.baharlou.buddies.domain.user.OffLineUser
import com.baharlou.buddies.domain.user.UserRepository
import com.baharlou.buddies.domain.validation.RegexValidator
import com.baharlou.buddies.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {

    single { OffLineUser() }
    factory { RegexValidator() }
    factory { UserRepository(userCatalog = get()) }

    viewModel {
        SignUpViewModel(
            regexValidator = get(),
            userRepository = get()
        )
    }
}