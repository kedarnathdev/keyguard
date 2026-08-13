package com.keyguard.keyboard

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class KeyGuardApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        // Apply theme based on user preference
        val prefs = getSharedPreferences("keyguard_prefs", Context.MODE_PRIVATE)
        val themeMode = prefs.getInt("theme_mode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        AppCompatDelegate.setDefaultNightMode(themeMode)
    }
}
