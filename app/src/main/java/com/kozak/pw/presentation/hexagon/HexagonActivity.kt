package com.kozak.pw.presentation.hexagon

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kozak.pw.R
import com.kozak.pw.presentation.hexagon.view.HexagonRecyclerView
import java.util.*

class HexagonActivity : AppCompatActivity(), ImagesAdapter.ViewHolderClicks {

    companion object {
        fun intent(context: Context) = Intent(context, HexagonActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hexagon)
        loadViews()
    }

    private val TAG = javaClass.name

    private var images: MutableList<String>? = null
    private var imagesAdapter: ImagesAdapter? = null
    private var recyclerView: HexagonRecyclerView? = null

    private fun loadViews() {
        imagesAdapter = ImagesAdapter(LinkedList<String>(), this)
        recyclerView = findViewById<View>(R.id.rvItems) as HexagonRecyclerView
        images = LinkedList()
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/2592.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/31910.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/32128.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/31923.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/2574.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/24131.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/23690.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/69316.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/23760.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/37918.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/24346.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/2580.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/2592.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/31910.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/32128.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/31923.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/2574.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/24131.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/23690.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/69316.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/23760.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/37918.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/24346.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/2580.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/2592.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/31910.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/32128.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/31923.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/2574.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/24131.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/23690.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/69316.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/23760.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/37918.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/24346.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/2580.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/2592.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/31910.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/32128.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/31923.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/2574.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/24131.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/23690.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/69316.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/23760.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/37918.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/24346.jpg")
        images!!.add("https://s3.amazonaws.com/99Covers-Facebook-Covers/watermark/2580.jpg")
        imagesAdapter!!.updateList(images)
        recyclerView!!.setAdapter(imagesAdapter)
    }

    override fun onStorySelected(view: View?, position: Int, image: String?) {
        Toast.makeText(this, "$position ", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "onStorySelected: $position")
    }
}