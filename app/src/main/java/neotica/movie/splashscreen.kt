package neotica.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import neotica.onboarding.ob1

class splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        var handler = Handler()
        handler.postDelayed({
                            var intent = Intent(this@splashscreen,ob1::class.java)
            startActivity(intent)
            finish()
        },300)
    }
}