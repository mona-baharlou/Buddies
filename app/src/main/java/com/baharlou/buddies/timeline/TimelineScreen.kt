package com.baharlou.buddies.timeline

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.baharlou.buddies.R

@Preview(showBackground = true)
@Composable
fun TimelineScreenPreview() {
    TimelineScreen()
}


@Composable
fun TimelineScreen() {
    Text(text = stringResource(id = R.string.timeline))
}