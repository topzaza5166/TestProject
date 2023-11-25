package com.example.testproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val itemAdapter = ItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        init()
    }

    private fun init() {
        initRecyclerView()
        binding.btAdd.isEnabled = false
        binding.btAdd.setOnClickListener {
            itemAdapter.addText(binding.editText.text.toString())
            binding.editText.setText("")
        }
        binding.editText.addTextChangedListener {
            binding.btAdd.isEnabled = it.toString().isNotEmpty()
        }
        binding.btDelete.setOnClickListener {
            itemAdapter.deleteAll()
        }
    }

    private fun initRecyclerView() {
        val linearLayout = LinearLayoutManager(this)
        binding.recyclerView.apply {
            layoutManager = linearLayout
            adapter = itemAdapter
            addItemDecoration(DividerItemDecoration(context, linearLayout.orientation))
        }
    }
}