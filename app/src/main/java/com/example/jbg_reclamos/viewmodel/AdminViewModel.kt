package com.example.jbg_reclamos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.jbg_reclamos.data.local.ClaimEntity
import com.example.jbg_reclamos.data.local.DbProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AdminViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = DbProvider.get(app).claimDao()

    private val _claims = MutableStateFlow<List<ClaimEntity>>(emptyList())
    val claims: StateFlow<List<ClaimEntity>> = _claims

    fun loadAll() {
        viewModelScope.launch {
            _claims.value = dao.getAllClaims()
        }
    }

    fun changeStatus(id: Long, status: String) {
        viewModelScope.launch {
            dao.updateStatus(id, status)
            loadAll()
        }
    }

    fun delete(claim: ClaimEntity) {
        viewModelScope.launch {
            dao.delete(claim)
            loadAll()
        }
    }
}
