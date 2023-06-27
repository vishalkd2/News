package com.example.news

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private var newsList: List<Articles>,private val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val new = newsList[position]
        holder.title.text = new.title
        holder.description.text = new.description
        Glide.with(holder.itemView).load(new.urlToImage).into(holder.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, WebNewsActivity::class.java)
            intent.putExtra("news_url", new.url)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
    fun updateData(newNewsList: List<Articles>) {
        newsList = newNewsList
        notifyDataSetChanged()
    }

    class ViewHolder(Itemview: View) : RecyclerView.ViewHolder(Itemview) {
        val image = Itemview.findViewById<ImageView>(R.id.imageview)
        val title = Itemview.findViewById<TextView>(R.id.title)
        val description = Itemview.findViewById<TextView>(R.id.description)

    }
}