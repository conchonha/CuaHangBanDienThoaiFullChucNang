package com.example.myapplication.Adapter

import android.content.Context
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import android.view.ViewGroup
import android.widget.ImageView
import com.example.myapplication.R
import com.squareup.picasso.Picasso

class Banner_Adapter(private val context: Context, private val arrayhinh: Array<String>) :
    PagerAdapter() {
    private lateinit var view: View
    override fun getCount(): Int {
        return arrayhinh.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        view = View.inflate(context, R.layout.bannerlayout, null)
        val imageView = view.findViewById<ImageView>(R.id.imageviewbanner)
        Picasso.with(context).load(arrayhinh[position]).into(imageView)
        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //  super.destroyItem(container, position, object);
        container.removeView(`object` as View)
    }
}