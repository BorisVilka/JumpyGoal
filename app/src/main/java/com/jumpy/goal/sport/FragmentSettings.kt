package com.jumpy.goal.sport

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.jumpy.goal.sport.databinding.FragmentSettingsBinding


class FragmentSettings : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        binding.music.isChecked = requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).getBoolean("music",false)
        binding.sound.isChecked = requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).getBoolean("sound",false)
        binding.music.setOnCheckedChangeListener { _, b ->
            requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).edit()
                .putBoolean("music",b).apply()
        }
        binding.sound.setOnCheckedChangeListener { _, b ->
            requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).edit()
                .putBoolean("sound",b).apply()
        }
        binding.imageButton.setOnClickListener {
            val navController  = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
            navController.popBackStack()
        }
        return binding.root
    }


}