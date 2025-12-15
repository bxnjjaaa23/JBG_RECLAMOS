package com.example.jbg_reclamos.viewmodel

import com.example.jbg_reclamos.data.local.ClaimDao
import com.example.jbg_reclamos.data.local.ClaimEntity

class FakeClaimDao : ClaimDao {

    private val data = mutableListOf<ClaimEntity>()
    private var nextId = 1L

    override suspend fun insert(claim: ClaimEntity) {
        val fixed = if (claim.id == 0L) claim.copy(id = nextId++) else claim
        data.add(fixed)
    }

    override suspend fun delete(claim: ClaimEntity) {
        data.removeAll { it.id == claim.id }
    }

    override suspend fun listByUser(email: String): List<ClaimEntity> {
        return data
            .filter { it.ownerEmail == email }
            .sortedByDescending { it.createdAt }
    }

    override suspend fun getAllClaims(): List<ClaimEntity> {
        return data.sortedByDescending { it.createdAt }
    }

    override suspend fun updateStatus(id: Long, status: String) {
        val i = data.indexOfFirst { it.id == id }
        if (i >= 0) data[i] = data[i].copy(status = status)
    }

    fun seed(vararg claims: ClaimEntity) {
        claims.forEach { insertBlocking(it) }
    }

    private fun insertBlocking(c: ClaimEntity) {
        val fixed = if (c.id == 0L) c.copy(id = nextId++) else c
        data.add(fixed)
    }
}
