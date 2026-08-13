package com.keyguard.keyboard

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat

class SettingsFragment : PreferenceFragmentCompat() {
    
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        
        // Theme preference
        findPreference<ListPreference>("theme_mode")?.setOnPreferenceChangeListener { _, newValue ->
            val mode = when (newValue) {
                "light" -> AppCompatDelegate.MODE_NIGHT_NO
                "dark" -> AppCompatDelegate.MODE_NIGHT_YES
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
            AppCompatDelegate.setDefaultNightMode(mode)
            
            // Save preference
            activity?.getSharedPreferences("keyguard_prefs", Context.MODE_PRIVATE)
                ?.edit()
                ?.putInt("theme_mode", mode)
                ?.apply()
            
            true
        }
        
        // About section
        findPreference<Preference>("privacy_info")?.setOnPreferenceClickListener {
            showPrivacyInfo()
            true
        }
    }
    
    private fun showPrivacyInfo() {
        // Show privacy dialog or open privacy policy
    }
}
