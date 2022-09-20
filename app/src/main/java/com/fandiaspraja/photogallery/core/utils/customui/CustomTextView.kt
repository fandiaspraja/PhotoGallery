package com.fandiaspraja.photogallery.core.utils.customui

import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.emoji.widget.EmojiTextViewHelper

class CustomTextView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    AppCompatTextView(context!!, attrs, defStyleAttr) {
    private var mEmojiTextViewHelper: EmojiTextViewHelper? = null
    override fun setFilters(filters: Array<InputFilter>) {
        super.setFilters(emojiTextViewHelper.getFilters(filters))
    }

    override fun setAllCaps(allCaps: Boolean) {
        super.setAllCaps(allCaps)
        emojiTextViewHelper.setAllCaps(allCaps)
    }

    /**
     * Returns the [EmojiTextViewHelper] for this TextView.
     *
     *
     * This method can be called from super constructors through [ ][.setFilters] or [.setAllCaps].
     */
    private val emojiTextViewHelper: EmojiTextViewHelper
        private get() {
            if (mEmojiTextViewHelper == null) {
                mEmojiTextViewHelper = EmojiTextViewHelper(this)
            }
            return mEmojiTextViewHelper!!
        }

    init {
        emojiTextViewHelper.updateTransformationMethod()
    }
}