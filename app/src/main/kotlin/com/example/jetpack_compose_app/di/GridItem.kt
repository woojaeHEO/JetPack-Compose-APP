package com.example.jetpack_compose_app.di

import com.example.jetpack_compose_app.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

data class GridItem(val imageResId: Int)

interface GridRepository {
    fun getGridItems(): List<GridItem>
}

class GridRepositoryImpl : GridRepository {
    override fun getGridItems(): List<GridItem> {
        return listOf(
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher),
            GridItem(R.drawable.ic_launcher)
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
object GridModule {

    @Provides
    fun provideGridRepository(): GridRepository {
        return GridRepositoryImpl()
    }
}