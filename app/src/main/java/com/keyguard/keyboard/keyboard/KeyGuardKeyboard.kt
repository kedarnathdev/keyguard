package com.keyguard.keyboard.keyboard

import android.content.Context
import android.graphics.Paint
import android.graphics.Rect
import android.view.KeyEvent

class KeyGuardKeyboard(private val context: Context) {
    
    companion object {
        const val KEYCODE_SHIFT = -1
        const val KEYCODE_MODE_CHANGE = -2
        const val KEYCODE_DELETE = -5
        const val KEYCODE_ENTER = -4
        
        const val MODE_TEXT = 1
        const val MODE_NUMERIC = 2
        const val MODE_PHONE = 3
        const val MODE_SYMBOLS = 4
        
        // Standard QWERTY layout
        private val ROW_1 = "QWERTYUIOP"
        private val ROW_2 = "ASDFGHJKL"
        private val ROW_3 = "ZXCVBNM"
        
        private val ROW_1_SHIFT = ROW_1
        private val ROW_2_SHIFT = ROW_2
        private val ROW_3_SHIFT = ROW_3
    }
    
    private var mode: Int = MODE_TEXT
    private var shifted: Boolean = false
    private var capsLock: Boolean = false
    private var passwordMode: Boolean = false
    
    private val keys: MutableList<Key> = mutableListOf()
    
    data class Key(
        val code: Int,
        val label: String,
        val hintLabel: String = "",
        val width: Int = 1,
        val isRepeatable: Boolean = false,
        val isSticky: Boolean = false,
        val isModifier: Boolean = false
    )
    
    fun getKeys(): List<Key> = keys
    
    fun setMode(mode: Int) {
        this.mode = mode
        buildKeys()
    }
    
    fun getMode(): Int = mode
    
    fun toggleShift() {
        if (shifted && capsLock) {
            // If already caps lock, turn off
            shifted = false
            capsLock = false
        } else if (shifted) {
            // If shift on, enable caps lock
            capsLock = true
        } else {
            // Turn shift on
            shifted = true
        }
    }
    
    fun isShifted(): Boolean = shifted || capsLock
    
    fun toggleMode() {
        mode = when (mode) {
            MODE_TEXT -> MODE_SYMBOLS
            MODE_SYMBOLS -> MODE_TEXT
            else -> MODE_TEXT
        }
        buildKeys()
    }
    
    fun setPasswordMode(enabled: Boolean) {
        passwordMode = enabled
    }
    
    fun buildKeys() {
        keys.clear()
        
        when (mode) {
            MODE_TEXT -> buildQwertyKeys()
            MODE_SYMBOLS -> buildSymbolKeys()
            MODE_NUMERIC -> buildNumericKeys()
            MODE_PHONE -> buildPhoneKeys()
        }
    }
    
    private fun buildQwertyKeys() {
        // Row 1: Q W E R T Y U I O P
        val row1 = if (isShifted()) ROW_1_SHIFT else ROW_1
        for (char in row1) {
            keys.add(Key(code = char.code, label = char.toString()))
        }
        
        // Row 2: A S D F G H J K L (with proper offset)
        val row2 = if (isShifted()) ROW_2_SHIFT else ROW_2
        for (char in row2) {
            keys.add(Key(code = char.code, label = char.toString()))
        }
        
        // Row 3: Shift Z X C V B N M Delete
        keys.add(Key(
            code = KEYCODE_SHIFT,
            label = "⇧",
            width = 1,
            isSticky = true,
            isModifier = true
        ))
        
        val row3 = if (isShifted()) ROW_3_SHIFT else ROW_3
        for (char in row3) {
            keys.add(Key(code = char.code, label = char.toString()))
        }
        
        keys.add(Key(
            code = KEYCODE_DELETE,
            label = "⌫",
            width = 1,
            isRepeatable = true
        ))
        
        // Row 4: ?123 , Space . Enter
        keys.add(Key(
            code = KEYCODE_MODE_CHANGE,
            label = "SYMB",
            width = 1,
            isModifier = true
        ))
        keys.add(Key(
            code = 44, // comma
            label = ",",
            width = 1
        ))
        keys.add(Key(
            code = 32, // space
            label = "space",
            width = 3
        ))
        keys.add(Key(
            code = 46, // period
            label = ".",
            width = 1
        ))
        keys.add(Key(
            code = KEYCODE_ENTER,
            label = "↵",
            width = 2,
            isModifier = true
        ))
    }
    
    private fun buildSymbolKeys() {
        // Symbol keyboard
        val symbols = listOf(
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            "@", "#", "$", "%", "&", "*", "-", "+", "(", ")",
            "!", "\"", "'", ":", ";", "/", "?", "_", "=", "\\"
        )
        
        for (symbol in symbols) {
            keys.add(Key(code = symbol[0].code, label = symbol))
        }
        
        // Bottom row
        keys.add(Key(
            code = KEYCODE_MODE_CHANGE,
            label = "ABC",
            width = 1,
            isModifier = true
        ))
        keys.add(Key(
            code = 44,
            label = ",",
            width = 1
        ))
        keys.add(Key(
            code = 32,
            label = "space",
            width = 3
        ))
        keys.add(Key(
            code = 46,
            label = ".",
            width = 1
        ))
        keys.add(Key(
            code = KEYCODE_ENTER,
            label = "↵",
            width = 2,
            isModifier = true
        ))
    }
    
    private fun buildNumericKeys() {
        // Numeric only keyboard
        keys.add(Key(code = 49, label = "1"))
        keys.add(Key(code = 50, label = "2"))
        keys.add(Key(code = 51, label = "3"))
        keys.add(Key(code = 52, label = "4"))
        keys.add(Key(code = 53, label = "5"))
        keys.add(Key(code = 54, label = "6"))
        keys.add(Key(code = 55, label = "7"))
        keys.add(Key(code = 56, label = "8"))
        keys.add(Key(code = 57, label = "9"))
        keys.add(Key(code = 48, label = "0"))
        keys.add(Key(code = KEYCODE_DELETE, label = "⌫", isRepeatable = true))
    }
    
    private fun buildPhoneKeys() {
        // Phone dialpad
        keys.add(Key(code = 49, label = "1"))
        keys.add(Key(code = 50, label = "2", hintLabel = "ABC"))
        keys.add(Key(code = 51, label = "3", hintLabel = "DEF"))
        keys.add(Key(code = 52, label = "4", hintLabel = "GHI"))
        keys.add(Key(code = 53, label = "5", hintLabel = "JKL"))
        keys.add(Key(code = 54, label = "6", hintLabel = "MNO"))
        keys.add(Key(code = 55, label = "7", hintLabel = "PQRS"))
        keys.add(Key(code = 56, label = "8", hintLabel = "TUV"))
        keys.add(Key(code = 57, label = "9", hintLabel = "WXYZ"))
        keys.add(Key(code = 42, label = "*"))
        keys.add(Key(code = 48, label = "0", hintLabel = "+"))
        keys.add(Key(code = 35, label = "#"))
        keys.add(Key(code = KEYCODE_DELETE, label = "⌫", isRepeatable = true))
    }
}
