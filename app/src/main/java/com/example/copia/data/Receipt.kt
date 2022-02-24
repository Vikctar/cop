package com.example.copia.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "receipt")
data class Receipt(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull
    val receiptRef: String,
    @NonNull
    val amount: Int = 0
)
