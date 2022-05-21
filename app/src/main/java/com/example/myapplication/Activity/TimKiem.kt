package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import com.example.myapplication.R
import android.content.Intent
import android.util.Log
import android.view.View
import com.example.myapplication.Service.DataService
import com.example.myapplication.Service.APIServices
import com.example.myapplication.Model.Sanpham
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.Adapter.Adapter_RecyclerviewSanpham
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class TimKiem : AppCompatActivity() {
    private var toolbartimkiem: Toolbar? = null
    private var recyclerviewtimkim: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tim_kiem)
        init()
        val intent = intent
        val timkim = intent.getStringExtra("timkim")
        if (timkim != "") {
            getdataproductsearch(timkim)
        }
    }

    private fun getdataproductsearch(timkim: String) {
        val dataService = APIServices.getService()
        val call = dataService.getdatasanphamsearch(timkim)
        call.enqueue(object : Callback<List<Sanpham?>> {
            override fun onResponse(
                call: Call<List<Sanpham?>>,
                response: Response<List<Sanpham?>>
            ) {
                Log.d("AAA", "getdataproduct search: $response")
                if (response.isSuccessful) {
                    val arrayList = response.body() as? ArrayList<Sanpham> ?: arrayListOf()
                    addRecyclerview(arrayList)
                    if (arrayList.size == 0) {
                        Toast.makeText(
                            this@TimKiem,
                            "Không tìm thấy sản phẩm nào",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(this@TimKiem, "Không tìm thấy sản phẩm nào", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<Sanpham?>>, t: Throwable) {}
        })
    }

    private fun addRecyclerview(arrayList: ArrayList<Sanpham>) {
        recyclerviewtimkim = findViewById(R.id.recyclerviewtimkim)
        recyclerviewtimkim?.setHasFixedSize(true)
        recyclerviewtimkim?.setLayoutManager(GridLayoutManager(applicationContext, 2))
        val adapter = Adapter_RecyclerviewSanpham(arrayList, this@TimKiem)
        recyclerviewtimkim?.setAdapter(adapter)
        adapter.notifyDataSetChanged()
    }

    private fun init() {
        toolbartimkiem = findViewById(R.id.toolbartimkiem)
        setSupportActionBar(toolbartimkiem)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbartimkiem?.setNavigationIcon(R.drawable.back)
        toolbartimkiem?.setNavigationOnClickListener(View.OnClickListener { finish() })
    }
}