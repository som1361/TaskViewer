package com.example.taskviewer.application

import android.app.Application
import com.example.taskviewer.DI.component.AppComponent
import com.example.taskviewer.DI.component.AsyncComponent
import com.example.taskviewer.DI.component.DaggerAppComponent
import com.example.taskviewer.DI.component.DaggerAsyncComponent
import com.example.taskviewer.DI.module.AppModule


class TaskApplication : Application() {

    lateinit var photoComponent: AppComponent
    companion object {
        private lateinit var  asyncComponent: AsyncComponent
        fun getAsyncComponent(): AsyncComponent{
            return asyncComponent
        }
    }

    private fun initDagger(app: TaskApplication): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()

    override fun onCreate() {
        super.onCreate()
        photoComponent = initDagger(this)
        asyncComponent = DaggerAsyncComponent.create()
    }
}