package com.example.copia.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_payment")
data class TransactionPayment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull
    val reference: String,
    @NonNull
    var amount: Int = 0
)
