package com.example.quizzappat2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result_acitivity.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_acitivity)

        val ans: Int = intent.getIntExtra("score", 0)

        val name: String = intent.getStringExtra("user").toString()

        tv_result_user.text = name
        tv_display_score.text = "Your score is $ans out of 10"

        btn_finish_result.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}