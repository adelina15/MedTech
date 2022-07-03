package com.example.medtech.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medtech.R
import com.example.medtech.data.Article
import com.example.medtech.data.Hour
import com.example.medtech.databinding.ArticleBinding
import com.example.medtech.databinding.HourBinding

class ArticlesAdapter: RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    private var list = listOf<Article>()
    fun setList(list: MutableList<Article>) {
        this.list = list
//        notifyDataSetChanged()
    }

    class ArticleViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = ArticleBinding.bind(item)
        fun bind(article: Article) = with(binding) {
            articleTitle.text = article.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}