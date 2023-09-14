package com.my.core-database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.my.core-database.entity.PokemonEntity
import com.my.core-database.entity.PokemonInfoEntity

@Database(
    entities = [PokemonEntity::class, PokemonInfoEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(value = [TypeResponseConverter::class])
abstract class PokedexDatabase: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonInfoDao(): PokemonInfoDao
}