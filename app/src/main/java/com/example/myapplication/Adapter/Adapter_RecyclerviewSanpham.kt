package com.example.myapplication.Adapter

import android.content.Context
import com.example.myapplication.Model.Sanpham
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.example.myapplication.R
import com.squareup.picasso.Picasso
import android.widget.TextView
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.example.myapplication.Activity.Chitietsanpham
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Adapter_RecyclerviewSanpham(
    private val arrayList: ArrayList<Sanpham>,
    private val context: Context
) : RecyclerView.Adapter<Adapter_RecyclerviewSanpham.Viewhdler>() {
    private lateinit var view: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewhdler {
        view = View.inflate(context, R.layout.layoutsanpham, null)
        return Viewhdler(view)
    }

    override fun onBindViewHolder(holder: Viewhdler, position: Int) {
        val sanpham = arrayList[position]
        val calendar = Calendar.getInstance()
        val simpleDateFormat = DecimalFormat("###,###,###")
        val format = SimpleDateFormat("yyyy-MM-dd")
        if (sanpham.giamGia!! > 0 && sanpham.ngayGiamGia != "") {
            var ngaykhuyenmai: Date? = null
            var ngayhientai: Date? = null
            try {
                ngaykhuyenmai = format.parse(sanpham.ngayGiamGia + "")
                ngayhientai =
                    format.parse(calendar[Calendar.YEAR].toString() + "-" + calendar[Calendar.MONTH] + "-" + calendar[Calendar.DATE])
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            if (ngaykhuyenmai?.compareTo(ngayhientai) ?:0 > 0) {
                Log.d("AAA", "ngay$ngayhientai")
                holder.txtsale.text = "-" + sanpham.giamGia + "%"
                holder.txtgiagiam.paintFlags =
                    holder.txtgiasaugiam.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                holder.txtgiagiam.text = simpleDateFormat.format(sanpham.giaSP) + ""
                val giagiam = (100 - sanpham.giamGia!!).toFloat() / 100
                val giaspsaukhuyenmai = giagiam * sanpham.giaSP!!
                Log.d("AAA", giaspsaukhuyenmai.toString() + "")
                holder.txtgiasaugiam.setText(
                    simpleDateFormat?.format(
                        giaspsaukhuyenmai.toLong()
                    ) + "Đ"
                )
            }
        } else {
            holder.txtgiagiam.setTextColor(Color.RED)
            holder.txtgiagiam.text = simpleDateFormat.format(sanpham.giaSP) + "Đ"
            holder.txtgiasaugiam.text = ""
        }
        holder.txttensanpham.text = sanpham.tenSP
        Picasso.with(context).load(sanpham.hinhAnhSP).into(holder.imageviewsanpham)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class Viewhdler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageviewsanpham: ImageView
        val txtsale: TextView
        val txttensanpham: TextView
        val txtgiagiam: TextView
        val txtgiasaugiam: TextView

        init {
            imageviewsanpham = itemView.findViewById(R.id.imageviewsanpham)
            txtsale = itemView.findViewById(R.id.txtsale)
            txttensanpham = itemView.findViewById(R.id.txttensanpham)
            txtgiagiam = itemView.findViewById(R.id.txtgiagiam)
            txtgiasaugiam = itemView.findViewById(R.id.txtgiasaugiam)
            itemView.setOnClickListener { //Toast.makeText(context, arrayList.get(getPosition()).getID()+"", Toast.LENGTH_SHORT).show();
                val intent = Intent(context, Chitietsanpham::class.java)
                intent.putExtra("sanpham", arrayList[position])
                context.startActivity(intent)
            }
        }
    }
}