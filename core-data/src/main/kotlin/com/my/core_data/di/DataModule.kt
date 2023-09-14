package com.my.core_data.di

import com.my.core_data.repository.ListRepository
import com.my.core_data.repository.ListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindsListRepository(
        listRepositoryImpl: ListRepositoryImpl
    ): ListRepository
}