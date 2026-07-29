package com.rachmanm.tvshow.ui.util

import android.content.Context
import android.content.Intent
import com.rachmanm.tvshow.domain.model.Show

fun shareShow(context: Context, show: Show) {
    val shareText = buildString {
        appendLine(show.name)
        appendLine()
        show.summary?.let { appendLine(stripHtml(it)) }
        show.url?.let {
            appendLine()
            append(it)
        }
    }

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, shareText)
        putExtra(Intent.EXTRA_SUBJECT, show.name)
    }

    context.startActivity(Intent.createChooser(intent, "Share via"))
}