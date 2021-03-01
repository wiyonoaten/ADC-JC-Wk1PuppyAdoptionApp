package com.wiyonoaten.composechallenge.wk1puppyadoptionapp.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.wiyonoaten.composechallenge.wk1puppyadoptionapp.R

@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = { Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "App Icon"
                ) },
                title = { Text("ComposePuppyAdopter") }
            )
        },
        floatingActionButton = floatingActionButton
    ) {
        content(it)
    }
}