package com.example.jbg_reclamos.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "claims")
data class ClaimEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val ownerEmail: String,
    val productName: String,
    val problemType: String,
    val description: String,
    val imageUri: String?,
    val address: String,
    val status: String,

    val createdAt: Long = System.currentTimeMillis()
)
