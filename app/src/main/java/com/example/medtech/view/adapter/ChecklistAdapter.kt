package com.example.medtech.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medtech.R
import com.example.medtech.data.model.Answer
import com.example.medtech.data.model.Checklist
import com.example.medtech.data.model.Faq
import com.example.medtech.data.model.Question
import com.example.medtech.databinding.ChecklistItemBinding
import com.example.medtech.databinding.QuestionsBinding
import com.example.medtech.utils.Delegates

class ChecklistAdapter(): RecyclerView.Adapter<ChecklistAdapter.ChecklistViewHolder>() {

    private var answerList = listOf<Answer>()
    fun setAnswerList(list: List<Answer>) {
        answerList = list
        notifyDataSetChanged()
    }

    private var questionList = listOf<Question>()
    fun setQuestionList(list: List<Question>) {
        questionList = list
        notifyDataSetChanged()
    }


    class ChecklistViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ChecklistItemBinding.bind(item)
        fun bind(question: Question, answer: Answer) = with(binding) {
            checklistQuestion.text = question.name
            badAnswer.text = answer.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChecklistViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.checklist_item, parent, false)
        return ChecklistViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChecklistViewHolder, position: Int) {
        holder.bind(questionList[position], answerList[position])
        val isOk: Boolean = answerList[position].is_ok
        with(holder.binding){
            if (!isOk){
                badAnswer.visibility = View.VISIBLE
                check.visibility = View.INVISIBLE
            }
        }
    }

    override fun getItemCount(): Int {
        return answerList.size
    }

}