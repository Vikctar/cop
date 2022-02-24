package com.example.copia.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.copia.data.TransactionPayment
import kotlinx.coroutines.flow.Flow


@Dao
interface TransactionDao {
    @Query("SELECT * FROM transaction_payment")
    fun getTransactions(): Flow<List<TransactionPayment>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(transactionPayment: TransactionPayment)

    @Query("SELECT * FROM transaction_payment")
    fun allTransactions(): MutableList<TransactionPayment>
}