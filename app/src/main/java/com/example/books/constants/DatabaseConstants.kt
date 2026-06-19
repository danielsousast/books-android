package com.example.books.constants

class DatabaseConstants private constructor(){
    object BOOK {
        const val TABLE_NAME = "books"

    }

    object COLUMNS {
        const val ID = "id"
        const val TITLE = "title"
        const val AUTHOR = "author"
        const val GENRE = "genre"
        const val FAVORITE = "favorite"
    }
}