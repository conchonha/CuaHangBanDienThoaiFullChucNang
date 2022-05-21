package com.example.myapplication.Activity.shipper;

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.Adapter_Donhang
import com.example.myapplication.Adapter.Adapter_RecyclerviewSanpham
import com.example.myapplication.Model.DonDatHang
import com.example.myapplication.Model.Sanpham
import com.example.myapplication.R
import com.example.myapplication.Service.APIServices
import com.google.android.gms.maps.SupportMapFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 5/21/2022
*/
public class ShipperActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shipper)
        setactionBar()
        getDataDonHang()
    }

    private fun getDataDonHang() {
        val api = APIServices.getService()
        val callback = api.trangThaiDonHangDangVanChuyen()
        callback.enqueue(object : Callback<List<DonDatHang?>?> {
            override fun onResponse(
                call: Call<List<DonDatHang?>?>,
                response: Response<List<DonDatHang?>?>
            ) {
                Log.d("AAAA", "getdata huawei$response")
                if (response.isSuccessful) {
                    if(response.body()?.size == 0){
                        Toast.makeText(this@ShipperActivity,"Hiện tại không có đơn hàng nào cần vận chuyển",Toast.LENGTH_LONG).show()
                    }
                    val arrayList = response.body() as? ArrayList<DonDatHang> ?: arrayListOf()
                    val rclDonHang = findViewById<RecyclerView>(R.id.rclDonHang)
                    val adapter_donhang = AdapterDonHangShipper(arrayList, this@ShipperActivity, R.layout.layoutdonhang)
                    rclDonHang?.setAdapter(adapter_donhang)
                    adapter_donhang.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<DonDatHang?>?>, t: Throwable) {
                Toast.makeText(this@ShipperActivity,"Hiện tại không load được dữ liệu",Toast.LENGTH_LONG).show()
            }
        })

    }

    fun reload(){
        finish()
        startActivity(intent)
    }

    private fun setactionBar() {
        toolbar = findViewById(R.id.toolbarGiaoHangVaSacNhan)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar?.setNavigationIcon(R.drawable.back)
        toolbar?.setNavigationOnClickListener(View.OnClickListener { finish() })
    }
}
