package com.example.myapplication.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.example.myapplication.R
import com.squareup.picasso.Picasso
import android.widget.TextView
import com.example.myapplication.Model.GioHang
import java.text.DecimalFormat
import java.util.ArrayList

class Adapter_Giohang(
    private val arrayList: ArrayList<GioHang>,
    private val context: com.example.myapplication.Activity.GioHang
) : RecyclerView.Adapter<Adapter_Giohang.Viewhdler>() {
    private lateinit var view: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewhdler {
        view = View.inflate(context, R.layout.layout_giohang, null)
        return Viewhdler(view)
    }

    override fun onBindViewHolder(holder: Viewhdler, position: Int) {
        val gioHang = arrayList[position]
        val decimalFormat = DecimalFormat("###,###,###")
        holder.txtgiasanphamgiohang?.text = decimalFormat.format(gioHang.gia) + ""
        holder.txtsoluonggiohang?.text = gioHang.soluong.toString() + ""
        Picasso.with(context).load(gioHang.hinhsp).into(holder.imggiohang11)
        holder.imgbttonxoa.setOnClickListener { context.Deletegiohang(gioHang.idgiohang.toString() + "") }
        holder.btntang.setOnClickListener { context.tang(gioHang.idgiohang.toString() + "") }
        holder.btngiam.setOnClickListener { context.giam(gioHang.idgiohang.toString() + "") }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class Viewhdler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imggiohang11: ImageView
        val imgbttonxoa: ImageView
        val txtgiasanphamgiohang: TextView
        val txtsoluonggiohang: TextView
        val btngiam: Button
        val btntang: Button

        init {
            imggiohang11 = itemView.findViewById(R.id.imggiohang11)
            imgbttonxoa = itemView.findViewById(R.id.imgbttonxoa)
            txtgiasanphamgiohang = itemView.findViewById(R.id.txtgiasanphamgiohang)
            txtsoluonggiohang = itemView.findViewById(R.id.txtsoluonggiohang)
            btngiam = itemView.findViewById(R.id.btngiam)
            btntang = itemView.findViewById(R.id.btntang)
        }
    }
}