package com.example.taskviewer.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.example.taskviewer.DI.component.ActivityComponent
import com.example.taskviewer.DI.component.DaggerActivityComponent
import com.example.taskviewer.DI.module.ActivityModule
import com.example.taskviewer.R
import com.example.taskviewer.application.TaskApplication
import com.example.taskviewer.domain.model.FeedItemDTO
import com.example.taskviewer.viewmodel.MainViewModel
import com.example.webviewscreenshot.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mMainViewModel: MainViewModel
    private lateinit var mLinearLayoutManager: GridLayoutManager
    private lateinit var mFeedAdapter: FeedAdapter
    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = DaggerActivityComponent
            .builder()
            .appComponent((application as TaskApplication).taskComponent)
            .activityModule(ActivityModule(this))
            .build()
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)
        loadView()
        listenToObservables()
    }

    private fun listenToObservables() {
        TaskApplication.getAsyncComponent().getGetFeedObservable().subscribe({
            feed_progress_bar.hide()
            if (it.size == 0)
                showSuccessMessage(this, R.string.empty_list)
            else {
                updateFeed(it)
            }
        })
        TaskApplication.getAsyncComponent().getGetFeedErrorObservable().subscribe({
            feed_progress_bar.hide()
            showFailMessage(this, R.string.error_get_data)
        })
    }

    private fun updateFeed(it: List<FeedItemDTO>) {
            mFeedAdapter.updateFeed(it)
            mFeedAdapter.notifyDataSetChanged()
    }

    private fun loadView() {
        setContentView(R.layout.activity_main)
        mFeedAdapter = FeedAdapter()
        mLinearLayoutManager = GridLayoutManager(this, 2)
        feed_recyclerview.layoutManager = mLinearLayoutManager
        feed_recyclerview.adapter = mFeedAdapter
        feed_progress_bar.show()
        mMainViewModel.fetchFeed()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMainViewModel.cancelNetworkConnections()
    }
}
