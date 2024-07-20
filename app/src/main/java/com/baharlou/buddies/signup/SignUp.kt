package com.baharlou.buddies.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.baharlou.buddies.R

@Composable
@Preview(showBackground = true)
fun SignUpPreview() {

}


@Composable
fun SignUp() {

    Column(modifier = Modifier.fillMaxSize()) {

        Text(text = "Create an account")

        OutlinedTextField(
            value = "",
            label = {
                Text(text = stringResource(id = R.string.email))
            },
            onValueChange = {}
        )

        OutlinedTextField(value = "",
            label = {
                Text(text = stringResource(id = R.string.password))
            },
            onValueChange = {}
        )

        Button(onClick = { }) {
            Text(text = stringResource(id = R.string.signup))
        }

    }
}