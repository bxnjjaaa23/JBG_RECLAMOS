package com.example.jbg_reclamos.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jbg_reclamos.util.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ClaimsViewModelTest {

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var fakeDao: FakeClaimDao
    private lateinit var viewModel: ClaimsViewModel

    @Before
    fun setup() {
        fakeDao = FakeClaimDao()
        viewModel = ClaimsViewModel(FakeApplication(fakeDao))
    }

    @Test
    fun no_se_crea_reclamo_con_campos_vacios() = runTest {
        viewModel.create(
            email = "",
            product = "",
            type = "",
            desc = "",
            imageUri = null,
            address = ""
        )

        advanceUntilIdle()

        assertEquals(0, fakeDao.items.size)
        assertEquals("Todos los campos son obligatorios", viewModel.error.value)
    }

    @Test
    fun se_crea_reclamo_con_datos_validos() = runTest {
        viewModel.create(
            email = "test@mail.com",
            product = "Teclado",
            type = "Producto dañado",
            desc = "No funciona",
            imageUri = null,
            address = "Av. Siempre Viva 123"
        )

        advanceUntilIdle()

        assertEquals(1, fakeDao.items.size)
        assertNull(viewModel.error.value)
    }
}
