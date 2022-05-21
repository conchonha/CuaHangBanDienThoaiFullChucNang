package com.example.myapplication.Activity.shipper;

import android.annotation.SuppressLint
import com.example.myapplication.Model.DonDatHang
import com.example.myapplication.Activity.DonHang
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.example.myapplication.Activity.DangNhap
import com.example.myapplication.R
import com.example.myapplication.Service.APIServices
import android.widget.Toast
import android.widget.TextView
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import com.example.myapplication.Activity.HoaDon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class AdapterDonHangShipper(
    private val arrayList: ArrayList<DonDatHang> ? = arrayListOf(),
    private val context: ShipperActivity? = ShipperActivity(),
    private val layout: Int? = R.layout.layoutdonhang
) : RecyclerView.Adapter<AdapterDonHangShipper.Viewhdler>() {
    private lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewhdler {
        view = View.inflate(context, layout!!, null)
        return Viewhdler(view)
    }

    override fun onBindViewHolder(holder: Viewhdler, @SuppressLint("RecyclerView") position: Int) {
        val donDatHang = arrayList?.get(position)
        holder.trinhtrang.text = donDatHang?.trinhtrang
        holder.ngaydat.text = donDatHang?.ngaydat
        holder.iddonhang.text = donDatHang?.id.toString() + ""

        holder.imgmenudonhang.setOnClickListener {
            val popupMenu = PopupMenu(context, holder.imgmenudonhang)
            popupMenu.menuInflater.inflate(R.menu.menu_update_donhang_shipper, popupMenu.menu)
            popupMenu.show()
            popupMenu.setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.menu_updatedonhang1) {
                    val dataService = APIServices.getService()
                    val call = dataService.updatedonhangHoanThanh(
                        arrayList?.get(position)?.id.toString() + ""
                    )
                    call.enqueue(object : Callback<String?> {
                        override fun onResponse(call: Call<String?>, response: Response<String?>) {
                            Log.d("BBB", "update donhang: $response")
                            if (response.isSuccessful) {
                                if(response.body().equals("succes")){
                                    Toast.makeText(context, "Update thanh cong", Toast.LENGTH_SHORT)
                                        .show()
                                    context?.reload()
                                }else{
                                    Toast.makeText(context, "Update Thất Bại", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }
                        }

                        override fun onFailure(call: Call<String?>, t: Throwable) {
                            Toast.makeText(context, "Không connect dc tới server", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
                }

                false
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList!!.size
    }

    inner class Viewhdler(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iddonhang: TextView
        val ngaydat: TextView
        val trinhtrang: TextView
        val imgmenudonhang: ImageView

        init {
            iddonhang = itemView.findViewById(R.id.iddonhang)
            ngaydat = itemView.findViewById(R.id.ngaydat)
            trinhtrang = itemView.findViewById(R.id.trinhtrang)
            imgmenudonhang = itemView.findViewById(R.id.imgmenudonhang)
            itemView.setOnClickListener {
                val intent = Intent(context?.applicationContext ?: itemView.context, HoaDon::class.java)
                intent.putExtra("iddonhang", arrayList?.get(position)?.id.toString() + "")
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context?.startActivity(intent)
            }
        }
    }
}
