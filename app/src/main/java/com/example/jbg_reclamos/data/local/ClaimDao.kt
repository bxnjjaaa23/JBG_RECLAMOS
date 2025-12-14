package com.example.jbg_reclamos.data.local

import androidx.room.*

@Dao
interface ClaimDao {

    @Insert
    suspend fun insert(claim: ClaimEntity)

    @Delete
    suspend fun delete(claim: ClaimEntity)

    @Query("SELECT * FROM claims WHERE ownerEmail = :email ORDER BY createdAt DESC")
    suspend fun listByUser(email: String): List<ClaimEntity>

    @Query("SELECT * FROM claims ORDER BY createdAt DESC")
    suspend fun getAllClaims(): List<ClaimEntity>

    @Query("UPDATE claims SET status = :status WHERE id = :id")
    suspend fun updateStatus(id: Long, status: String)
}
