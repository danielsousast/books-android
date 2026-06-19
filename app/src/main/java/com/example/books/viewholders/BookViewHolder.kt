package com.example.books.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.books.R
import com.example.books.databinding.ItemBookBinding
import com.example.books.entity.BookEntity
import com.example.books.listeners.BookListener

class BookViewHolder(val item: ItemBookBinding, private val listener: BookListener): RecyclerView.ViewHolder(item.root) {
    fun bind(book: BookEntity) {
        item.textviewTitle.text = book.title
        item.textviewAuthor.text = book.author
        item.textviewGenre.text = book.genre

        item.textviewTitle.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                listener.onClick(book.id)
            }

        })

        setBadgeBackground(book.genre)
        
        val favoriteIcon = if (book.favorite) {
            android.R.drawable.btn_star_big_on
        } else {
            android.R.drawable.btn_star_big_off
        }
        item.imageviewFavorite.setImageResource(favoriteIcon)
    }

    private fun setBadgeBackground(genre: String) {
        when (genre) {
            "Terror" -> {
                item.textviewGenre.setBackgroundResource(R.drawable.rounded_label_red)
            }
            "Fantasia" -> {
                item.textviewGenre.setBackgroundResource(R.drawable.rounded_label_fantasy)
            }
            else -> {
                item.textviewGenre.setBackgroundResource(R.drawable.rounded_label_teal)
            }
        }
    }
}