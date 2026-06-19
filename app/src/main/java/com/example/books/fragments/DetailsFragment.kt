package com.example.books.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.books.R
import com.example.books.viewmodels.DetailsViewModel
import com.example.books.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()
    private var bookId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        bookId = arguments?.getInt("bookId") ?: -1
        if (bookId != -1) {
            viewModel.getBookById(bookId)
        }

        setObservers()
        setListeners()

        return binding.root
    }

    private fun setObservers() {
        viewModel.book.observe(viewLifecycleOwner) { book ->
            if (book != null) {
                binding.textviewTitle.text = book.title
                binding.textviewAuthorValue.text = book.author
                binding.textviewGenreValue.text = book.genre
                binding.checkboxFavorite.isChecked = book.favorite

                setBadgeBackground(book.genre)
            }
        }

        viewModel.bookRemoval.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), getString(R.string.msg_removed_successfully), Toast.LENGTH_SHORT)
            }
        }
    }

    private fun setListeners() {
        binding.imageviewBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.buttonRemove.setOnClickListener {
            handleRemove()
        }

        binding.checkboxFavorite.setOnClickListener {
            handleFavorite()
        }
    }

    private fun handleRemove() {
       val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(getString(R.string.dialog_message_delete_item))
            .setPositiveButton("Sim"
            ) { dialog, which ->
                viewModel.deleteBook(bookId)
                parentFragmentManager.popBackStack()
            }
            .setNegativeButton("Não") { dialog, which ->
                dialog.dismiss()
            }
        builder.create().show()
    }

    private fun handleFavorite() {
        viewModel.favorite(bookId)
    }

    private fun setBadgeBackground(genre: String) {
        when (genre) {
            "Terror" -> {
                binding.textviewGenreValue.setBackgroundResource(R.drawable.rounded_label_red)
            }
            "Fantasia" -> {
                binding.textviewGenreValue.setBackgroundResource(R.drawable.rounded_label_fantasy)
            }
            else -> {
                binding.textviewGenreValue.setBackgroundResource(R.drawable.rounded_label_teal)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}