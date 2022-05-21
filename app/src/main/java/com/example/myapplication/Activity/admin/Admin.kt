package com.example.myapplication.Activity.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.myapplication.Activity.admin.QuanLytaikhoan
import com.example.myapplication.Activity.DangNhap
import com.example.myapplication.Activity.DonHang

class Admin : AppCompatActivity() {
    private var imgquanlytaikhoan: ImageView? = null
    private var imgdangxuat: ImageView? = null
    private var imgdonhang: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        imgquanlytaikhoan = findViewById(R.id.imgquanlytaikhoan)
        imgquanlytaikhoan?.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    applicationContext, QuanLytaikhoan::class.java
                )
            )
        })
        imgdangxuat = findViewById(R.id.imgdangxuat)
        imgdangxuat?.setOnClickListener(View.OnClickListener {
            DangNhap.editor?.remove("admin")
            DangNhap.editor?.commit()
            startActivity(Intent(applicationContext, DangNhap::class.java))
        })
        imgdonhang = findViewById(R.id.imgdonhang)
        imgdonhang?.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    DonHang::class.java
                )
            )
        })
        findViewById<LinearLayout>(R.id.SanPham).setOnClickListener {
            startActivity(Intent(this,SanPhamActivity::class.java))
        }
    }
}