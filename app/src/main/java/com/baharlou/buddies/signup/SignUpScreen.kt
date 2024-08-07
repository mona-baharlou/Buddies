package com.baharlou.buddies.signup

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baharlou.buddies.R
import com.baharlou.buddies.signup.state.SignUpState

@Composable
@Preview(showBackground = true)
fun SignUpScreenPreview() {
}


@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel,
    onSignedUp: () -> Unit,
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isBadEmail by remember { mutableStateOf(false) }
    var isBadPassword by remember { mutableStateOf(false) }
    val signUpState by signUpViewModel.signUpState.observeAsState()

    if (signUpState is SignUpState.SignedUp) {
        onSignedUp()
    }

    Box(modifier = Modifier.fillMaxSize()) {

        if (signUpState is SignUpState.BadEmail) {
            isBadEmail = true
        }

        if (signUpState is SignUpState.BadPassword) {
            isBadPassword = true
        }

        SignUpForm(email, password, signUpViewModel, isBadEmail, isBadPassword, OnEmailChanged, OnPasswordChanged)

        if (signUpState is SignUpState.DuplicateAccount) {
            MessageSection(R.string.duplicateAccountError)
        } else if (signUpState is SignUpState.BackendError) {
            MessageSection(res = R.string.backendError)
        } else if (signUpState is SignUpState.OfflineError) {
            MessageSection(res = R.string.offlineError)
        }

    }
}

@Composable
private fun SignUpForm(
    email: State<String>,
    password: State<String>,
    signUpViewModel: SignUpViewModel,
    isBadEmail: Boolean,
    isBadPassword: Boolean,
) {
   /* var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
*/
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ScreenTitle(R.string.create_an_account)

        Spacer(modifier = Modifier.height(10.dp))

        EmailField(
            value = email.value,
            isError = isBadEmail,
            OnValueChanged = { email = it }
        )

        Spacer(modifier = Modifier.height(10.dp))

        PasswordField(
            value = password.value,
            isError = isBadPassword,
            OnValueChanged = { password = it }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            modifier = Modifier.fillMaxWidth(.8f),
            onClick = {
                signUpViewModel.createAccount(email, password)
            }) {
            Text(text = stringResource(id = R.string.signup))
        }

    }
}

@Composable
fun MessageSection(@StringRes res: Int) {
    Surface(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.error)
            .fillMaxWidth()
            .padding(20.dp),
    ) {
        Text(text = stringResource(id = res))
    }
}


@Composable
private fun EmailField(
    value: String,
    isError: Boolean,
    OnValueChanged: (String) -> Unit,
) {

    OutlinedTextField(
        value = value,
        isError = isError,
        label = {

            val resource = if (isError) R.string.badEmailError else R.string.email
            Text(text = stringResource(id = resource))
        },
        onValueChange = OnValueChanged
    )
}

@Composable
private fun PasswordField(
    value: String,
    isError: Boolean,
    OnValueChanged: (String) -> Unit,
) {
    var isVisible by remember {
        mutableStateOf(false)
    }

    val visualTransformation = if (isVisible)
        VisualTransformation.None
    else
        PasswordVisualTransformation()

    OutlinedTextField(
        value = value,
        isError = isError,
        label = {
            val resource = if (isError) R.string.badPasswrordError else R.string.password
            Text(text = stringResource(id = resource))
        },
        trailingIcon = {
            VisibilityToggle(isVisible) {
                isVisible = !isVisible
            }
        },
        visualTransformation = visualTransformation,
        onValueChange = OnValueChanged
    )
}

@Composable
private fun VisibilityToggle(
    isVisible: Boolean,
    OnToggle: () -> Unit,
) {
    val resource = if (isVisible) R.drawable.ic_invisible
    else R.drawable.ic_visible
    Image(
        modifier = Modifier.clickable {
            OnToggle.invoke()
        },
        painter = painterResource(id = resource),
        contentDescription = stringResource(R.string.toggle_visibility)
    )
}

@Composable
private fun ScreenTitle(@StringRes title: Int) {
    Text(
        text = stringResource(title),
        style = typography.headlineSmall,
    )
}