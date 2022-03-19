package com.bithoven.android.shopping.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android.shopping.R
import com.bithoven.android.shopping.adapter.ShoppingListAdapter
import com.bithoven.android.shopping.contracts.OnDialougeListner
import com.bithoven.android.shopping.contracts.OnItemUpdated
import com.bithoven.android.shopping.database.ShoppingItem
import com.example.android.shopping.databinding.ActivityMainBinding
import com.bithoven.android.shopping.viewmodel.ShoppingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ShoppingViewModel by viewModels()
    private var binding: ActivityMainBinding? = null
    private val onItemUpdated = object : OnItemUpdated {
        override fun update(item: ShoppingItem) {
            viewModel.upsertItem(item)
        }
        override fun delete(item: ShoppingItem) {
            viewModel.deleteItem(item)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding!!.lifecycleOwner = this
        binding!!.dBindingViewModel = viewModel

        val adapter = ShoppingListAdapter(onItemUpdated)
        binding!!.recyclerHolder.adapter = adapter
        viewModel.getShoppingList().observe(this) {
            adapter.submitList(it)
            if (it.isNotEmpty()) {
                binding!!.floatButtonClearAll.visibility = View.VISIBLE
            } else {
                binding!!.floatButtonClearAll.visibility = View.GONE
            }


        }
        binding!!.floatButton.setOnClickListener {
            AddingDialog(this, object : OnDialougeListner {
                override fun onDialougeListner(item: ShoppingItem) {
                    viewModel.upsertItem(item)
                }
            }).show()
        }
        binding!!.floatButtonClearAll.setOnClickListener {

            val alertDialog = AlertDialog.Builder(this).apply {
                setMessage(getString(R.string.askToClear))
                setNegativeButton(getString(R.string.cancel), null)
                setPositiveButton(getString(R.string.clear)) { _, _ ->
                    viewModel.clear()
                }
                setCancelable(true)
                setCancelable(true)
            }.create()
            alertDialog.show()
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}