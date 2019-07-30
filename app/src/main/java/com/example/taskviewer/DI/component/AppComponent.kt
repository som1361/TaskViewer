package com.example.taskviewer.DI.component

import com.example.taskviewer.DI.Scope.AppScope
import com.example.taskviewer.DI.module.AppModule
import com.example.taskviewer.application.TaskApplication
import dagger.Component
@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
fun inject(target:TaskApplication)
}