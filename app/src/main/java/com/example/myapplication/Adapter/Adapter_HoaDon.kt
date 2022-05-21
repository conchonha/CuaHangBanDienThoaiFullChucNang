package com.example.myapplication.Adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.Adapter_HoaDon.Viewhodler
import android.view.ViewGroup
import android.widget.TextView
import com.example.myapplication.Model.HoaDon
import com.example.myapplication.R
import java.util.ArrayList

class Adapter_HoaDon(
    private val arrayList: ArrayList<HoaDon>,
    private val layout: Int,
    private val context: Context
) : RecyclerView.Adapter<Viewhodler>() {
    private lateinit var view: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewhodler {
        view = View.inflate(context, layout, null)
        return Viewhodler(view)
    }

    override fun onBindViewHolder(holder: Viewhodler, position: Int) {
        val hoaDon = arrayList[position]
        holder.stt.setText("${position + 1}")
        val solong = hoaDon.soluongsanpham
        val gia = hoaDon.giasanpham
        val dongia = gia?.div(solong!!)
        holder.txtdongia.text = "$dongia Đ"
        holder.txtsoluong.text = solong.toString() + ""
        holder.txttensanpham1.text = hoaDon.tensanpham
        holder.txtthanhtien.text = "$gia Đ"
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class Viewhodler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stt: TextView
        val txttensanpham1: TextView
        val txtsoluong: TextView
        val txtdongia: TextView
        val txtthanhtien: TextView

        init {
            stt = itemView.findViewById(R.id.stt)
            txttensanpham1 = itemView.findViewById(R.id.txttensanpham1)
            txtsoluong = itemView.findViewById(R.id.txtsoluong)
            txtdongia = itemView.findViewById(R.id.txtdongia)
            txtthanhtien = itemView.findViewById(R.id.txtthanhtien)
        }
    }
}