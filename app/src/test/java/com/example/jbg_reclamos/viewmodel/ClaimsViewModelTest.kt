package com.example.jbg_reclamos.viewmodel

import com.example.jbg_reclamos.data.local.ClaimEntity
import com.example.jbg_reclamos.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ClaimsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `load trae reclamos del usuario`() = runTest {
        val fakeDao = FakeClaimDao()

        fakeDao.seed(
            ClaimEntity(
                ownerEmail = "test@test.com",
                productName = "Producto",
                problemType = "Tipo",
                description = "Descripción",
                imageUri = null,
                address = "Dirección",
                status = "PENDIENTE"
            )
        )

        val vm = ClaimsViewModel(dao = fakeDao)

        vm.load("test@test.com")

        assertEquals(1, vm.claims.value.size)
    }
}
