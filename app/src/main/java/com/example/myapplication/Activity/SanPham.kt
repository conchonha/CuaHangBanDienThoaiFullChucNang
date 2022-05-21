package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import android.content.Intent
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.Service.DataService
import com.example.myapplication.Service.APIServices
import com.example.myapplication.Model.Sanpham
import com.example.myapplication.Adapter.Adapter_RecyclerviewSanpham
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class SanPham : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_san_pham)
        anhxa()
        val intent = intent
        val id = intent.getIntExtra("id", 4)
        val recyclerviewsanpham = findViewById<RecyclerView>(R.id.recyclerviewsanpham)
        recyclerviewsanpham.layoutManager = GridLayoutManager(applicationContext, 2)
        recyclerviewsanpham.setHasFixedSize(true)
        val dataService = APIServices.getService()
        val callback = dataService.getdatasanpham(id.toString() + "")
        callback.enqueue(object : Callback<List<Sanpham?>?> {
            override fun onResponse(
                call: Call<List<Sanpham?>?>,
                response: Response<List<Sanpham?>?>
            ) {
                Log.d("AAA", "getdatasanpham$response")
                if (response.isSuccessful) {
                    val arrayList = response.body() as? ArrayList<Sanpham> ?: arrayListOf()
                    val adapter = Adapter_RecyclerviewSanpham(arrayList, this@SanPham)
                    recyclerviewsanpham.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Sanpham?>?>, t: Throwable) {}
        })
    }

    private fun anhxa() {
        val toolbarsanpham = findViewById<Toolbar>(R.id.toolbarsanpham)
        setSupportActionBar(toolbarsanpham)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbarsanpham.setNavigationIcon(R.drawable.back)
        toolbarsanpham.setNavigationOnClickListener { finish() }
    }
}