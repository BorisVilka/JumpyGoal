package com.jumpy.goal.sport

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.jumpy.goal.sport.databinding.FragmentStartBinding


class StartFragment : Fragment() {


    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(inflater,container,false)
        binding.favs.setOnClickListener {
            val navController  = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
            navController.navigate(R.id.scoreFragment)
        }
        binding.settings.setOnClickListener {
            val navController  = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
            navController.navigate(R.id.fragmentSettings)
        }
        binding.start.setOnClickListener {
            val navController  = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
            navController.navigate(R.id.gameFragment)
        }
        val set = requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE)
            .getStringSet("score",HashSet<String>())!!.map { it.toInt() }.toList()
        val max = if(set.isEmpty()) 0 else set.max()
        binding.textView.text = "Score: $max"
        return binding.root
    }


}