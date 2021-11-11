package com.example.notebook.main.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.notebook.MainActivity
import com.example.notebook.databinding.FragmentNoteEditBinding
import com.example.notebook.main.NoteFragment


class NoteEditFragment : Fragment() {

    private var _binding: FragmentNoteEditBinding? = null
    private val binding get() = _binding!!
    private val noteEditViewModel: NoteEditViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNoteEditBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this

        binding.ibtnBack.setOnClickListener {
            (context as MainActivity).changeFragment(NoteFragment(), null)
        }

        binding.apply {
            ibtnDone.setOnClickListener {
                noteEditViewModel.saveAndUpdateButton(
                    binding.title,
                    binding.description
                )
                (context as MainActivity).changeFragment(NoteFragment(), null)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val description = arguments?.getString("noteDescription") ?: ""
        val title = arguments?.getString("noteTitle") ?: ""
        val noteid = arguments?.getString("noteId") ?: ""

        binding.description = description
        binding.title = title

        noteEditViewModel.findById(noteid)
    }
}


