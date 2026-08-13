package com.keyguard.keyboard.keyboard

import android.content.Context
import android.content.SharedPreferences
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.keyguard.keyboard.R

class KeyGuardKeyboardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    
    private lateinit var keyboard: KeyGuardKeyboard
    private var keys: List<KeyGuardKeyboard.Key> = emptyList()
    
    // Dimensions
    private var keyWidth: Float = 0f
    private var keyHeight: Float = 0f
    private var keySpacing: Float = 4f
    private var rowSpacing: Float = 4f
    private var horizontalPadding: Float = 8f
    
    // Paints
    private val keyPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val keyTextPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val keyHintPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val suggestionPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    
    // Theme colors
    private var keyboardBgColor: Int = 0
    private var keyBgColor: Int = 0
    private var keyPressedColor: Int = 0
    private var keyTextColor: Int = 0
    private var keyHintColor: Int = 0
    
    // State
    private var pressedKey: KeyGuardKeyboard.Key? = null
    private var pressedKeyRect: RectF = RectF()
    
    // Preferences
    private val prefs: SharedPreferences = context.getSharedPreferences("keyguard_prefs", Context.MODE_PRIVATE)
    
    init {
        setupPaints()
        setupDimensions()
        applyTheme()
    }
    
    private fun setupPaints() {
        keyPaint.style = Paint.Style.FILL
        keyTextPaint.textAlign = Paint.Align.CENTER
        keyHintPaint.textAlign = Paint.Align.CENTER
        suggestionPaint.textAlign = Paint.Align.LEFT
        
        // Set text size
        val textSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, 20f, resources.displayMetrics
        )
        keyTextPaint.textSize = textSize
        
        val hintTextSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, 12f, resources.displayMetrics
        )
        keyHintPaint.textSize = hintTextSize
    }
    
    private fun setupDimensions() {
        // Calculate key dimensions based on screen width
        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels.toFloat()
        
        // Standard keyboard has 10 keys per row
        keyWidth = (screenWidth - (horizontalPadding * 2) - (keySpacing * 9)) / 10
        keyHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 48f, displayMetrics
        )
        
        keySpacing = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 4f, displayMetrics
        )
    }
    
    private fun applyTheme() {
        val isDark = when (resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK) {
            android.content.res.Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
        
        if (isDark) {
            keyboardBgColor = ContextCompat.getColor(context, R.color.keyboard_bg_dark)
            keyBgColor = ContextCompat.getColor(context, R.color.key_bg_dark)
            keyPressedColor = ContextCompat.getColor(context, R.color.key_pressed_dark)
            keyTextColor = ContextCompat.getColor(context, R.color.key_text_dark)
            keyHintColor = ContextCompat.getColor(context, R.color.key_hint_text_dark)
        } else {
            keyboardBgColor = ContextCompat.getColor(context, R.color.keyboard_bg_light)
            keyBgColor = ContextCompat.getColor(context, R.color.key_bg_light)
            keyPressedColor = ContextCompat.getColor(context, R.color.key_pressed_light)
            keyTextColor = ContextCompat.getColor(context, R.color.key_text_light)
            keyHintColor = ContextCompat.getColor(context, R.color.key_hint_text_light)
        }
        
        setBackgroundColor(keyboardBgColor)
        keyPaint.color = keyBgColor
        keyTextPaint.color = keyTextColor
        keyHintPaint.color = keyHintColor
    }
    
    fun setKeyboard(keyboard: KeyGuardKeyboard) {
        this.keyboard = keyboard
        this.keys = keyboard.getKeys()
        updateKeyboard()
    }
    
    fun updateKeyboard() {
        keys = keyboard.getKeys()
        requestLayout()
        invalidate()
    }
    
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        
        // Calculate total height: 4 rows + suggestions bar + padding
        val suggestionsHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 40f, resources.displayMetrics
        )
        val totalHeight = (keyHeight * 4) + (rowSpacing * 3) + suggestionsHeight + (horizontalPadding * 2)
        
        setMeasuredDimension(width, totalHeight.toInt())
    }
    
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        
        var currentX = horizontalPadding
        var currentY = horizontalPadding
        
        // Draw suggestions bar (top)
        val suggestionsHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 40f, resources.displayMetrics
        )
        drawSuggestionsBar(canvas, currentX, currentY, width - horizontalPadding, suggestionsHeight)
        currentY += suggestionsHeight + rowSpacing
        
        // Draw keys
        for ((index, key) in keys.withIndex()) {
            val keyWidthActual = keyWidth * key.width + (keySpacing * (key.width - 1))
            
            // Draw key background
            val rect = RectF(currentX, currentY, currentX + keyWidthActual, currentY + keyHeight)
            
            val bgColor = if (pressedKey == key) {
                keyPressedColor
            } else {
                keyBgColor
            }
            
            keyPaint.color = bgColor
            canvas.drawRoundRect(rect, 8f, 8f, keyPaint)
            
            // Draw key text
            val textY = currentY + keyHeight / 2 - (keyTextPaint.descent() + keyTextPaint.ascent()) / 2
            canvas.drawText(key.label, rect.centerX(), textY, keyTextPaint)
            
            // Draw hint text if present
            if (key.hintLabel.isNotEmpty()) {
                val hintY = currentY + keyHeight * 0.75f
                canvas.drawText(key.hintLabel, rect.centerX(), hintY, keyHintPaint)
            }
            
            // Update position for next key
            currentX += keyWidthActual + keySpacing
            
            // Wrap to next row if needed (simplified logic)
            if ((index + 1) % 10 == 0) {
                currentX = horizontalPadding
                currentY += keyHeight + rowSpacing
            }
        }
    }
    
    private fun drawSuggestionsBar(canvas: Canvas, left: Float, top: Float, right: Float, height: Float) {
        // Draw suggestion bar background
        keyPaint.color = keyboardBgColor
        canvas.drawRect(left, top, right, top + height, keyPaint)
        
        // Placeholder: show "Suggestions" text
        suggestionPaint.color = keyHintColor
        suggestionPaint.textSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, 14f, resources.displayMetrics
        )
        canvas.drawText("Suggestions", left + 16f, top + height * 0.7f, suggestionPaint)
    }
    
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                pressedKey = findKeyAt(x, y)
                pressedKey?.let {
                    updatePressedRect(it, x, y)
                    invalidate()
                }
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                // Handle key repeat for backspace, etc.
                pressedKey?.let { key ->
                    if (key.isRepeatable) {
                        // TODO: Implement key repeat
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                pressedKey?.let { key ->
                    handleKeyPress(key)
                }
                pressedKey = null
                invalidate()
                return true
            }
            MotionEvent.ACTION_CANCEL -> {
                pressedKey = null
                invalidate()
                return true
            }
        }
        
        return super.onTouchEvent(event)
    }
    
    private fun findKeyAt(x: Float, y: Float): KeyGuardKeyboard.Key? {
        var currentX = horizontalPadding
        var currentY = horizontalPadding
        
        val suggestionsHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 40f, resources.displayMetrics
        )
        currentY += suggestionsHeight + rowSpacing
        
        for ((index, key) in keys.withIndex()) {
            val keyWidthActual = keyWidth * key.width + (keySpacing * (key.width - 1))
            
            if (x >= currentX && x <= currentX + keyWidthActual &&
                y >= currentY && y <= currentY + keyHeight) {
                return key
            }
            
            currentX += keyWidthActual + keySpacing
            
            if ((index + 1) % 10 == 0) {
                currentX = horizontalPadding
                currentY += keyHeight + rowSpacing
            }
        }
        
        return null
    }
    
    private fun updatePressedRect(key: KeyGuardKeyboard.Key, x: Float, y: Float) {
        // Calculate rect for pressed key
        // (simplified - actual implementation would track exact position)
    }
    
    private fun handleKeyPress(key: KeyGuardKeyboard.Key) {
        val service = context as? KeyGuardInputMethodService ?: return
        
        when (key.code) {
            KeyGuardKeyboard.KEYCODE_SHIFT -> service.onKey(KeyGuardKeyboard.KEYCODE_SHIFT)
            KeyGuardKeyboard.KEYCODE_DELETE -> service.handleBackspace()
            KeyGuardKeyboard.KEYCODE_ENTER -> service.handleEnter()
            KeyGuardKeyboard.KEYCODE_MODE_CHANGE -> service.onKey(KeyGuardKeyboard.KEYCODE_MODE_CHANGE)
            else -> {
                if (key.code == 32) {
                    service.handleSpace()
                } else {
                    service.onKey(key.code)
                }
            }
        }
    }
}
