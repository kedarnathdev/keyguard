package com.keyguard.keyboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    
    private var step = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Check if keyboard is enabled
        checkKeyboardStatus()
    }
    
    private fun checkKeyboardStatus() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        val enabledInputMethods = imm.enabledInputMethodList
        val isEnabled = enabledInputMethods.any { 
            it.packageName == packageName 
        }
        
        if (!isEnabled) {
            // Show enable keyboard screen
            showEnableKeyboardPrompt()
        } else if (!isKeyboardSelected()) {
            // Show select keyboard screen
            showSelectKeyboardPrompt()
        } else {
            // Show settings
            showSettings()
        }
    }
    
    private fun showEnableKeyboardPrompt() {
        startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
    }
    
    private fun showSelectKeyboardPrompt() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showInputMethodPicker()
    }
    
    private fun isKeyboardSelected(): Boolean {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        val defaultInputMethod = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.DEFAULT_INPUT_METHOD
        )
        return defaultInputMethod?.contains(packageName) == true
    }
    
    private fun showSettings() {
        // Load settings fragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, SettingsFragment())
            .commit()
    }
}
