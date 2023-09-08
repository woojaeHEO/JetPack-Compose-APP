package com.example.jetpack_compose_app.screen.main

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_app.base.BaseActivity
import com.example.jetpack_compose_app.di.CategoryItem
import com.example.jetpack_compose_app.screen.main.components.CategoryButton
import com.example.jetpack_compose_app.ui.theme.Gray2D2D2D
import com.example.jetpack_compose_app.ui.theme.GrayBCBCBC
import com.example.jetpack_compose_app.ui.theme.GrayF0F0F0

class ActMain : BaseActivity() {

    @Composable
    override fun BuildContent() {

        MainScreen(this)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(context: Context) {

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {

        val field = remember { mutableStateOf(TextFieldValue()) }

        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
            text = "What Pokemon are you looking for ?",
            textAlign = TextAlign.Start,
            color = Gray2D2D2D,
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            value = field.value,
            onValueChange = { field.value = it },
            placeholder = {
                Text(
                    text = "Search Pokemon",
                    color = GrayBCBCBC
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search Pokemon"
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {

                    // hide keyboard
                    focusManager.clearFocus()

                    // show toast
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
                }
            ),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = GrayF0F0F0,
                focusedTextColor = Gray2D2D2D,
                unfocusedTextColor = Gray2D2D2D,
                focusedLeadingIconColor = GrayBCBCBC,
                unfocusedLeadingIconColor = GrayBCBCBC,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            )
        )

        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            CategoryButton(
                modifier = Modifier.weight(1f),
                categoryState = CategoryItem.pokedex,
                onClick = {}
            )

            CategoryButton(
                modifier = Modifier.weight(1f),
                categoryState = CategoryItem.moves,
                onClick = {}
            )
        }

        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            CategoryButton(
                modifier = Modifier.weight(1f),
                categoryState = CategoryItem.evolutions,
                onClick = {}
            )

            CategoryButton(
                modifier = Modifier.weight(1f),
                categoryState = CategoryItem.locations,
                onClick = {}
            )
        }

        Text(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp, bottom = 8.dp),
            text = "Watch",
            color = Gray2D2D2D,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            color = GrayBCBCBC
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() { ActMain().BuildContent() }