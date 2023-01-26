package com.jumpy.goal.sport

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.navigation.Navigation
import com.jumpy.goal.sport.databinding.EndDialogBinding


class EndDialog(val score: Int):DialogFragment() {

    @SuppressLint("DialogFragmentCallbacksDetector")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(requireContext())
        var binding = EndDialogBinding.inflate(LayoutInflater.from(requireContext()))
        binding.textView5.text = "Score: $score"
        builder = builder.setView(binding.root)
        builder = builder.setOnDismissListener {
            val navController  = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
            navController.popBackStack()
        }
        var dialog = builder.create()
        dialog.setOnDismissListener {
            val navController  = Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
            navController.popBackStack()
        }
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        val set = requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE)
            .getStringSet("score",HashSet<String>())
        val set1 = HashSet<String>()
        set1.addAll(set!!)
        set1.add(score.toString())
        requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).edit()
            .putStringSet("score",set1).apply()
        return dialog
    }


    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        val activity: Activity? = activity
        if (activity is DialogInterface.OnDismissListener) {
            (activity as DialogInterface.OnDismissListener).onDismiss(dialog)
        }
    }
}