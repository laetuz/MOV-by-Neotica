package neotica.movie.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*
import neotica.movie.R
import neotica.movie.homescreen.homescreen

class signup : AppCompatActivity() {

    private lateinit var sUsername:String
    private lateinit var sPassword:String
    private lateinit var sEmail:String
    private lateinit var sName:String

    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("User")

        val continue_btn = findViewById<Button>(R.id.signup_continue_btn)
        val back_btn = findViewById<Button>(R.id.signup_back_btn)

        val username_et = findViewById<EditText>(R.id.signup_username_et)
        val password_et = findViewById<EditText>(R.id.signup_password_et)
        val email_et = findViewById<EditText>(R.id.signup_email_et)
        val name_et = findViewById<EditText>(R.id.signup_name_et)

        back_btn.setOnClickListener {
            val loginpage = Intent(this@signup,login::class.java)
            startActivity(loginpage)
        }

        continue_btn.setOnClickListener {
            sUsername = username_et.text.toString()
            sPassword = password_et.text.toString()
            sEmail = email_et.text.toString()
            sName = name_et.text.toString()

            if (sUsername.equals("")){
                username_et.error = "Please insert username"
                username_et.requestFocus()
            } else if (sPassword.equals("")){
                password_et.error = "Please insert Password"
                password_et.requestFocus()
            } else if (sEmail.equals("")) {
                email_et.error = "Please insert Password"
                email_et.requestFocus()
            } else if (sName.equals("")) {
                name_et.error = "Please insert Password"
                name_et.requestFocus()
            } else {
                saveUsername (sUsername,sPassword,sEmail,sName)
                Toast.makeText(this, "Successfully Registered.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun saveUsername(sUsername: String, sPassword: String, sEmail: String, sName: String) {
        var user = User()
        user.username = sUsername
        user.password = sPassword
        user.email = sEmail
        user.name = sName

        if (sUsername !=null){
            checkingUsername(sUsername,user)
        }

    }

    private fun checkingUsername(sUsername: String, data: User) {
        mDatabaseReference.child(sUsername).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user==null){
                    mDatabaseReference.child(sUsername).setValue(data)
                    intent = Intent(this@signup,homescreen::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@signup,"Username already used",Toast.LENGTH_LONG)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@signup,"Database Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}