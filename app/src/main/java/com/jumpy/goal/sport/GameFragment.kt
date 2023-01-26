package com.jumpy.goal.sport

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.jumpy.goal.sport.databinding.FragmentGameBinding
import java.io.IOException


class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater,container,false)
        binding.game.setEndListener(object: GameView.Companion.EndListener {
            override fun end() {
                val dialog = EndDialog(binding.game.score)
                dialog.show(requireActivity().supportFragmentManager,"TAG")
             }
          }
        )
        try {
            // get input stream
            val ims = requireContext().assets.open("bg.png")
            // load image as Drawable
            val d = Drawable.createFromStream(ims, null)
            // set image to ImageView
            binding.imageView3.setImageDrawable(d)
            ims.close()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        binding.imageView3.setOnClickListener {
            binding.imageView3.visibility = View.GONE
            binding.game.start()
        }
        binding.imageButton2.setOnClickListener {
            binding.game.togglePause()
            val dialog = PauseDialog {
                binding.game.togglePause()
            }
            dialog.show(requireActivity().supportFragmentManager,"TAG1")
        }
        return binding.root
    }


}