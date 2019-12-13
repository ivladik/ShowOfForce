package com.example.core.presentation.ui.custom_view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.example.core.R
import com.example.core.extension.gone
import com.example.core.extension.visible
import kotlinx.android.synthetic.main.v_loading.view.*

class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.v_loading, this)
        orientation = VERTICAL
        gravity = Gravity.CENTER
    }

    fun setState(state: State) {
        when (state) {
            State.LOADING -> {
                progress.visible()
                status.visible()
            }
            State.DONE -> {
                progress.gone()
                status.gone()
            }
        }
    }

    enum class State {
        LOADING,
        DONE
    }
}