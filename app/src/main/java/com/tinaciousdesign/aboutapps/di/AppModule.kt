package com.tinaciousdesign.aboutapps.di

import android.content.Context
import com.tinaciousdesign.aboutapps.events.EventBus
import com.tinaciousdesign.aboutapps.repositories.InstalledAppsRepository
import com.tinaciousdesign.aboutapps.repositories.InstalledAppsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // region Repositories

    @Provides @Singleton
    fun provideInstalledAppsRepository(
        @ApplicationContext appContext: Context,
    ): InstalledAppsRepository =
        InstalledAppsRepositoryImpl(
            appContext,
        )

    @Provides @Singleton
    fun provideEventBus(): EventBus = EventBus()

    // endregion Repositories
}
