package neotica.movie.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import neotica.model.movies
import neotica.model.plays
import neotica.movie.R
import java.util.ArrayList

class Detail : AppCompatActivity() {

    lateinit var mDatabase:DatabaseReference
    private var dataList = ArrayList<plays>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data= intent.getParcelableExtra<movies>("data")

        mDatabase =FirebaseDatabase.getInstance().getReference("movies")
            .child(data?.title.toString())
            .child("play")

        /*tv_kursi.text = data.title
        tv_genre.text = data.genre
        tv_desc.text = data.desc
        tv_rate.text = data.rated*/
    }
}