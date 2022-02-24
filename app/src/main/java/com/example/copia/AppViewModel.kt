package com.example.copia

import androidx.lifecycle.*
import com.example.copia.data.AppRepository
import com.example.copia.data.PaymentAllocation
import com.example.copia.data.Receipt
import com.example.copia.data.TransactionPayment
import kotlinx.coroutines.launch

class AppViewModel(private val appRepository: AppRepository) : ViewModel() {
    val allReceipts: LiveData<List<Receipt>> = appRepository.allReceipts.asLiveData()

    val allTransactions: LiveData<List<TransactionPayment>> =
        appRepository.allTransactions.asLiveData()

    val allocations: LiveData<List<PaymentAllocation>> =
        appRepository.allocations.asLiveData()

    fun insertAllocation(allocation: PaymentAllocation) = viewModelScope.launch {
        appRepository.insertAllocation(allocation)
    }

    val transactions: MutableList<TransactionPayment> = appRepository.transactions
    val receipts: List<Receipt> = appRepository.receipts

}

class AppViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}