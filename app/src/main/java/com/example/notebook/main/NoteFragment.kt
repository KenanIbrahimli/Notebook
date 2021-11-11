package com.example.notebook.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notebook.MainActivity
import com.example.notebook.adapter.NoteListAdapter
import com.example.notebook.databinding.FragmentNoteBinding
import com.example.notebook.db.dao.NoteDatabase
import com.example.notebook.db.dao.NoteListEntity
import com.example.notebook.db.repository.NoteRepository
import com.example.notebook.main.second.NoteEditFragment


class NoteFragment : Fragment(){

    private lateinit var binding: FragmentNoteBinding
    private lateinit var noteViewModel: NoteViewModel


    private var notelist: ArrayList<NoteListEntity> = arrayListOf()
    private val noteListAdapter: NoteListAdapter by lazy {
        NoteListAdapter(notelist) {
            val params = HashMap<String, String>()
            params["noteId"] = it.noteId.toString()
            params["noteTitle"] = it.noteTitle
            params["noteDescription"] = it.noteDescription

            (context as MainActivity).changeFragment(NoteEditFragment(), params)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this

        val dao = NoteDatabase.getInstrance(container!!.context).noteDao()
        val repostitory = NoteRepository(dao)
        val factory = NoteViewModelFactory(repostitory)

        noteViewModel = ViewModelProvider(this, factory).get(NoteViewModel::class.java)
        binding.myViewModel = noteViewModel


        binding.addIcon.setOnClickListener {
            (context as MainActivity).changeFragment(NoteEditFragment(), null)
        }

        displayNoteList()

        return binding.root
    }


    override fun onStop() {
        super.onStop()
        Log.d("TAG", "onStop")
        notelist.clear()
    }


    private fun displayNoteList() {
        noteViewModel.notes.observe(viewLifecycleOwner, { notes ->
            notes.forEach {
                notelist.add(it)
            }
            binding.rvData.apply {
                adapter = noteListAdapter
                layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            }
        })
    }
}