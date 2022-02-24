package com.example.copia.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.copia.data.dao.AllocationDao
import com.example.copia.data.dao.ReceiptDao
import com.example.copia.data.dao.TransactionDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [TransactionPayment::class, Receipt::class, PaymentAllocation::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun receiptDao(): ReceiptDao
    abstract fun allocationDao(): AllocationDao

    private class AppDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { appDatabase ->
                scope.launch {
                    val transactionDao = appDatabase.transactionDao()
                    val receiptDao = appDatabase.receiptDao()
                    val allocationDao = appDatabase.allocationDao()

                    allocationDao.deleteAll();

                    // add transactions and receipts
                    val transactionPayments = listOf(
                        TransactionPayment(reference = "MG001", amount = 100),
                        TransactionPayment(reference = "MG002", amount = 200),
                        TransactionPayment(reference = "MG003", amount = 300),
                        TransactionPayment(reference = "MG004", amount = 250)
                    )

                    val receipts = listOf(
                        Receipt(receiptRef = "R001", amount = 100),
                        Receipt(receiptRef = "R002", amount = 400),
                        Receipt(receiptRef = "R003", amount = 350)
                    )

                    transactionPayments.forEach { transactionDao.insert(it) }
                    receipts.forEach { receiptDao.insert(it) }

                }
            }

        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}