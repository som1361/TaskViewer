package com.example.taskviewer.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taskviewer.R
import com.example.taskviewer.domain.model.FeedDTO.Feed
import kotlinx.android.synthetic.main.feed_item.view.*

//Make the class extend RecyclerView.ViewHolder, allowing the adapter to use it as as a ViewHolder
class FeedAdapter() : RecyclerView.Adapter<FeedAdapter.FeedHolder>() {
    var tasks: List<Feed> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.FeedHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.feed_item, parent, false)
        return FeedHolder(view!!)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: FeedAdapter.FeedHolder, position: Int) {
        holder.bindFeed(tasks[position])
    }

    fun updateFeed(taskList: List<Feed>) {
        tasks = taskList

    }

    class FeedHolder(v: View) : RecyclerView.ViewHolder(v) {
        //Add a reference to the view you’ve inflated to allow the ViewHolder to access the views as an extension property
        private var view: View = v
        private var feed: Feed? = null

        fun bindFeed(feed: Feed) {
            this.feed = feed
            //Glide.with(view.context).load(feed.).into(view.item_image)
            view.task_description.text = feed.text
            view.task_event.text = feed.event
        }
    }
}

