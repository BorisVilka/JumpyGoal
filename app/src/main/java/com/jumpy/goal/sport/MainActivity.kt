package com.jumpy.goal.sport

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity(), DialogInterface.OnDismissListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        dialog!!.dismiss()
        val navController  = Navigation.findNavController(this,R.id.fragmentContainerView)
        navController.popBackStack()
    }
}