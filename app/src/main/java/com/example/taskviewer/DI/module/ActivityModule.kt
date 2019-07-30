package com.example.taskviewer.DI.module

import android.app.Activity
import android.content.Context
import com.example.taskviewer.DI.Scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityScope
    fun providesContext(): Context = activity
}
