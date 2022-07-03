package com.example.medtech.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.medtech.R
import com.example.medtech.adapter.ArticlesAdapter
import com.example.medtech.data.Article
import com.example.medtech.databinding.FragmentArticlesBinding

class ArticlesFragment : Fragment() {

    private var _binding: FragmentArticlesBinding? = null
    private val binding
        get() = _binding!!

    private val articlesAdapter by lazy { ArticlesAdapter() }

    private val articlesList by lazy {
        mutableListOf(
            Article("Дневник беременности"),
            Article("Плановое  УЗИ"),
            Article("Профилактика растяжек"),
            Article("Токсикоз"),
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_articles, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_arrow)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
        binding.articlesRv.adapter = articlesAdapter
        articlesAdapter.setList(articlesList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}