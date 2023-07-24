package com.example.jetpack_compose_app.base

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable

abstract class BaseActivity : AppCompatActivity() {

    @Composable
    abstract fun BuildContent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuildContent()
        }
    }
}