package com.example.jbg_reclamos.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ClaimDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: ClaimDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        dao = db.claimDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertar_y_listar_reclamo_por_usuario() = runBlocking {

        val claim = ClaimEntity(
            ownerEmail = "test@test.com",
            productName = "Producto Test",
            problemType = "Falla",
            description = "No funciona",
            imageUri = null,
            address = "Calle falsa 123"
        )

        dao.insert(claim)

        val list = dao.listByUser("test@test.com")

        assertEquals(1, list.size)
        assertEquals("Producto Test", list[0].productName)
    }
}
