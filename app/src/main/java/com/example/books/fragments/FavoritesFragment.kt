package com.example.books.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.books.R
import com.example.books.adapters.BookAdapter
import com.example.books.databinding.FragmentFavoritesBinding
import com.example.books.databinding.FragmentHomeBinding
import com.example.books.listeners.BookListener
import com.example.books.viewmodels.FavoritesViewModel
import com.example.books.viewmodels.HomeViewModel
import kotlin.getValue

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val adapter: BookAdapter = BookAdapter()

    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        binding.recyclerviewFavorites.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewFavorites.adapter = adapter
        attachListener()

        setObservers()

        return binding.root
    }

    private fun setObservers() {
        viewModel.books.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.recyclerviewFavorites.visibility = View.GONE
                binding.textviewNoBooks.visibility = View.VISIBLE
                binding.imageviewNoBooks.visibility = View.VISIBLE
            } else {
                adapter.submitList(it)
                binding.recyclerviewFavorites.visibility = View.VISIBLE
                binding.textviewNoBooks.visibility = View.GONE
                binding.imageviewNoBooks.visibility = View.GONE
            }
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

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteBooks()
    }
}