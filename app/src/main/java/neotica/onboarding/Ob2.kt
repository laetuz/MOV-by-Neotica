package neotica.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import neotica.movie.R
import neotica.movie.signin.login

class ob2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ob2)

        val btnob2 = findViewById<Button>(R.id.ob2_continue_btn)
        val btnskip = findViewById<Button>(R.id.ob2_skip_btn)

        btnob2.setOnClickListener {
            intent = Intent(this@ob2, ob3::class.java)
            startActivity(intent)
        }
        btnskip.setOnClickListener {
            var intent = Intent(this@ob2, login::class.java)
            startActivity(intent)
        }
    }
}
