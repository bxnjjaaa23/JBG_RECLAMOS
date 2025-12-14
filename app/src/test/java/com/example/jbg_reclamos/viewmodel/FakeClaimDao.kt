package com.example.jbg_reclamos.viewmodel

import com.example.jbg_reclamos.data.local.ClaimDao
import com.example.jbg_reclamos.data.local.ClaimEntity

class FakeClaimDao : ClaimDao {

    val items = mutableListOf<ClaimEntity>()

    override suspend fun insert(claim: ClaimEntity) {
        items.add(claim)
    }

    override suspend fun delete(claim: ClaimEntity) {
        items.remove(claim)
    }

    override suspend fun listByUser(email: String): List<ClaimEntity> {
        return items.filter { it.ownerEmail == email }
    }

    override suspend fun getAllClaims(): List<ClaimEntity> {
        return items
    }

    override suspend fun updateStatus(id: Long, status: String) {
        val index = items.indexOfFirst { it.id == id }
        if (index != -1) {
            items[index] = items[index].copy(status = status)
        }
    }
}
