package com.example.myapplication.Adapter

import android.content.Context
import android.view.View
import com.example.myapplication.Model.Danhgia
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.RatingBar
import com.makeramen.roundedimageview.RoundedImageView
import android.view.ViewGroup
import com.example.myapplication.Adapter.Comment_Adapter.Viewhodeler
import com.example.myapplication.R
import com.example.myapplication.Activity.DangNhap
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.ArrayList

class Comment_Adapter(
    private val context: Context,
    private val layout: Int,
    private val arrayList: ArrayList<Danhgia>
) : BaseAdapter() {
    private lateinit var view: View

    inner class Viewhodeler {
        var txttenuser1: TextView? = null
        var txtcommentnguoidung: TextView? = null
        var txtngaythangdanhgia: TextView? = null
        var ratingBar: RatingBar? = null
        var img: RoundedImageView? = null
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(i: Int): Any {
        return arrayList[i]
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {
        var view = view
        var viewhodeler: Viewhodeler? = null
        if (view == null) {
            viewhodeler = Viewhodeler()
            view = View.inflate(context, layout, null)
            viewhodeler.txttenuser1 = view.findViewById(R.id.txttenuser1)
            viewhodeler.txtcommentnguoidung = view.findViewById(R.id.txtcommentnguoidung)
            viewhodeler.txtngaythangdanhgia = view.findViewById(R.id.txtngaythangdanhgia)
            viewhodeler.ratingBar = view.findViewById(R.id.ratingdanhgianguoidung)
            viewhodeler.img = view.findViewById(R.id.img)
            view.tag = viewhodeler
        } else {
            viewhodeler = view.tag as Viewhodeler
        }
        viewhodeler.txttenuser1?.text = arrayList[i].idUser + ""
        viewhodeler?.txtcommentnguoidung?.text = arrayList[i].comMent
        if (DangNhap.sharedPreferences?.getString("hinhanh", "") != "") {
            Picasso.with(context).load(DangNhap.sharedPreferences?.getString("hinhanh", ""))
                .into(viewhodeler.img)
        }
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
        viewhodeler.txtngaythangdanhgia!!.text = arrayList[i].ngayDanhGia + ""
        if (arrayList[i].get1sao() == 1) {
            viewhodeler.ratingBar?.rating = 1f
        } else if (arrayList[i].get2sao() == 1) {
            viewhodeler.ratingBar?.rating = 2f
        } else if (arrayList[i].get3sao() == 1) {
            viewhodeler.ratingBar?.rating = 3f
        } else if (arrayList[i].get4sao() == 1) {
            viewhodeler.ratingBar?.rating = 4f
        } else {
            viewhodeler.ratingBar?.rating = 5f
        }
        return view!!
    }
}