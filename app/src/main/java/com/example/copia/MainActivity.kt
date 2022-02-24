package com.example.copia

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.copia.data.PaymentAllocation

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val appViewModel: AppViewModel by viewModels {
        AppViewModelFactory((application as MyApp).appRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val paymentTransactions = appViewModel.transactions
        val receipts = appViewModel.receipts



        var position = 0
        for (receipt in receipts) {
            val paymentTransaction = paymentTransactions[position]
            val remainder = receipt.amount - paymentTransaction.amount
            if (receipt.amount == paymentTransaction.amount) {
                appViewModel.insertAllocation(
                    PaymentAllocation(
                        receiptRef = receipt.receiptRef,
                        transactionRef = paymentTransaction.reference,
                        amount = paymentTransaction.amount
                    )
                )
                position += 1
            } else if (remainder > 0 && paymentTransaction.amount != 0) {
                position += 1
                appViewModel.insertAllocation(
                    PaymentAllocation(
                        receiptRef = receipt.receiptRef,
                        transactionRef = paymentTransaction.reference,
                        amount = paymentTransaction.amount
                    )
                )
                if (paymentTransactions.last() != paymentTransaction) {
                    appViewModel.insertAllocation(
                        PaymentAllocation(
                            receiptRef = receipt.receiptRef,
                            transactionRef = paymentTransactions[position].reference,
                            amount = remainder
                        )
                    )
                }

                if (position > paymentTransactions.lastIndex) {
                    break
                }
                appViewModel.insertAllocation(
                    PaymentAllocation(
                        receiptRef = receipts[position].receiptRef,
                        transactionRef = paymentTransactions[position].reference,
                        amount = paymentTransactions[position].amount - remainder
                    )
                )

                paymentTransactions[position].amount = 0
                position += 1
            }
        }

        appViewModel.allocations.observe(this) { allocationsLiveData ->
            allocationsLiveData.let { allocationsList ->
                allocationsList.forEach { Log.d(TAG, "Allocation: $it") }

            }

        }


    }
}