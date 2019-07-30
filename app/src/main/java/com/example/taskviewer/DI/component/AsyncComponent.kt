package com.example.taskviewer.DI.component

import com.example.taskviewer.DI.Scope.AsyncScope
import com.example.taskviewer.DI.module.AsyncModule
import dagger.Component

@AsyncScope
@Component(modules = [AsyncModule::class])
interface AsyncComponent {
}