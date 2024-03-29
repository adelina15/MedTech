package com.example.medtech.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.medtech.R
import com.example.medtech.databinding.FragmentFoodBinding

class FoodFragment : Fragment() {

    private var _binding: FragmentFoodBinding? = null
    private val binding
        get() = _binding!!
    private val args by navArgs<FoodFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.toolbar) {
            setNavigationIcon(R.drawable.ic_arrow)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }
        binding.mainText.text = args.foodInfo
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}