package com.baharlou.buddies.signup

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

@Composable
@Preview(showBackground = true)
fun SignUpScreenPreview() {
    SignUpScreen()
}


@Composable
fun SignUpScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ScreenTitle(R.string.create_an_account)


        Spacer(modifier = Modifier.height(10.dp))

        var email by remember { mutableStateOf("") }
        EmailField(
            value = email,
            OnValueChanged = { email = it }
        )

        Spacer(modifier = Modifier.height(10.dp))

        var password by remember { mutableStateOf("") }
        PasswordField(
            value = password,
            OnValueChanged = { password = it }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            modifier = Modifier.fillMaxWidth(.8f),
            onClick = { }) {
            Text(text = stringResource(id = R.string.signup))
        }

    }
}


@Composable
private fun EmailField(
    value: String,
    OnValueChanged: (String) -> Unit,
) {

    OutlinedTextField(
        value = value,
        label = {
            Text(text = stringResource(id = R.string.email))
        },
        onValueChange = OnValueChanged
    )
}

@Composable
private fun PasswordField(
    value: String,
    OnValueChanged: (String) -> Unit
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
        label = {
            Text(text = stringResource(id = R.string.password))
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
    OnToggle: () -> Unit
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