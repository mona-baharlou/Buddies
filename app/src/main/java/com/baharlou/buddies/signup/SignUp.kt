package com.baharlou.buddies.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.baharlou.buddies.R

@Composable
@Preview(showBackground = true)
fun SignUpPreview() {
    SignUp()
}


@Composable
fun SignUp() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(R.string.create_an_account),
            style = typography.headlineSmall,
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = "",
            label = {
                Text(text = stringResource(id = R.string.email))
            },
            onValueChange = {}
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
                        value = "",
            label = {
                Text(text = stringResource(id = R.string.password))
            },
            onValueChange = {}
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            modifier = Modifier.fillMaxWidth(.8f),
            onClick = { }) {
            Text(text = stringResource(id = R.string.signup))
        }

    }
}