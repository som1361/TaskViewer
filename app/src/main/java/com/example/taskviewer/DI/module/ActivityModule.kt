package com.example.taskviewer.DI.module

import android.app.Activity
import android.content.Context
import com.example.taskviewer.DI.Scope.ActivityScope
import com.example.taskviewer.domain.Repository.TaskRepository
import com.example.taskviewer.model.NetworkTaskRepository
import com.example.taskviewer.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityScope
    fun providesContext(): Context = activity

    /////ViewModel////////////////////////////////////
    @Provides
    @ActivityScope
    fun providesViewModel(taskRepository: TaskRepository) = MainViewModel(taskRepository)

    /////Model////////////////////////////////////////
    @Provides
    @ActivityScope
    fun providesTaskRepository(): TaskRepository = NetworkTaskRepository()
}

