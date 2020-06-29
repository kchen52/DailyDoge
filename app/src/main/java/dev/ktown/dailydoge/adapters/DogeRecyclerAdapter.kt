package dev.ktown.dailydoge.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.ktown.dailydoge.R
import dev.ktown.dailydoge.models.Doge
import kotlinx.android.synthetic.main.layout_doge_listitem.view.*

class DogeRecyclerAdapter(var doges: MutableList<Doge>?):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun addDoge(newDoge: Doge) {
        doges?.add(newDoge)
    }

    fun addDoges(newDoges: List<Doge>) {
        doges?.addAll(newDoges)
    }
    /*fun updateDoges(newDoges: MutableList<Doge>) {
        doges = newDoges as
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DogeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_doge_listitem, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is DogeViewHolder -> {
                holder.bind(doges?.get(position) ?: return)
            }
        }
    }

    override fun getItemCount(): Int {
        return doges?.size ?: 0
    }

    class DogeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val dogeImage = itemView.dogeImage

        fun bind(doge: Doge) {
            val glideRequestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(glideRequestOptions)
                .load(doge.imageUrl)
                .into(dogeImage)
        }
    }
}