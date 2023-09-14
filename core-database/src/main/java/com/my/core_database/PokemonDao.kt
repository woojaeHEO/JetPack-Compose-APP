package com.my.core_database

import com.my.core_database.entity.PokemonEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonList(pokemonList: List<PokemonEntity>)

    @Query("SELECT * FROM PokemonEntity WHERE page = :page_")
    suspend fun getPokemonList(page_: Int): List<PokemonEntity>

    @Query("SELECT * FROM PokemonEntity WHERE page <= :page_")
    suspend fun getAllPokemonList(page_: Int): List<PokemonEntity>
}