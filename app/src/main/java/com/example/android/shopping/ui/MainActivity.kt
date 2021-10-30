package com.example.android.shopping.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.android.shopping.R
import com.example.android.shopping.adapter.ShoppinngListAdapter
import com.example.android.shopping.contracts.OnDialougeListner
import com.example.android.shopping.contracts.OnItemUpdated
import com.example.android.shopping.database.ShoppingItem
import com.example.android.shopping.databinding.ActivityMainBinding
import com.example.android.shopping.viewmodel.ShoppingViewlModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),OnItemUpdated {

    val viewModel: ShoppingViewlModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.dBindingViewModel = viewModel

        val adapter=ShoppinngListAdapter(this)
        binding.recyclerHolder.adapter=adapter
        viewModel.getShoppingList().observe(this,{
            adapter.submitList(it)
        })
        binding.floatButton.setOnClickListener {
            Dialgoue(this, object : OnDialougeListner {
                override fun onDialougeListner(item: ShoppingItem) {
                    viewModel.upsertItem(item)
                }
            }).show()
        }
    }

    override fun update(item: ShoppingItem) {
        viewModel.upsertItem(item)
    }

    override fun delete(item: ShoppingItem) {
        viewModel.deleteItem(item)
    }
}