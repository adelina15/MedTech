package com.example.medtech.view.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.medtech.R
import com.example.medtech.data.UserPreferences
import com.example.medtech.data.model.Answer
import com.example.medtech.databinding.FragmentChecklistBinding
import com.example.medtech.view.adapter.ChecklistAdapter
import com.example.medtech.viewmodel.ChecklistViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChecklistFragment : Fragment() {

    private lateinit var binding: FragmentChecklistBinding

    private val sharedPreferences by inject<UserPreferences>()
    private val checklistViewModel by viewModel<ChecklistViewModel>()
    lateinit var answerList: List<Answer>


    private val commonChecklistAdapter by lazy { ChecklistAdapter() }
    private val babyChecklistAdapter by lazy { ChecklistAdapter() }
    private val diagnosisChecklistAdapter by lazy { ChecklistAdapter() }
//
//    private val commonChecklist by lazy {
//        mutableListOf(
//            Checklist("Жалобы"),
//            Checklist("Артериальное давление"),
//            Checklist("Вес (при ИМТ менее 18,5)"),
//            Checklist("Уровень гемоглобина"),
//        )
//    }
//    private val babyChecklist by lazy {
//        mutableListOf(
//            Checklist("Положение плода "),
//            Checklist("Продлежание плода "),
//            Checklist("Сердцебиение плода"),
//            Checklist("Шевеление плода"),
//        )
//    }
//    private val diagnosisChecklist by lazy {
//        mutableListOf(
//            Checklist("Диагноз"),
//            Checklist("Код по МКБ -10"),
//            Checklist("Обследование (лабораторное и инструментальное)")
//        )
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checklist, container, false)
        checklistViewModel.getChecklist(sharedPreferences.fetchUserId(), 1)
        binding.toolbarTitle.text = "Чеклист 1 месяца"
        setupObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.commonRv.adapter = commonChecklistAdapter
        binding.babyRv.adapter = babyChecklistAdapter
        binding.diagnosisRv.adapter = diagnosisChecklistAdapter

//        babyChecklistAdapter.setList(babyChecklist)
//        diagnosisChecklistAdapter.setList(diagnosisChecklist)

    }

    private fun setupObservers() {
        checklistViewModel.checklist.observe(requireActivity()) { checklist ->
            answerList = checklist[0].answer
            answerList = answerList.sortedBy { it.question }
            val subLists: List<List<Answer>> = answerList.chunked(4)
            Log.i("lists", "${subLists.size} $subLists")

            val questionList1 = checklist[0].template.title[0].question
            val questionList2 = checklist[0].template.title[1].question
            val questionList3 = checklist[0].template.title[2].question

            val answerList1 = subLists[0]
            val answerList2 = subLists[1]
            val answerList3 = subLists[2]

            commonChecklistAdapter.setAnswerList(answerList1)
            commonChecklistAdapter.setQuestionList(questionList1)

            babyChecklistAdapter.setAnswerList(answerList2)
            babyChecklistAdapter.setQuestionList(questionList2)

            diagnosisChecklistAdapter.setAnswerList(answerList3)
            diagnosisChecklistAdapter.setQuestionList(questionList3)
            hideProgressBar()
        }
        checklistViewModel.errorMessage.observe(requireActivity()) {
            Toast.makeText(context, "Что-то пошло не так", Toast.LENGTH_SHORT).show()
        }
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}