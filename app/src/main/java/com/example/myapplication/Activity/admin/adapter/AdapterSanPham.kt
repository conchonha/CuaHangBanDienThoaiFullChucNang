package com.example.myapplication.Activity.admin.adapter;

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.Sanpham
import com.example.myapplication.R
import com.example.myapplication.Service.APIServices
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Response

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 5/20/2022
*/
public class AdapterSanPham : RecyclerView.Adapter<AdapterSanPham.ViewHolder>() {
    private var listItem = mutableListOf<Sanpham>()

    fun updateList(list:MutableList<Sanpham>){
        listItem.clear()
        listItem = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private lateinit var txtName : TextView
        private lateinit var txtGia : TextView
        private lateinit var txtNgay : TextView
        private lateinit var imgHinh : ImageView

        init {
            view.findViewById<ImageView>(R.id.imgDelete).setOnClickListener {
                listItem[adapterPosition].id?.let {
                    val dataService = APIServices.getService()
                    val calback = dataService.deleteSanPham(it.toString())
                    calback.enqueue(object : retrofit2.Callback<String> {
                        override fun onResponse(call: Call<String>, response: Response<String>) {
                            Log.d("SangTB", "onResponse: ${response.body()}")
                            if(response.isSuccessful){
                                Toast.makeText(itemView.context,"Sản Phẩm Đã Được Xóa",Toast.LENGTH_LONG).show()
                                notifyItemRemoved(adapterPosition)
                            }
                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {
                            Toast.makeText(
                                itemView.context,
                                t.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
                }

            }
        }

        fun bin(sanpham: Sanpham){
            txtName = itemView.findViewById<TextView>(R.id.txtName)
            txtGia = itemView.findViewById<TextView>(R.id.txtGia)
            txtNgay = itemView.findViewById<TextView>(R.id.txtNgay)
            imgHinh = itemView.findViewById<ImageView>(R.id.imgHinh)

            txtName.setText("Tên: ${sanpham.tenSP}")
            txtGia.setText("Giá: ${sanpham.giaSP.toString()}")
            txtNgay.setText("Ngày giảm giá: ${sanpham.ngayGiamGia}")
            Picasso.with(itemView.context).load(sanpham.hinhAnhSP).into(imgHinh)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(View.inflate(parent.context,R.layout.item_sanpham,null))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("AAA", "onBindViewHolder: ${listItem[position]}")
       holder.bin(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size
}
