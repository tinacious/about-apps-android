package com.tinaciousdesign.aboutapps.utils

fun String.lastSegment(delimiter: String): String? =
    split(delimiter).lastOrNull()
