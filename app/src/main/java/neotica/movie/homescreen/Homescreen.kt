package neotica.movie.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import neotica.movie.R
import java.lang.reflect.Array
import java.util.ArrayList
import neotica.movie.utils.Preferences

class homescreen : AppCompatActivity() {

    lateinit var preference: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)

        preference = Preferences(this)
        preference.setValues("login","1")
        if (preference.getValues("homescreen").equals("1")) {
            finishAffinity()
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomnavbar)
        //val navController = findNavController(R.id.fragment)
        val navController = supportFragmentManager.findFragmentById(R.id.fragment)?.findNavController()
        navController?.let { bottomNavigationView.setupWithNavController(it) }
        //setupActionBarWithNavController(navController)



        //bottomNavigationView.setupWithNavController(navController)
    }
}