package com.example.lr1

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lr1.databinding.Task2ActivityBinding

class Task2Activity : AppCompatActivity() {

    private lateinit var binding: Task2ActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = Task2ActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val words = this.resources.getStringArray(R.array.word_list).apply { shuffle() }
        var index = 0
        var currentWord = words[index]
        var shuffeledWord = String(currentWord.toCharArray().apply { shuffle() })
        binding.t2TvWord.text = shuffeledWord

        binding.t2Input.setOnKeyListener { _, keyCode, keyEvent ->
            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)
            ) {
                if (binding.t2Input.text.toString() == currentWord) {
                    Toast.makeText(baseContext, "True!", Toast.LENGTH_SHORT).show()
                    if(index == words.size - 1) {
                        Toast.makeText(baseContext, "Gongratz, words ended!", Toast.LENGTH_SHORT).show()
                        binding.t2TvWord.text = ""

                        val intent = Intent(this, Task1Activity::class.java)
                        startActivity(intent)
                    } else {
                        index++
                        currentWord = words[index]
                        shuffeledWord = String(currentWord.toCharArray().apply { shuffle() })
                        binding.t2TvWord.text = shuffeledWord
                    }
                    binding.t2Input.text?.clear()
                } else {
                    Toast.makeText(baseContext, "False, try again!", Toast.LENGTH_SHORT).show()
                }
                true
            } else {
                false
            }
        }
    }
}
