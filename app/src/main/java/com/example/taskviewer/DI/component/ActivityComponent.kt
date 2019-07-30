package com.example.taskviewer.DI.component

import com.example.taskviewer.DI.Scope.ActivityScope
import com.example.taskviewer.DI.module.ActivityModule
import com.example.taskviewer.MainActivity
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class] , dependencies = [AppComponent ::class])
interface ActivityComponent {
    fun inject(target: MainActivity)

}