package com.example.medtech.view.info

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.medtech.R
import com.example.medtech.view.adapter.ArticlesAdapter
import com.example.medtech.data.model.Article
import com.example.medtech.databinding.FragmentArticlesBinding
import com.example.medtech.utils.Delegates
import com.example.medtech.viewmodel.ArticlesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticlesFragment : Fragment(), Delegates.ArticleClicked {

    private var _binding: FragmentArticlesBinding? = null
    private val binding
        get() = _binding!!

    private val articlesAdapter by lazy { ArticlesAdapter(this) }
    private val articlesViewModel by viewModel<ArticlesViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_articles, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articlesViewModel.getArticles()
        showProgressBar()
        setupObservers()
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_arrow)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
        binding.articlesRv.adapter = articlesAdapter
    }

    private fun setupObservers() {
        articlesViewModel.articles.observe(requireActivity()) {
            hideProgressBar()
            articlesAdapter.setList(it.asList())
        }
        articlesViewModel.errorMessage.observe(requireActivity()) {
            Log.i("articles", it)
            Toast.makeText(requireContext(), "Что-то пошло не так", Toast.LENGTH_SHORT).show()
        }
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(article: Article) {
        val action = ArticlesFragmentDirections.actionArticlesFragmentToArticleDetailsFragment(article)
        findNavController().navigate(action)
    }
}