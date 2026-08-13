package com.keyguard.keyboard.keyboard

import android.content.Context
import android.inputmethodservice.InputMethodService
import android.text.InputType
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.view.inputmethod.InputMethodManager

class KeyGuardInputMethodService : InputMethodService() {
    
    private lateinit var keyboardView: KeyGuardKeyboardView
    private lateinit var keyboard: KeyGuardKeyboard
    private var lastText: String = ""
    
    override fun onCreate() {
        super.onCreate()
        keyboard = KeyGuardKeyboard(this)
    }
    
    override fun onCreateInputView(): View {
        keyboardView = KeyGuardKeyboardView(this)
        keyboardView.setKeyboard(keyboard)
        return keyboardView
    }
    
    override fun onCreateCandidatesView(): View? {
        // Return null if we don't want to show candidates inline
        // Our keyboard handles suggestions internally
        return null
    }
    
    override fun onStartInput(attribute: EditorInfo?, restarting: Boolean) {
        super.onStartInput(attribute, restarting)
        
        // Detect input type and adjust keyboard accordingly
        attribute?.let { info ->
            val inputType = info.inputType and InputType.TYPE_MASK_CLASS
            
            when (inputType) {
                InputType.TYPE_CLASS_NUMBER -> {
                    keyboard.setMode(KeyGuardKeyboard.MODE_NUMERIC)
                }
                InputType.TYPE_CLASS_PHONE -> {
                    keyboard.setMode(KeyGuardKeyboard.MODE_PHONE)
                }
                InputType.TYPE_CLASS_TEXT -> {
                    keyboard.setMode(KeyGuardKeyboard.MODE_TEXT)
                    
                    // Check for password fields
                    if (info.inputType and InputType.TYPE_TEXT_VARIATION_PASSWORD != 0) {
                        keyboard.setPasswordMode(true)
                    }
                }
                else -> {
                    keyboard.setMode(KeyGuardKeyboard.MODE_TEXT)
                }
            }
        }
        
        keyboardView.updateKeyboard()
    }
    
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
    }
    
    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyUp(keyCode, event)
    }
    
    fun onKey(code: Int) {
        val ic = currentInputConnection ?: return
        
        when (code) {
            KeyGuardKeyboard.KEYCODE_DELETE -> {
                ic.deleteSurroundingText(1, 0)
                playKeyClick(code)
            }
            KeyGuardKeyboard.KEYCODE_SHIFT -> {
                keyboard.toggleShift()
                keyboardView.updateKeyboard()
            }
            KeyGuardKeyboard.KEYCODE_ENTER -> {
                val action = currentInputEditorInfo?.imeOptions ?: 0
                when {
                    action and EditorInfo.IME_FLAG_NO_ENTER_ACTION != 0 -> {
                        ic.commitText("\n", 1)
                    }
                    action and EditorInfo.IME_ACTION_GO != 0 -> {
                        ic.performEditorAction(EditorInfo.IME_ACTION_GO)
                    }
                    action and EditorInfo.IME_ACTION_SEARCH != 0 -> {
                        ic.performEditorAction(EditorInfo.IME_ACTION_SEARCH)
                    }
                    action and EditorInfo.IME_ACTION_SEND != 0 -> {
                        ic.performEditorAction(EditorInfo.IME_ACTION_SEND)
                    }
                    else -> {
                        ic.commitText("\n", 1)
                    }
                }
                playKeyClick(code)
            }
            KeyGuardKeyboard.KEYCODE_MODE_CHANGE -> {
                keyboard.toggleMode()
                keyboardView.updateKeyboard()
            }
            else -> {
                if (code >= 32 && code <= 122) {
                    val text = if (keyboard.isShifted()) {
                        code.toChar().uppercaseChar().toString()
                    } else {
                        code.toChar().lowercaseChar().toString()
                    }
                    ic.commitText(text, 1)
                    playKeyClick(code)
                }
            }
        }
    }
    
    fun onText(text: CharSequence) {
        currentInputConnection?.commitText(text, 1)
    }
    
    private fun playKeyClick(code: Int) {
        // Use View.performHapticFeedback() - no VIBRATE permission needed!
        keyboardView.performHapticFeedback(
            android.view.HapticFeedbackConstants.KEYBOARD_TAP,
            android.view.HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
        )
    }
    
    fun handleBackspace() {
        currentInputConnection?.deleteSurroundingText(1, 0)
        playKeyClick(KeyGuardKeyboard.KEYCODE_DELETE)
    }
    
    fun handleSpace() {
        currentInputConnection?.commitText(" ", 1)
        playKeyClick(32)
    }
    
    fun handleEnter() {
        onKey(KeyGuardKeyboard.KEYCODE_ENTER)
    }
}
