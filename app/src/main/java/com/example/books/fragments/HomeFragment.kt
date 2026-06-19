package com.example.books.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.books.R
import com.example.books.databinding.FragmentHomeBinding
import com.example.books.viewmodels.HomeViewModel
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.books.adapters.BookAdapter
import com.example.books.listeners.BookListener

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private val adapter: BookAdapter = BookAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.recyclerviewBooks.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewBooks.adapter = adapter
        attachListener()

        setObservers()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllBooks()
    }

    private fun setObservers() {
        viewModel.books.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    fun attachListener() {
        adapter.attachListener(object : BookListener {
            override fun onClick(id: Int) {
                val fragment = DetailsFragment()
                val bundle = Bundle()
                bundle.putInt("bookId", id)
                fragment.arguments = bundle

                parentFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}