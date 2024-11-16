package com.huancahuari.andres.laboratoriocalificado03

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.huancahuari.andres.laboratoriocalificado03.databinding.ItemTeacherBinding
import com.bumptech.glide.Glide

class TeacherAdapter(
    private val context: Context,
    private var teacherList: List<TeacherResponse>
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    inner class TeacherViewHolder(private val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(teacher: TeacherResponse) {
            binding.tvName.text = "${teacher.name} ${teacher.last_name}"

            Glide.with(context)
                .load(teacher.image_url)
                .into(binding.ivTeacher)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        holder.bind(teacherList[position])
    }

    override fun getItemCount(): Int = teacherList.size

    fun updateTeachers(newTeachers: List<TeacherResponse>) {
        teacherList = newTeachers
        notifyDataSetChanged()
    }
}
