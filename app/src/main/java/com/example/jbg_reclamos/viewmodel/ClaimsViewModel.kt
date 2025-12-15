package com.example.jbg_reclamos.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jbg_reclamos.data.local.ClaimDao
import com.example.jbg_reclamos.data.local.ClaimEntity
import com.example.jbg_reclamos.data.local.DbProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ClaimsViewModel(
    context: Context
) : ViewModel() {

    private val dao: ClaimDao = DbProvider.get(context).claimDao()

    private val _claims = MutableStateFlow<List<ClaimEntity>>(emptyList())
    val claims: StateFlow<List<ClaimEntity>> = _claims

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun load(email: String) {
        viewModelScope.launch {
            _claims.value = dao.listByUser(email)
        }
    }

    fun create(
        email: String,
        product: String,
        type: String,
        desc: String,
        imageUri: String?,
        address: String
    ) {
        if (
            email.isBlank() ||
            product.isBlank() ||
            type.isBlank() ||
            desc.isBlank() ||
            address.isBlank()
        ) {
            _error.value = "Todos los campos son obligatorios"
            return
        }

        _error.value = null

        viewModelScope.launch {
            dao.insert(
                ClaimEntity(
                    ownerEmail = email,
                    productName = product,
                    problemType = type,
                    description = desc,
                    imageUri = imageUri,
                    address = address,
                    status = "PENDIENTE"
                )
            )
        }
    }

    fun delete(claim: ClaimEntity, email: String) {
        viewModelScope.launch {
            dao.delete(claim)
            load(email)
        }
    }
}
