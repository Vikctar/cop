package com.example.copia.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payment_allocation")
data class PaymentAllocation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull
    @ColumnInfo(name = "receipt_ref")
    val receiptRef: String,
    @NonNull
    @ColumnInfo(name = "transaction_ref")
    val transactionRef: String,
    @NonNull
    val amount: Int = 0,

)
