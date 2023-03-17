package com.extra.edge.test.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.extra.edge.test.model.Rocket

@Dao
interface RocketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRocket(rockets: List<Rocket>)

    @Query("SELECT * FROM rocket")
    suspend fun getRockets(): List<Rocket>

    @Query("SELECT * FROM rocket WHERE id =:id")
    suspend fun getRocketDetail(id: String): Rocket
}