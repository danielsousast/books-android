package com.example.books.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.books.entity.BookEntity
import com.example.books.repository.BookRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val _books = MutableLiveData<List<BookEntity>>()
    private val repository = BookRepository.getInstance(application.applicationContext)
    val books: LiveData<List<BookEntity>> = repository.getAllBooks().asLiveData()

}