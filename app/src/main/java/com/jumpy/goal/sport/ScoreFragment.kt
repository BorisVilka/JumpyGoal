package com.jumpy.goal.sport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.jumpy.goal.sport.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {

    private lateinit var binding: FragmentScoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentScoreBinding.inflate(inflater,container,false)
        binding.score.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext()).apply {
            orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
        }
        binding.score.adapter = MyAdapter(requireContext())
        binding.imageButton.setOnClickListener {
            val navController  = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
            navController.popBackStack()
        }
        return binding.root
    }


}