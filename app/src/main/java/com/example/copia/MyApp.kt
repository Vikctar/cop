package com.example.copia

import android.app.Application
import com.example.copia.data.AppDatabase
import com.example.copia.data.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApp : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val appRepository by lazy {
        AppRepository(
            database.receiptDao(),
            database.transactionDao(),
            database.allocationDao()
        )
    }
}