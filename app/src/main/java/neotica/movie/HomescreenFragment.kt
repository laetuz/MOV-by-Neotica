package neotica.movie

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log

//import android.content.Intent
import neotica.movie.homescreen.Detail

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.database.*
import neotica.model.movies
import neotica.movie.adapter.NowPlayingAdapter
import neotica.movie.utils.Preferences
import java.text.NumberFormat
import java.util.*
import neotica.movie.HomescreenFragment as HomescreenFragment1

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomescreenFragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomescreenFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var preferences: Preferences
    private lateinit var mDatabase: DatabaseReference

    private var dataList = ArrayList<movies>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }




    private fun currency(harga: Double, textView: TextView){
        val localID = Locale("in","ID")
        val formatRupiah= NumberFormat.getCurrencyInstance(localID)
        textView.setText(formatRupiah.format(harga as Double))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homescreen, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val home_name_tv = view.findViewById<TextView>(R.id.homescreen_name)
        val home_saldo_tv = view.findViewById<TextView>(R.id.homescreen_saldo)
        val rec_now_playing = view.findViewById<RecyclerView>(R.id.recNowPlaying)
        val rec_coming_soon = view.findViewById<RecyclerView>(R.id.recComingSoon)
        val homescreenProfile = view.findViewById<ImageView>(R.id.homescreen_profile)

        preferences = Preferences(activity!!.applicationContext)
        mDatabase= FirebaseDatabase.getInstance().getReference("movies")

        home_name_tv.setText(preferences.getValues("name"))
        if (!preferences.getValues("saldo").equals("")){
            currency(preferences.getValues("saldo")!!.toDouble(),home_saldo_tv)
        }

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(homescreenProfile)


        rec_now_playing.layoutManager =  LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rec_coming_soon.layoutManager = LinearLayoutManager(context)

        getData()

        rec_now_playing.adapter = NowPlayingAdapter(dataList){}
    }

    private fun getData() {

        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(datasnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in datasnapshot.children) {
                    val film = datasnapshot.getValue(movies::class.java!!)
                    dataList.add(film!!)
                }

                if (dataList.isNotEmpty()){
                    NowPlayingAdapter(dataList){
                        val intent = Intent(context,Detail::class.java).putExtra("data",it)
                        startActivity(intent)
                    }
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG,"Failed to Read value", databaseError.toException())
                Toast.makeText(context, ""+databaseError.message, Toast.LENGTH_SHORT).show()
            }

        })
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomescreenFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomescreenFragment1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}