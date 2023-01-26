package com.jumpy.goal.sport

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jumpy.goal.sport.databinding.ScoreItemBinding

class MyAdapter(val ctx: Context): RecyclerView.Adapter<MyAdapter.Companion.MyHolder>() {

    var data = mutableListOf<Int>()

    init {
        data = ctx.getSharedPreferences("prefs",Context.MODE_PRIVATE).getStringSet("score",HashSet<String>())!!.map {
                it.toInt()
            }.toList().sortedBy { -it }.toMutableList()
    }

    companion object {
        class MyHolder(val binding: ScoreItemBinding): RecyclerView.ViewHolder(binding.root) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(ScoreItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.binding.textView3.text = data[position].toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}