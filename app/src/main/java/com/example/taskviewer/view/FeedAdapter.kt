package com.example.taskviewer.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.taskviewer.R
import com.example.taskviewer.domain.model.FeedItem
import kotlinx.android.synthetic.main.feed_item.view.*

//Make the class extend RecyclerView.ViewHolder, allowing the adapter to use it as as a ViewHolder
class FeedAdapter() : RecyclerView.Adapter<FeedAdapter.FeedHolder>() {
    var feedItems: List<FeedItem> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.FeedHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.feed_item, parent, false)
        return FeedHolder(view!!)
    }

    override fun getItemCount() = feedItems.size

    override fun onBindViewHolder(holder: FeedAdapter.FeedHolder, position: Int) {
        holder.bindFeed(feedItems[position])
    }

    fun updateFeed(feedItems: List<FeedItem>) {
        this.feedItems = feedItems

    }

    class FeedHolder(v: View) : RecyclerView.ViewHolder(v) {
        //Add a reference to the view you’ve inflated to allow the ViewHolder to access the views as an extension property
        private var view: View = v
        private var feedItem: FeedItem? = null

        fun bindFeed(feedItem: FeedItem) {
            this.feedItem = feedItem
            Glide.with(view.context).load(feedItem.image_url).into(view.profile_image)
            view.task_description.text = feedItem.text
            view.task_event.text = feedItem.event
            view.task_time.text = feedItem.created_at
        }
    }
}

