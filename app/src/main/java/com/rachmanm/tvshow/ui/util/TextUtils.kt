package com.rachmanm.tvshow.ui.util

fun stripHtml(raw: String): String =
    raw.replace(Regex("<[^>]*>"), "").trim()