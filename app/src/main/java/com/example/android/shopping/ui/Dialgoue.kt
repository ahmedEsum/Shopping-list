package com.example.android.shopping.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.android.shopping.contracts.OnDialougeListner
import com.example.android.shopping.database.ShoppingItem
import com.example.android.shopping.databinding.DialogBinding


class Dialgoue (context:Context ,val onDialougeListner: OnDialougeListner) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding : DialogBinding?= DialogBinding.inflate(layoutInflater)
        setContentView(binding?.root!!)
        binding.compelete.setOnClickListener {
            val name = binding?.nameItem?.text.toString()
            val amount = binding?.amountItem?.text.toString()
            if (name.isNotEmpty()&& amount.isNotEmpty() ){
                val item =ShoppingItem(name,amount.toInt())
                onDialougeListner.onDialougeListner(item)
                dismiss()
                binding=null
            }else {
                Toast.makeText(context, "please enter the data correctly", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

        }
        binding?.cancel?.setOnClickListener {
            dismiss()
            binding=null
        }

    }
}