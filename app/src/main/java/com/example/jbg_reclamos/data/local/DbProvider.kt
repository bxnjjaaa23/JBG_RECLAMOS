package com.example.jbg_reclamos.data.local

import android.content.Context
import androidx.room.Room

object DbProvider {
    @Volatile private var db: AppDatabase? = null

    fun get(context: Context): AppDatabase =
        db ?: synchronized(this) {
            db ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "jbg_reclamos_db"
            ).build().also { db = it }
        }
}
