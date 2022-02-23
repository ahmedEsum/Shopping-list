package com.bithoven.android.shopping.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.android.shopping.R
import com.bithoven.android.shopping.contracts.OnDialougeListner
import com.bithoven.android.shopping.database.ShoppingItem
import com.example.android.shopping.databinding.DialogBinding


class AddingDialog(context: Context, private val onDialogLister: OnDialougeListner) :
    AppCompatDialog(context) {
    private var binding: DialogBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogBinding.inflate(layoutInflater, null, false)
        setContentView(binding!!.root)
        binding!!.compelete.setOnClickListener {
            val name = binding!!.nameItem.text.toString()
            val amount = binding!!.amountItem.text.toString()
            if (name.isNotEmpty() && amount.isNotEmpty()) {
                val item = ShoppingItem(name, amount.toFloat())
                onDialogLister.onDialougeListner(item)
                dismiss()
                binding = null
            } else {
                Toast.makeText(context, context.getString(R.string.enterDataCorrectly), Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

        }
        binding!!.cancel.setOnClickListener {
            dismiss()

        }


    }

    override fun dismiss() {
        binding = null
        super.dismiss()
    }
}