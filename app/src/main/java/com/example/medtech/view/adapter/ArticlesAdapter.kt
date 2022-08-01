package com.example.medtech.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medtech.R
import com.example.medtech.data.model.Article
import com.example.medtech.databinding.ArticleBinding
import com.example.medtech.utils.Delegates

class ArticlesAdapter(val articleClicked: Delegates.ArticleClicked): RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    private var list = listOf<Article>()
    fun setList(list: List<Article>) {
        this.list = list
        notifyDataSetChanged()
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
        holder.binding.articleTitle.setOnClickListener {
            articleClicked.onItemClick(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}