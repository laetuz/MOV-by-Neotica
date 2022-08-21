package neotica.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import neotica.movie.R
import neotica.movie.utils.Preferences
import neotica.movie.signin.login

class ob1 : AppCompatActivity() {

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ob1)

    preferences= Preferences(this)
        if (preferences.getValues("ob1").equals("1")){
            finishAffinity()

            var signupintent = Intent(this@ob1, login::class.java)
            startActivity(signupintent)
        }

    val btnob1 = findViewById<Button>(R.id.ob1_continue_btn)
    val btnskip = findViewById<Button>(R.id.ob1_skip_btn)

        btnob1.setOnClickListener{
            var intent = Intent(this@ob1,ob2::class.java)
            startActivity(intent)
        }
        btnskip.setOnClickListener {
            preferences.setValues("ob1","1")
            finishAffinity()

            var signupintent = Intent(this@ob1, login::class.java)
            startActivity(signupintent)
        }
    }
}