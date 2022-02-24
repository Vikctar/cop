package com.example.copia.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.copia.data.Receipt
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceiptDao {
    @Query("SELECT * FROM receipt")
    fun getReceipts(): Flow<List<Receipt>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(receipt: Receipt)

    @Query("SELECT * FROM receipt")
    fun allReceipts(): List<Receipt>
}