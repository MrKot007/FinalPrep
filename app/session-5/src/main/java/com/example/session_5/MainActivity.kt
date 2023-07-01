package com.example.session_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.session_5.databinding.ActivityMainBinding
import java.sql.Driver

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DriversAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val drivers = listOf(
            DriverItem("John Joshua", "R-456-223U", 4, "ava1.png"),
            DriverItem("Saviour Bill", "R-432-723K", 3, "ava2.png"),
            DriverItem("Chinoso James", "R-359-224G", 5, "ava3.png"),
            DriverItem("Raph Ron", "R-890-245N", 4, "ava4.png"),
            DriverItem("Joy Ezekiel", "R-434-221C", 4, "ava5,png"),
            DriverItem("Wonder Obi", "R-345-267V", 3, "ava6.png")
        )
        adapter = DriversAdapter(drivers, object : OnClickDriver{
            override fun onClick(driver: DriverItem) {
                val driverIntent = Intent(this@MainActivity, DriverActivity::class.java)
                driverIntent.putExtra("name", driver.name)
                driverIntent.putExtra("regNum", driver.id)
                startActivity(driverIntent)
            }
        })
        binding.dirvers.adapter = adapter
        binding.dirvers.layoutManager = LinearLayoutManager(this@MainActivity)
    }
}