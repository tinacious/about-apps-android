package com.tinaciousdesign.aboutapps.navigation

import androidx.annotation.Keep
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.tinaciousdesign.aboutapps.R
import com.tinaciousdesign.aboutapps.ui.icons.TinaciousDesignLogoIcon
import com.tinaciousdesign.aboutapps.ui.icons.TintedIconDrawable
import kotlinx.serialization.Serializable

@Serializable @Keep
sealed class Route {
    abstract val icon: @Composable () -> Unit

    @get:StringRes
    abstract val titleRes: Int

    val routeName: String? get() = javaClass.simpleName

    @Serializable @Keep
    data object AppsList : Route() {
        override val titleRes: Int get() = R.string.route_app_list

        override val icon: @Composable () -> Unit = {
            TintedIconDrawable(
                R.drawable.ic_apps,
                R.string.route_app_list
            )
        }
    }

    @Serializable @Keep
    data object About : Route() {
        override val titleRes: Int get() = R.string.route_about

        override val icon: @Composable () -> Unit = {
            TinaciousDesignLogoIcon()
        }
    }
}
