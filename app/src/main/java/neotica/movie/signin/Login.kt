package neotica.movie.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import neotica.movie.R
import neotica.movie.homescreen.homescreen
import neotica.movie.utils.Preferences


class login : AppCompatActivity() {

    private lateinit var iUsername:String
    private lateinit var iPassword:String

    private lateinit var mDatabase : DatabaseReference
    lateinit var preference : Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login_btn = findViewById<Button>(R.id.login_btn)
        val register_btn = findViewById<Button>(R.id.register_btn)
        val username_et = findViewById<EditText>(R.id.login_username_et)
        val password_et = findViewById<EditText>(R.id.login_password_et)

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preference = Preferences(this)

        preference.setValues("ob1","1")
        if (preference.getValues("login").equals("1")){
            finishAffinity()

            val goHome = Intent(this@login,homescreen::class.java)
            startActivity(goHome)
        }

        login_btn.setOnClickListener {
            iUsername = username_et.text.toString()
            iPassword = password_et.text.toString()
            if (iUsername.equals("")) {
                username_et.error = "Please enter your username"
                username_et.requestFocus()
            } else if (iPassword.equals("")){
                password_et.error = "Please enter your password"
                password_et.requestFocus()
            } else {
                pushLogin(iUsername,iPassword)
            }
        }

        register_btn.setOnClickListener {
            intent = Intent(this@login, signup::class.java)
            startActivity(intent)
        }

    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                var user = snapshot.getValue(User::class.java)
                if (user == null){
                    Toast.makeText(this@login, "Username unknown",Toast.LENGTH_SHORT).show()
                } else {
                    if (user.password.equals(iPassword)) {

                        preference.setValues("Email",user.email.toString())
                        preference.setValues("Name",user.name.toString())
                        preference.setValues("Password",user.password.toString())
                        preference.setValues("url",user.url.toString())
                        preference.setValues("Username",user.username.toString())
                        preference.setValues("Saldo",user.saldo.toString())
                        preference.setValues("Status","1")

                        var intent = Intent(this@login, homescreen::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@login, "Unknown/incorrect password",Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@login, databaseError.message,Toast.LENGTH_SHORT).show()
            }
        })
    }
}