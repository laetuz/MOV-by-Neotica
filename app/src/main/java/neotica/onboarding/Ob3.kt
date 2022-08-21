package neotica.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import neotica.movie.R
import neotica.movie.signin.login

class ob3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ob3)

        val btnob3 = findViewById<Button>(R.id.ob3_continue_btn)

        btnob3.setOnClickListener {
            intent = Intent(this@ob3, login::class.java)
            startActivity(intent)
        }

    }
}