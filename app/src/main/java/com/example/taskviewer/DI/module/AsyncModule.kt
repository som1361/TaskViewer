package com.example.taskviewer.DI.module

import com.example.taskviewer.DI.Scope.AsyncScope
import com.example.taskviewer.domain.model.FeedDTO
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import javax.inject.Named

@Module
class AsyncModule() {
    companion object {
        const val GET_Feed_ERROR_OBSERVABLE = "getFeedErrorObservable"
        const val GET_Feed_OBSERVABLE = "getFeedObservable"
    }

    @Provides
    @AsyncScope
    @Named(GET_Feed_ERROR_OBSERVABLE)
    fun provideGetFeedErrorObservable(): PublishSubject<Exception> = PublishSubject.create()

    @Provides
    @AsyncScope
    @Named(GET_Feed_OBSERVABLE)
    fun provideGetFeedObservable(): PublishSubject<List<FeedDTO.Feed>> = PublishSubject.create()

}