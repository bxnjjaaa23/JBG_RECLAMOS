package com.example.jbg_reclamos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ClaimEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun claimDao(): ClaimDao
}
