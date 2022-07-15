package com.example.medtech.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.medtech.R
import com.example.medtech.data.model.Checklist
import com.example.medtech.data.model.Faq
import com.example.medtech.databinding.FragmentChecklistBinding
import com.example.medtech.view.adapter.ChecklistAdapter
import com.example.medtech.view.adapter.HoursAdapter

class ChecklistFragment : Fragment() {

    private var _binding: FragmentChecklistBinding? = null
    private val binding
        get() = _binding!!

    private val commonChecklistAdapter by lazy { ChecklistAdapter() }
    private val babyChecklistAdapter by lazy { ChecklistAdapter() }
    private val diagnosisChecklistAdapter by lazy { ChecklistAdapter() }
    private val cureChecklistAdapter by lazy { ChecklistAdapter() }
    private val consultChecklistAdapter by lazy { ChecklistAdapter() }

    private val commonChecklist by lazy {
        mutableListOf(
            Checklist("Жалобы"),
            Checklist("Артериальное давление"),
            Checklist("Вес (при ИМТ менее 18,5)"),
            Checklist("Уровень гемоглобина"),
        )
    }
    private val babyChecklist by lazy {
        mutableListOf(
            Checklist("Положение плода "),
            Checklist("Продлежание плода "),
            Checklist("Сердцебиение плода"),
            Checklist("Шевеление плода"),
        )
    }
    private val diagnosisChecklist by lazy {
        mutableListOf(
            Checklist("Диагноз"),
            Checklist("Код по МКБ -10"),
            Checklist("Обследование (лабораторное и инструментальное)")
        )
    }
    private val cureChecklist by lazy {
        mutableListOf(
            Checklist("Лечение"),
            Checklist("Направлена в школу подготовки к родам")
        )
    }
    private val consultChecklist by lazy {
        mutableListOf(
            Checklist("Физиологические изменения во время беременности"),
            Checklist("Режим и гигиена"),
            Checklist("Тревожные признаки во время беременности", false, "Беспокоит бессонница"),
            Checklist("Заполнение плана родов"),
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checklist, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.commonRv.adapter = commonChecklistAdapter
        binding.babyRv.adapter = babyChecklistAdapter
        binding.diagnosisRv.adapter = diagnosisChecklistAdapter
        binding.cureRv.adapter = cureChecklistAdapter
        binding.consultRv.adapter = consultChecklistAdapter

        commonChecklistAdapter.setList(commonChecklist)
        babyChecklistAdapter.setList(babyChecklist)
        diagnosisChecklistAdapter.setList(diagnosisChecklist)
        cureChecklistAdapter.setList(cureChecklist)
        consultChecklistAdapter.setList(consultChecklist)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}