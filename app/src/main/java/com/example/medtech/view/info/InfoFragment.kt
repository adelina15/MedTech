package com.example.medtech.view.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.medtech.R
import com.example.medtech.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.toolbar) {
            inflateMenu(R.menu.info_menu)
            setNavigationIcon(R.drawable.ic_arrow)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
        binding.articles.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToArticlesFragment()
            findNavController().navigate(action)
        }
        binding.faq.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToFaqFragment()
            findNavController().navigate(action)
        }
        binding.weight.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToWeightFragment()
            findNavController().navigate(action)
        }
        binding.food.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToFoodFragment()
            findNavController().navigate(action)
        }
        binding.bag.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToBagFragment()
            findNavController().navigate(action)
        }
        binding.clothes.setOnClickListener {
            val action = InfoFragmentDirections.actionInfoFragmentToClothesFragment()
            findNavController().navigate(action)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}