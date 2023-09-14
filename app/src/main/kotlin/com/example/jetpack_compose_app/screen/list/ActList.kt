package com.example.jetpack_compose_app.screen.list

import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import com.example.jetpack_compose_app.base.BaseActivity
import com.example.jetpack_compose_app.ui.theme.JetPackComposeAPPTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActList : BaseActivity() {

    @Composable
    override fun BuildContent() {

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {

            JetPackComposeAPPTheme {

                PokemonListScreen()
            }
        }
    }
}