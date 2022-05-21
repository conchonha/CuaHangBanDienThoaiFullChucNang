package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.example.myapplication.R
import android.content.Intent
import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.Service.DataService
import com.example.myapplication.Service.APIServices
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.Adapter.Adapter_HoaDon
import com.example.myapplication.Model.HoaDon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class HoaDon : AppCompatActivity() {
    private var txtmadonhang: TextView? = null
    private var txttentaikhoan: TextView? = null
    private var txtemailhoadon: TextView? = null
    private var txtsodienthoai: TextView? = null
    private var txtngaymua: TextView? = null
    private var txttrinhtranghoadon: TextView? = null
    private var txtdiachihoadon: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hoa_don)
        init()
        getintent()
        actionbar()
    }

    private fun actionbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbarHoadon)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationIcon(R.drawable.back)
        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun getintent() {
        val intent = intent
        if (intent != null) {
            if (intent.hasExtra("iddonhang")) {
                val iddonhang = intent.getStringExtra("iddonhang")
                val dataService = APIServices.getService()
                val call = dataService.getAlldatadonhang(iddonhang)
                call.enqueue(object : Callback<List<HoaDon>> {
                    override fun onResponse(
                        call: Call<List<HoaDon>>,
                        response: Response<List<HoaDon>>
                    ) {
                        Log.d("AAA", "HoaDon: $response")
                        if (response.isSuccessful) {
                            val arrayList = response.body() as ArrayList<HoaDon>
                            val hoaDon = arrayList[0]
                            txtmadonhang?.text = hoaDon.iddondathang.toString() + ""
                            txttentaikhoan?.text = hoaDon.tentaikhoan
                            txtemailhoadon?.text = hoaDon.email
                            txtsodienthoai?.text = hoaDon.sodienthoai
                            txtngaymua?.text = hoaDon.ngaydat
                            txttrinhtranghoadon?.text = hoaDon.trinhtrang
                            txtdiachihoadon?.text = hoaDon.diachi
                            setrecyclerview(arrayList)
                        }
                    }

                    override fun onFailure(call: Call<List<HoaDon>>, t: Throwable) {}
                })
            }
        }
    }

    private fun setrecyclerview(arrayList: ArrayList<HoaDon>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewhoadon)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 1)
        recyclerView.setHasFixedSize(true)
        val adapter_hoaDon = Adapter_HoaDon(arrayList, R.layout.layout_hoadon, applicationContext)
        recyclerView.adapter = adapter_hoaDon
        adapter_hoaDon.notifyDataSetChanged()
    }

    private fun init() {
        txtmadonhang = findViewById(R.id.txtmadonhang)
        txttentaikhoan = findViewById(R.id.txttentaikhoan)
        txtemailhoadon = findViewById(R.id.txtemailhoadon)
        txtsodienthoai = findViewById(R.id.txtsodienthoai)
        txtngaymua = findViewById(R.id.txtngaymua)
        txttrinhtranghoadon = findViewById(R.id.txttrinhtranghoadon)
        txtdiachihoadon = findViewById(R.id.txtdiachihoadon)
    }
}