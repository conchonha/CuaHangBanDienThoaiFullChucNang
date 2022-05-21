package com.example.myapplication.Activity.admin;

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Activity.admin.adapter.AdapterSanPham
import com.example.myapplication.Model.Sanpham
import com.example.myapplication.R
import com.example.myapplication.Service.APIServices
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 5/19/2022
*/
public class SanPhamActivity : AppCompatActivity() {
    private val REQUEST_CODE = 10000;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_san_pham)

        val toolbar = findViewById<Toolbar>(R.id.toolbarsanpham)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar?.setNavigationIcon(R.drawable.back)
        toolbar?.setNavigationOnClickListener(View.OnClickListener { finish() })

        findViewById<FloatingActionButton>(R.id.btnAdd).setOnClickListener {
            startActivityForResult(Intent(this,ThemSanPham::class.java),REQUEST_CODE)
        }

        val adapter1 = AdapterSanPham()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewsanpham)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 1)
        recyclerView.adapter = adapter1

        val dataService = APIServices.getService()
        val calback = dataService.getAllSanPham()
        calback.enqueue(object : Callback<List<Sanpham>> {
            override fun onResponse(call: Call<List<Sanpham>>, response: Response<List<Sanpham>>) {
                Log.d("SangTB", "onResponse: ${response.body()}")
                if(response.isSuccessful){
                    response.body()?.toMutableList()?.let { adapter1.updateList(it) }
                }
            }

            override fun onFailure(call: Call<List<Sanpham>>, t: Throwable) {
                Toast.makeText(
                    this@SanPhamActivity,
                    t.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            finish()
            startActivity(intent)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
