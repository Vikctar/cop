package com.example.copia.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.copia.data.PaymentAllocation
import kotlinx.coroutines.flow.Flow

@Dao
interface AllocationDao {
    @Query("SELECT * FROM payment_allocation")
    fun getAllocations(): Flow<List<PaymentAllocation>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(paymentAllocation: PaymentAllocation)

    @Query("DELETE FROM payment_allocation")
    suspend fun deleteAll()

}