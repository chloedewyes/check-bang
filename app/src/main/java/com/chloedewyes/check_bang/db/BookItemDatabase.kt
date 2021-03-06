package com.chloedewyes.check_bang.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chloedewyes.check_bang.models.Item

@Database(
    entities = [Item::class],
    version = 1
)

abstract class BookItemDatabase: RoomDatabase() {

    abstract fun getBookDao(): BookDao

    companion object {

        @Volatile
        private var instance: BookItemDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BookItemDatabase::class.java,
                "bookItem_db.db"
            ).build()
    }

}