package com.example.books.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.books.entity.BookEntity
import com.example.books.repository.BookRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val _books = MutableLiveData<List<BookEntity>>()
    val books: LiveData<List<BookEntity>> = _books

    private val repository = BookRepository.getInstance(application.applicationContext)

    init {
        if (repository.getAllBooks().isEmpty()) {
            repository.loadInitialData()
        }
    }
    
    fun getAllBooks() {
       _books.value = repository.getAllBooks()
    }
}