package com.example.finalprep

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
        val elements = listOf(
            Triple("firstHeading", "firstText", R.drawable.boarding1),
            Triple("secondHeading", "secondText", R.drawable.boarding2),
            Triple("thirdHeading","thirdText", R.drawable.boarding3)
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
            }else {
                val trio = controller.getElement()
                binding.heading.text = trio.first
                binding.paragraph.text = trio.second
                binding.image.setImageResource(trio.third)
            }
        }
        binding.signUp.setOnClickListener {
            controller.deleteElement()
            if (controller.getSize() == 0) {
                startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
                finish()
            }
        }
        binding.goToSignIn.setOnClickListener {
            controller.deleteElement()
            if (controller.getSize() == 0) {
                startActivity(Intent(this@MainActivity, EnteranceActivity::class.java))
                finish()
            }
        }
    }
}