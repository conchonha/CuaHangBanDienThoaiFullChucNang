package com.example.myapplication.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import com.example.myapplication.Activity.DangNhap
import com.example.myapplication.Service.DataService
import com.example.myapplication.Service.APIServices
import com.example.myapplication.Model.DonDatHang
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.Activity.admin.ThemSanPham
import com.example.myapplication.Adapter.Adapter_Donhang
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class DonHang : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var toolbardonhang: Toolbar? = null
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_don_hang)

        if (DangNhap.sharedPreferences?.getString("admin", "") == "") {
            getdatadondathang()
        } else {
            getdatadonhangAdmin()
        }
        anhxa()
    }

    private fun anhxa() {
        toolbardonhang = findViewById(R.id.toolbardonhang)
        setSupportActionBar(toolbardonhang)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbardonhang?.setNavigationIcon(R.drawable.back)
        toolbardonhang?.setNavigationOnClickListener(View.OnClickListener { finish() })
    }

    private fun getdatadonhangAdmin() {
        val dataService = APIServices.getService()
        val callback = dataService.getdataAlldonhangAdmin()
        callback.enqueue(object : Callback<List<DonDatHang?>?> {
            override fun onResponse(
                call: Call<List<DonDatHang?>?>,
                response: Response<List<DonDatHang?>?>
            ) {
                Log.d("AAA", "getdataall donhang admin: $response")
                if (response.isSuccessful) {
                    val arrayList = response.body() as? ArrayList<DonDatHang> ?: arrayListOf()
                    setRecyclerview(arrayList)
                    Log.d("AAA", DangNhap.sharedPreferences?.getInt("iduser", 0).toString() + "")
                }
            }

            override fun onFailure(call: Call<List<DonDatHang?>?>, t: Throwable) {}
        })
    }

    private fun getdatadondathang() {
        val dataService = APIServices.getService()
        val call = dataService.getdatadonhang(
            DangNhap.sharedPreferences?.getInt("iduser", 0).toString() + ""
        )
        call.enqueue(object : Callback<List<DonDatHang?>?> {
            override fun onResponse(
                call: Call<List<DonDatHang?>?>,
                response: Response<List<DonDatHang?>?>
            ) {
                Log.d("AAA", "getdatadonhang: $response")
                if (response.isSuccessful) {
                    val arrayList = response.body() as? ArrayList<DonDatHang> ?: arrayListOf()
                    setRecyclerview(arrayList)
                    Log.d("AAA", DangNhap.sharedPreferences?.getInt("iduser", 0).toString() + "")
                }
            }

            override fun onFailure(call: Call<List<DonDatHang?>?>, t: Throwable) {}
        })
    }

    fun reload() {
        finish()
        startActivity(intent)
    }

    private fun setRecyclerview(arrayList: ArrayList<DonDatHang>) {
        recyclerView = findViewById(R.id.recyclerviewdonhang)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.setLayoutManager(GridLayoutManager(applicationContext, 1))
        val adapter_donhang = Adapter_Donhang(arrayList, this@DonHang, R.layout.layoutdonhang)
        recyclerView?.setAdapter(adapter_donhang)
        adapter_donhang.notifyDataSetChanged()
    }
}