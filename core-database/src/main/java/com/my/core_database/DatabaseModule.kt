package com.my.core_database

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun providePokedexDatabase(@ApplicationContext context: Context, typeResponseConverter: TypeResponseConverter): PokedexDatabase {
        return Room.databaseBuilder(context, PokedexDatabase::class.java, "pokedex_database")
            .addTypeConverter(typeResponseConverter)
            .build()
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    fun providePokemonDao(database: PokedexDatabase): PokemonDao {

        return database.pokemonDao()
    }

    @Provides
    fun providePokemonInfoDao(database: PokedexDatabase): PokemonInfoDao {

        return database.pokemonInfoDao()
    }
}