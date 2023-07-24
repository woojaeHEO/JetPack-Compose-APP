package com.example.jetpack_compose_app.screen.gallery

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpack_compose_app.base.BaseActivity
import com.example.jetpack_compose_app.di.GridItem
import com.example.jetpack_compose_app.ui.theme.JetPackComposeAPPTheme
import com.example.jetpack_compose_app.viewModel.GridViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActGrid : BaseActivity() {

    @Composable
    override fun BuildContent() {

        JetPackComposeAPPTheme {

            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "My Grid",
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    PhotoGrid(onItemClick = { gridItem ->
                        showToast("Clicked on item with ID ${gridItem.imageResId}")
                    })
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun GridItemComponent(gridItem: GridItem, onItemClick: () -> Unit) {

    Surface(
        color = Color.LightGray,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(4.dp)
    ) {

        Image(
            painter = painterResource(id = gridItem.imageResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clickable {
                    onItemClick()
                }
                .width(120.dp)
                .height(120.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

@Composable
fun PhotoGrid(onItemClick: (GridItem) -> Unit) {

    val viewModel: GridViewModel = viewModel()
    val gridItems = viewModel.getGridItems()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(gridItems) { gridItem ->
            GridItemComponent(gridItem = gridItem) {
                onItemClick(gridItem)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() { ActGrid() }