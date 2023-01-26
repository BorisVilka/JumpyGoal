package com.jumpy.goal.sport

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.Navigation
import com.jumpy.goal.sport.databinding.PauseDialogBinding

class PauseDialog(var list: ()->Unit): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var build = AlertDialog.Builder(requireContext())
        var binding = PauseDialogBinding.inflate(layoutInflater)
        binding.button.setOnClickListener {
            list()
            dismiss()
        }
        binding.button2.setOnClickListener {
            val navController  = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
            navController.popBackStack()
            dismiss()
        }
        build = build.setView(binding.root)
        var dialog = build.create()
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent);
        return dialog
    }
}