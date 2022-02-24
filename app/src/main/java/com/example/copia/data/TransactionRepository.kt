package com.example.copia.data

import com.example.copia.data.dao.TransactionDao
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val transactionDao: TransactionDao) {
    val allTransactions: Flow<List<TransactionPayment>> = transactionDao.getTransactions()
}