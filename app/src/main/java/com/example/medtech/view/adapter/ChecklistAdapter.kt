package com.example.medtech.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medtech.R
import com.example.medtech.data.model.Checklist
import com.example.medtech.data.model.Faq
import com.example.medtech.databinding.ChecklistItemBinding
import com.example.medtech.databinding.QuestionsBinding
import com.example.medtech.utils.Delegates

class ChecklistAdapter(): RecyclerView.Adapter<ChecklistAdapter.ChecklistViewHolder>() {

    private var list = listOf<Checklist>()
    fun setList(list: MutableList<Checklist>) {
        this.list = list
//        notifyDataSetChanged()
    }

    class ChecklistViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ChecklistItemBinding.bind(item)
        fun bind(checklist: Checklist) = with(binding) {
            checklistQuestion.text = checklist.question
            badAnswer.text = checklist.comment
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChecklistViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.checklist_item, parent, false)
        return ChecklistViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChecklistViewHolder, position: Int) {
        holder.bind(list[position])
        val isOk: Boolean = list[position].isOk
        with(holder.binding){
            if (!isOk){
                badAnswer.visibility = View.VISIBLE
                check.visibility = View.INVISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}