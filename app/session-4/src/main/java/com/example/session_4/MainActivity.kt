package com.example.session_4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.session_4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TransactionsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val transactions = listOf(
            Transaction("Delivery fee", "July 7, 2022", 3000, true),
            Transaction("Delivery fee", "July 7, 2022", 2000, true),
            Transaction("Top up", "July 28, 2022", 10000, false),
            Transaction("Delivery fee", "July 25, 2022", 2000, true),
            Transaction("Top up", "July 25, 2022", 5000, false),
            Transaction("Delivery fee", "July 17, 2022", 4000, true)
        )
        adapter = TransactionsAdapter(transactions)
        binding.transactions.adapter = adapter
        binding.transactions.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.track.setOnClickListener {
            startActivity(Intent(this@MainActivity, TrackActivity::class.java))
            finish()
        }
    }
}