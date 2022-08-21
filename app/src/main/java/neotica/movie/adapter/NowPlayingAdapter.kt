package neotica.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.target.Target

import neotica.model.movies
import neotica.movie.HomescreenFragment
import neotica.movie.R

class NowPlayingAdapter(private var data: List<movies>,
                        private val listener: (movies) -> Unit)
    : RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>(){

    lateinit var contextAdapter: Context




    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView : View = layoutInflater.inflate(R.layout.recview_nowplaying,parent,false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position],listener,contextAdapter, position)
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        private val recNowTitle:TextView = view.findViewById(R.id.rec_now_title)
        private val recNowPoster:ImageView = view.findViewById(R.id.rec_now_poster)

        fun bindItem(data:movies, listener: (movies) -> Unit, context: Context, position: Int){

            recNowTitle.text = data.title

            Glide.with(context)
                .load(data.poster)
               // .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .into(recNowPoster)


            itemView.setOnClickListener { listener(data) }



        }

    }

    override fun getItemCount(): Int = data.size
    }


