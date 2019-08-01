package com.example.taskviewer.viewmodel

import com.example.taskviewer.application.TaskApplication
import com.example.taskviewer.domain.Repository.TaskRepository
import com.example.taskviewer.domain.model.FeedDTO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class MainViewModel @Inject constructor(private var taskRepository: TaskRepository){
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun fetchFeed()  {
        val disposable: Disposable = taskRepository.fetchFeed()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<FeedDTO.Feed>>(){
                override fun onSuccess(t: List<FeedDTO.Feed>) {
                    TaskApplication.getAsyncComponent().getGetFeedObservable().onNext(t)
                }

                override fun onError(e: Throwable) {
                    TaskApplication.getAsyncComponent().getGetFeedErrorObservable().onNext(e as HttpException)
                }

            })
        compositeDisposable.add(disposable)
    }

    fun cancelNetworkConnections() {
        compositeDisposable.clear()
    }
}
