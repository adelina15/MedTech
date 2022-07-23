package com.example.medtech.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.medtech.R
import com.example.medtech.databinding.FragmentWeekDetailsBinding

class WeekDetailsFragment : Fragment() {

    private val args by navArgs<WeekDetailsFragmentArgs>()
    private var _binding: FragmentWeekDetailsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_week_details, container, false)
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
        with(binding){
            weekText.text = args.baby.content
            weekNumber.text = "${args.baby.week} неделя"
            Glide.with(requireContext()).load(args.baby.fruit_img).into(fruitImage)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}