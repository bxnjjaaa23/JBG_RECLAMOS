package com.example.jbg_reclamos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jbg_reclamos.ui.theme.JBG_RECLAMOSTheme
import com.example.jbg_reclamos.navigation.AppNavigation



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JBG_RECLAMOSTheme {
                AppNavigation()
            }
        }
    }
}
