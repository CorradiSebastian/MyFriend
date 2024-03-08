package com.sebastiancorradi.myfriend.datasource.repository.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sebastiancorradi.myfriend.data.Cat

@Dao
interface CatDao {
    @Query("SELECT * FROM Cat ORDER BY internalId ASC LIMIT 10 OFFSET (:startFrom)")
    fun getAll(startFrom:Int): List<Cat>

    @Query("SELECT * FROM Cat WHERE id = (:catId) limit 1")
    fun getCatById(catId: String): Cat?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun createAll(objects: List<Cat>)


    @Delete
    fun delete(cat: Cat)

    @Query("DELETE FROM Cat")
    fun deleteAll()

}