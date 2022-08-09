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
import com.example.medtech.view.adapter.FaqAdapter
import com.example.medtech.databinding.FragmentFaqBinding
import com.example.medtech.viewmodel.FaqViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FaqFragment : Fragment(){

    private var _binding: FragmentFaqBinding? = null
    private val binding
        get() = _binding!!

    private val faqAdapter by lazy { FaqAdapter() }
    private val faqViewModel by viewModel<FaqViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_faq, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        faqViewModel.getFaq()
        setupObservers()
        showProgressBar()
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_arrow)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
        binding.faqRv.adapter = faqAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() {
        faqViewModel.faq.observe(requireActivity()) {
            hideProgressBar()
            faqAdapter.setList(it.asList())
        }
        faqViewModel.errorMessage.observe(requireActivity()) {
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

}