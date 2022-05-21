package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.makeramen.roundedimageview.RoundedImageView
import android.os.Bundle
import com.example.myapplication.R
import android.content.Intent
import android.os.Handler
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import com.squareup.picasso.Picasso
import androidx.core.view.GravityCompat
import com.example.myapplication.Adapter.Banner_Adapter

class MainActivity : AppCompatActivity() {
    private var toolbarMain: Toolbar? = null
    private var drawerLayout: DrawerLayout? = null
    private var viewPager: ViewPager? = null
    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private var RoundedImageViewavartar: RoundedImageView? = null
    private var imgsearch: ImageView? = null
    private var edttimkim: EditText? = null
    private var CurrentItem = 0
    private var txtdanhmuc: TextView? = null
    private var txthuawe: TextView? = null
    private var txtxiaomi: TextView? = null
    private var txtrealme: TextView? = null
    private var txtsony: TextView? = null
    private var txtvivo: TextView? = null
    private var txtdangxuat: TextView? = null
    private var txttennguoidung: TextView? = null
    private var txtgmail: TextView? = null
    private var txttrangchu: TextView? = null
    private var txtgiohang: TextView? = null
    private var txttaikhoan: TextView? = null
    private var txtdonhang: TextView? = null
    private var txtthongtin: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        anhxa()
        init()
        onclicknavigationbar()
        timkim()
    }

    private fun timkim() {
        imgsearch?.setOnClickListener {
            if (edttimkim?.text.toString() != "") {
                val intent = Intent(applicationContext, TimKiem::class.java)
                intent.putExtra("timkim", edttimkim?.text.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this@MainActivity, "không được để trống dữ liệu", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun onclicknavigationbar() {
        txtgiohang?.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    GioHang::class.java
                )
            )
        }
        txttrangchu?.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
        if (DangNhap.sharedPreferences?.getString("khachhang", "") != "") {
            txttennguoidung?.text = DangNhap.sharedPreferences?.getString("username", "")
            txtgmail?.text = DangNhap.sharedPreferences?.getString("email", "")
            Picasso.with(applicationContext)
                .load(DangNhap.sharedPreferences?.getString("hinhanh", ""))
                .into(RoundedImageViewavartar)
        }
        txttaikhoan?.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    TaiKhoan::class.java
                )
            )
        }
        txtdangxuat?.setOnClickListener {
            DangNhap.editor?.remove("username")
            DangNhap.editor?.remove("password")
            DangNhap.editor?.remove("iduser")
            DangNhap.editor?.remove("email")
            DangNhap.editor?.remove("sodienthoai")
            DangNhap.editor?.remove("diachi")
            DangNhap.editor?.remove("khachhang")
            DangNhap.editor?.commit()
            startActivity(Intent(applicationContext, DangNhap::class.java))
        }
        txtdonhang?.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    DonHang::class.java
                )
            )
        }
    }

    private fun init() {
        actionbar()
        setviewpager()
    }

    private fun actionbar() {
        setSupportActionBar(toolbarMain)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbarMain?.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size)
        toolbarMain?.setNavigationOnClickListener { drawerLayout?.openDrawer(GravityCompat.START) }
    }

    private fun anhxa() {
        txtthongtin = findViewById(R.id.txtthongtinnnn)
        edttimkim = findViewById(R.id.edttimkim)
        imgsearch = findViewById(R.id.imgsearch)
        txtdonhang = findViewById(R.id.txtdonhang)
        txttaikhoan = findViewById(R.id.txttaikhoan)
        txtgiohang = findViewById(R.id.txtgiohang)
        txttrangchu = findViewById(R.id.txttrangchu)
        RoundedImageViewavartar = findViewById(R.id.RoundedImageViewavartar)
        txtgmail = findViewById(R.id.txtgmail)
        txttennguoidung = findViewById(R.id.txttennguoidung)
        txtdangxuat = findViewById(R.id.txtdangxuat)
        txthuawe = findViewById(R.id.txthuawei)
        txtrealme = findViewById(R.id.txtrealmi)
        txtsony = findViewById(R.id.txtsony1)
        txtxiaomi = findViewById(R.id.txtxiaomi1)
        txtvivo = findViewById(R.id.txtvivo1)
        viewPager = findViewById(R.id.viewpagermain)
        toolbarMain = findViewById(R.id.toolbarMain)
        drawerLayout = findViewById(R.id.drawerlayout)
        txthuawe?.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, SanPham::class.java)
            intent.putExtra("id", 4)
            startActivity(intent)
        })
        txtrealme?.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, SanPham::class.java)
            intent.putExtra("id", 6)
            startActivity(intent)
        })
        txtsony?.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, SanPham::class.java)
            intent.putExtra("id", 8)
            startActivity(intent)
        })
        txtxiaomi?.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, SanPham::class.java)
            intent.putExtra("id", 5)
            startActivity(intent)
        })
        txtvivo?.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, SanPham::class.java)
            intent.putExtra("id", 7)
            startActivity(intent)
        })
        txtthongtin?.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    Activity_Vitrishop::class.java
                )
            )
        })
        txtdanhmuc = findViewById(R.id.txtdanhmuc)
        txtdanhmuc?.setOnClickListener(View.OnClickListener {
            val popupMenu = PopupMenu(applicationContext, txtdanhmuc)
            popupMenu.menuInflater.inflate(R.menu.menudanhmuc, popupMenu.menu)
            popupMenu.show()
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.vhdd -> {
                        val intent = Intent(applicationContext, SanPham::class.java)
                        intent.putExtra("id", 4)
                        startActivity(
                            intent
                        )
                    }
                    R.id.gkgtbt -> {
                        val intent1 = Intent(applicationContext, SanPham::class.java)
                        intent1.putExtra("id", 5)
                        startActivity(
                            intent1
                        )
                    }
                    R.id.pl -> {
                        val intent2 = Intent(applicationContext, SanPham::class.java)
                        intent2.putExtra("id", 6)
                        startActivity(
                            intent2
                        )
                    }
                    R.id.tpvhvn -> {
                        val intent3 = Intent(applicationContext, SanPham::class.java)
                        intent3.putExtra("id", 7)
                        startActivity(
                            intent3
                        )
                    }
                    R.id.tlkhac -> {
                        val intent4 = Intent(applicationContext, SanPham::class.java)
                        intent4.putExtra("id", 8)
                        startActivity(
                            intent4
                        )
                    }
                }
                false
            }
        })
    }

    private fun setviewpager() {
        val arrayhinh = arrayOf(
            "https://cdn.tgdd.vn/Products/Images/42/153856/Slider/vi-vn-iphone-11-tinhnang.jpg",
            "https://cdn.tgdd.vn/Products/Images/42/153856/Slider/vi-vn-iphone-11-mausac.jpg",
            "https://cdn.tgdd.vn/Products/Images/42/248218/Slider/Thumb-Video-XIaomi-11T-Pro-5G-1020x570.jpg",
            "https://cdn.tgdd.vn/Products/Images/42/274158/Slider/iphone-se-2022-thumb-video-new-1020x570.jpg",
            "https://cdn.tgdd.vn/Products/Images/42/153856/Slider/vi-vn-iphone-11-tinhnang.jpg"
        )
        val adapter = Banner_Adapter(applicationContext, arrayhinh)
        viewPager?.adapter = adapter
        handler = Handler()
        runnable = Runnable {
            CurrentItem = viewPager?.currentItem ?: 0
            CurrentItem++
            if (CurrentItem >= viewPager?.adapter?.count ?: 0) {
                CurrentItem = 0
            }
            viewPager?.setCurrentItem(CurrentItem, true)
            handler?.postDelayed(runnable, 4500)
        }
        handler?.postDelayed(runnable, 4500)
    }
}