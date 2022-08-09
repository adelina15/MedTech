package com.example.medtech.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medtech.R
import com.example.medtech.data.model.Faq
import com.example.medtech.databinding.QuestionsBinding
import com.example.medtech.utils.Delegates

class FaqAdapter(): RecyclerView.Adapter<FaqAdapter.FaqViewHolder>()  {

    private var list = listOf<Faq>()
    fun setList(list: List<Faq>) {
        this.list = list
        notifyDataSetChanged()
    }

    class FaqViewHolder(item: View): RecyclerView.ViewHolder(item)  {
        val binding = QuestionsBinding.bind(item)
        fun bind(faq: Faq) = with(binding) {
            question.text = faq.question
            answer.text = faq.answer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.questions, parent, false)
        return FaqAdapter.FaqViewHolder(view)
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        holder.bind(list[position])
        //logic to expand and close answers
        val isExpanded: Boolean = list[position].expanded
        with(holder.binding){
            if (isExpanded) {
                answer.visibility = View.VISIBLE
                toggleUp.visibility = View.VISIBLE
                toggle.visibility = View.INVISIBLE
            }
            else {
                answer.visibility = View.GONE
                toggle.visibility = View.VISIBLE
                toggleUp.visibility = View.INVISIBLE
            }
            layout.setOnClickListener {
                val items = list[position]
                items.expanded = !items.expanded
                notifyItemChanged(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}