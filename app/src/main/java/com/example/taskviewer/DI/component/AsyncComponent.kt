package com.example.taskviewer.DI.component

import com.example.taskviewer.DI.Scope.AsyncScope
import com.example.taskviewer.DI.module.AsyncModule
import com.example.taskviewer.domain.model.FeedDTO
import dagger.Component
import io.reactivex.subjects.PublishSubject
import javax.inject.Named

@AsyncScope
@Component(modules = [AsyncModule::class])
interface AsyncComponent {
    @Named(AsyncModule.GET_Feed_OBSERVABLE)
    fun getGetFeedObservable(): PublishSubject<List<FeedDTO.Feed>>

    @Named(AsyncModule.GET_Feed_ERROR_OBSERVABLE)
    fun getGetFeedErrorObservable(): PublishSubject<Exception>
}