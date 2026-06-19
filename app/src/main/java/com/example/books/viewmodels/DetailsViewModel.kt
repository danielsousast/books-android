package com.example.books.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.books.entity.BookEntity
import com.example.books.repository.BookRepository

class DetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val _book = MutableLiveData<BookEntity>()
    val book: LiveData<BookEntity> = _book
    private val repository = BookRepository.getInstance(application.applicationContext)

    private val _bookRemoval = MutableLiveData<Boolean>()
    val bookRemoval: LiveData<Boolean> = _bookRemoval

    fun getBookById(id: Int) {
        _book.value = repository.getBookById(id)
    }

    fun deleteBook(id: Int) {
       _bookRemoval.value = repository.deleteBook(id)
    }

    fun favorite(id: Int) {
        repository.toggleFavorite(id)
    }
}