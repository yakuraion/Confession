package pro.yakuraion.confession.data.persistence.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert
    suspend fun insert(item: T)

    @Insert
    suspend fun insert(items: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReplace(item: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReplace(items: List<T>)

    @Update
    suspend fun update(item: T)

    @Update
    suspend fun update(items: List<T>)

    @Delete
    suspend fun delete(item: T)

    @Delete
    suspend fun delete(items: List<T>)
}
