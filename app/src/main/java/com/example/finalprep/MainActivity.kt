package com.example.finalprep

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.finalprep.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val controller = Controller()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val shared = getSharedPreferences("main", Context.MODE_PRIVATE)
        if(shared.getBoolean("isFirstEnter", true)) {
            val elements = listOf(
                Triple("Quick Delivery At Your\nDoorstep", "Enjoy quick pick-up and delivery to\nyour destination", R.drawable.boarding1),
                Triple("Flexible Payment", "Different modes of payment either\nbefore and after delivery without stress", R.drawable.boarding2),
                Triple("Real-time Tracking","Track your packages/items from the\ncomfort of your home till final destination", R.drawable.boarding3)
            )
            for (i in elements) {
                controller.addElement(i)
            }
            val trio = controller.getElement()
            binding.heading.text = trio.first
            binding.paragraph.text = trio.second
            binding.image.setImageResource(trio.third)

            binding.next.setOnClickListener {
                controller.deleteElement()
                if (controller.getSize() == 1) {
                    binding.skip.visibility = View.GONE
                    binding.next.visibility = View.GONE
                    binding.signUp.visibility = View.VISIBLE
                    val trio = controller.getElement()
                    binding.heading.text = trio.first
                    binding.paragraph.text = trio.second
                    binding.image.setImageResource(trio.third)
                }else {
                    val trio = controller.getElement()
                    binding.heading.text = trio.first
                    binding.paragraph.text = trio.second
                    binding.image.setImageResource(trio.third)
                }
            }
            binding.signUp.setOnClickListener {
                saveNotFirstEnter()
                controller.deleteElement()
                if (controller.getSize() == 0) {
                    startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
                    finish()
                }
            }
            binding.goToSignIn.setOnClickListener {
                saveNotFirstEnter()
                controller.deleteElement()
                if (controller.getSize() == 0) {
                    startActivity(Intent(this@MainActivity, EnteranceActivity::class.java))
                    finish()
                }
            }
            binding.skip.setOnClickListener {
                saveNotFirstEnter()
                startActivity(Intent(this@MainActivity, EnteranceActivity::class.java))
            }
        }else {
            startActivity(Intent(this@MainActivity, EnteranceActivity::class.java))
        }
    }
    private fun saveNotFirstEnter() {
        val sh = getSharedPreferences("main", Context.MODE_PRIVATE)
        sh.edit().putBoolean("isFirstEnter", false).apply()
    }
}