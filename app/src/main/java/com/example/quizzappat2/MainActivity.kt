package com.example.quizzappat2

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //window.setDecorFitsSystemWindows(false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        btn_start.setOnClickListener {
            if(et_name.text.toString().isEmpty()) {
                Toast.makeText(this,
                    "Please enter your name", Toast.LENGTH_SHORT).show()
            }else {
                name = et_name.text.toString()
                val intent = Intent(this, QuizQueActivity::class.java)
                intent.putExtra("user", name)
                startActivity(intent)
                finish()
            }
        }
    }
}