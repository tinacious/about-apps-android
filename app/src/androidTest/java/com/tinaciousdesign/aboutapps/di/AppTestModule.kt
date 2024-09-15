package com.tinaciousdesign.aboutapps.di

import com.tinaciousdesign.aboutapps.events.EventBus
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppTestModule {
    // region Repositories

    @Provides @Singleton
    fun provideEventBus(): EventBus = EventBus()

    // endregion Repositories
}
