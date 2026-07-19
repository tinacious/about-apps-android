package com.tinaciousdesign.aboutapps.ui.utils

import android.graphics.Typeface
import android.text.style.StyleSpan
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.HtmlCompat
import androidx.core.text.getSpans
import com.tinaciousdesign.aboutapps.ui.theme.AboutAppsTheme

fun String.toColorizedStrongTextAnnotatedString(spanStyle: SpanStyle): AnnotatedString {
    val spanned = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)

    return buildAnnotatedString {
        append(spanned.toString().trimEnd())

        spanned.getSpans<StyleSpan>(0, this.length - 1).forEach { span ->
            val spanStart = spanned.getSpanStart(span)
            val spanEnd = spanned.getSpanEnd(span)

            when (span.style) {
                Typeface.BOLD -> addStyle(spanStyle, spanStart, spanEnd)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AboutAppsTheme {
        Surface {
            Text(
                text = "Hello, <strong>world</strong>!"
                    .toColorizedStrongTextAnnotatedString(
                        SpanStyle(
                            color = Color(0xFFFF3399),
                        )
                    )
            )
        }
    }
}
