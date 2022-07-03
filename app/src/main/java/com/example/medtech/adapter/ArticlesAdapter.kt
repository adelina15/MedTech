package com.example.medtech.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medtech.data.Article
import com.example.medtech.data.Hour
import com.example.medtech.databinding.HourBinding

class ArticlesAdapter: RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    private var list = ArrayList<Article>()
    fun setList(list: ArrayList<Article>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ArticleViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = HourBinding.bind(item)
        fun bind(hours: Hour) = with(binding) {
            hour.text = hours.hour
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}