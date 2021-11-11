package com.example.notebook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook.R
import com.example.notebook.databinding.RwNoteViewBinding
import com.example.notebook.db.dao.NoteListEntity


class NoteListAdapter(
    private var noteListEntityList: ArrayList<NoteListEntity>,
    private val listener: (NoteListEntity) -> Unit
) : RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RwNoteViewBinding>(
            layoutInflater,
            R.layout._rw_note_view,
            parent,
            false
        )
        return NoteListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        holder.bind(noteListEntityList[position])
    }

    override fun getItemCount(): Int = noteListEntityList.size


    inner class NoteListViewHolder(private val binding: RwNoteViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteListEntity: NoteListEntity) {
            binding.tvTitle.text = noteListEntity.noteTitle
            binding.tvDescription.text = noteListEntity.noteDescription

            binding.root.setOnClickListener {
                listener.invoke(noteListEntity)
            }
        }

    }
}