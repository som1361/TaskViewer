package com.example.taskviewer.viewmodel

import com.example.taskviewer.application.TaskApplication
import com.example.taskviewer.domain.Repository.TaskRepository
import com.example.taskviewer.domain.model.FeedItemDTO
import com.example.taskviewer.domain.model.ProfileDTO
import com.example.taskviewer.domain.model.TaskDTO
import com.example.taskviewer.utils.BASE_URL
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.text.SimpleDateFormat
import javax.inject.Inject

class MainViewModel @Inject constructor(private var taskRepository: TaskRepository) {
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun fetchFeed() {
        val disposable: Disposable = taskRepository.getFeed()
            .concatMapIterable { items -> items }.concatMap { fetchDetails(it) }
            .toList().toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<FeedItemDTO>>() {
                override fun onComplete() {
                }

                override fun onNext(t: List<FeedItemDTO>) {
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

    fun fetchDetails(feedItem: FeedItemDTO): Observable<FeedItemDTO> {
        return Observable.zip(taskRepository.getTask(feedItem.task_id), taskRepository.getProfile(feedItem.profile_id),
            BiFunction<TaskDTO, ProfileDTO, FeedItemDTO>()
            { task, profile -> updateFeedItemInfo(task, profile, feedItem) })
    }

    private fun updateFeedItemInfo(task: TaskDTO, profile: ProfileDTO, feedItem: FeedItemDTO): FeedItemDTO {
        feedItem.image_url = BASE_URL + profile.avatar_mini_url.replaceFirst("/", "")
        feedItem.text = feedItem.text.replace("{profileName}", profile.first_name).replace("{taskName}", task.name)
        feedItem.created_at =
            SimpleDateFormat("EEE HH:mm:ss")
                .format(
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                        .parse(feedItem.created_at)
                )
        return feedItem
    }
}
