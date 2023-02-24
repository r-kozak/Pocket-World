package com.kozak.pw.presentation.hexagon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kozak.pw.R
import com.kozak.pw.presentation.hexagon.view.HexagonImageView


class ImagesAdapter(
    private var images: MutableList<String>?,
    private val clicksListener: ViewHolderClicks
) : RecyclerView.Adapter<ImagesAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
        return CustomViewHolder(view, clicksListener)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        if (images == null || images!!.size == 0) {
            return
        }
        holder.setImageUrl(images!![position])
    }

    override fun getItemCount(): Int {
        return if (images == null) {
            0
        } else images!!.size
    }

    fun updateList(itemsList: MutableList<String>?) {
        images = itemsList
        notifyDataSetChanged()
    }

    fun addToList(itemsList: List<String>?) {
        images!!.addAll(itemsList!!)
        notifyDataSetChanged()
    }

    fun clear() {
        images!!.clear()
        notifyDataSetChanged()
    }

    class CustomViewHolder(view: View, var clicksListener: ViewHolderClicks?) :
        RecyclerView.ViewHolder(view), View.OnClickListener {
        var imageView: HexagonImageView
        var imageUrl: String? = null

        init {
            imageView = view.findViewById<View>(R.id.img_view) as HexagonImageView
            imageView.setOnClickListener(this)
        }

        fun setImageUrl(imageUrl: String?) {
            this.imageUrl = imageUrl
            Glide.with(imageView.context)
                .load(imageUrl)
                //.asBitmap()
                .placeholder(R.drawable.sample)
                .into(imageView)
        }

        override fun onClick(v: View) {
            if (clicksListener != null) clicksListener!!.onStorySelected(
                v, layoutPosition, imageUrl
            )
        }
    }

    interface ViewHolderClicks {
        fun onStorySelected(view: View?, position: Int, imageUrl: String?)
    }
}