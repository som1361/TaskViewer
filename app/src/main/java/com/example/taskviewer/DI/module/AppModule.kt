package com.example.taskviewer.DI.module

import android.app.Application
import android.content.Context
import com.example.taskviewer.DI.Scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app:Application) {

    @Provides
    @AppScope
    fun providesContext(): Context = app
}