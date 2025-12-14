package com.example.jbg_reclamos.ui.screen

import android.content.Context
import android.location.Geocoder
import java.util.Locale

fun getAddressFromLocation(
    context: Context,
    latitude: Double,
    longitude: Double
): String {
    return try {
        val geocoder = Geocoder(context, Locale("es", "CL"))
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)

        if (!addresses.isNullOrEmpty()) {
            val addr = addresses[0]
            val street = addr.thoroughfare ?: ""
            val number = addr.subThoroughfare ?: ""
            val comuna = addr.subAdminArea ?: ""
            val city = addr.adminArea ?: ""

            listOf(street, number, comuna, city)
                .filter { it.isNotBlank() }
                .joinToString(", ")
        } else {
            "Dirección no disponible"
        }
    } catch (e: Exception) {
        "Dirección no disponible"
    }
}
