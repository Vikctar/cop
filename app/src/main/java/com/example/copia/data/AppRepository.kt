package com.example.copia.data

import androidx.annotation.WorkerThread
import com.example.copia.data.dao.AllocationDao
import com.example.copia.data.dao.ReceiptDao
import com.example.copia.data.dao.TransactionDao
import kotlinx.coroutines.flow.Flow

class AppRepository(
    private val receiptDao: ReceiptDao,
    private val transactionDao: TransactionDao,
    private val allocationDao: AllocationDao
) {
    val allReceipts: Flow<List<Receipt>> = receiptDao.getReceipts()
    val allTransactions: Flow<List<TransactionPayment>> = transactionDao.getTransactions()
    val allocations: Flow<List<PaymentAllocation>> = allocationDao.getAllocations()
    val transactions: MutableList<TransactionPayment> = transactionDao.allTransactions()
    val receipts: List<Receipt> = receiptDao.allReceipts()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertReceipt(receipt: Receipt) {
        receiptDao.insert(receipt)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertTransaction(transactionPayment: TransactionPayment) {
        transactionDao.insert(transactionPayment)
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAllocation(paymentAllocation: PaymentAllocation) {
        allocationDao.insert(paymentAllocation)
    }

}