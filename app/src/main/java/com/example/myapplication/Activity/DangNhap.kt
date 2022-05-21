package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import android.view.animation.Animation
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.Activity.DangNhap
import android.content.Intent
import com.example.myapplication.Activity.MainActivity
import com.example.myapplication.Activity.admin.Admin
import com.example.myapplication.Activity.DangKy
import com.example.myapplication.Service.DataService
import com.example.myapplication.Service.APIServices
import com.example.myapplication.Model.Taikhoan
import android.content.DialogInterface
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.Activity.shipper.ShipperActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class DangNhap : AppCompatActivity() {
    private var edttendangnhap: EditText? = null
    private var edtpassword: EditText? = null
    private var btndangnhap: Button? = null
    private var cloud1: ImageView? = null
    private var star: ImageView? = null
    private var txtdangky: TextView? = null
    var animCloud: Animation? = null
    var animStar: Animation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dang_nhap)
        anhxxa()
        if (sharedPreferences?.getString("khachhang", "") != "") {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
        if (sharedPreferences?.getString("admin", "") != "") {
            startActivity(Intent(applicationContext, Admin::class.java))
        }
    }

    private fun anhxxa() {
        txtdangky = findViewById(R.id.txtdangky)
        txtdangky?.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    applicationContext, DangKy::class.java
                )
            )
        })
        sharedPreferences = getSharedPreferences("datalogin", MODE_PRIVATE)
        editor = sharedPreferences?.edit()
        cloud1 = findViewById(R.id.cloud1)
        star = findViewById(R.id.star)
        animCloud = AnimationUtils.loadAnimation(this, R.anim.animcloud)
        animStar = AnimationUtils.loadAnimation(this, R.anim.animstar)
        cloud1?.startAnimation(animCloud)
        star?.startAnimation(animStar)
        edttendangnhap = findViewById(R.id.edttendangnhap)
        edtpassword = findViewById(R.id.edtpassword)
        btndangnhap = findViewById(R.id.btndangnhap)
        btndangnhap?.setOnClickListener(View.OnClickListener {
            if (edtpassword?.getText().toString() == "" || edttendangnhap?.getText()
                    .toString() == ""
            ) {
                Toast.makeText(this@DangNhap, "Không để trống dữ liệu", Toast.LENGTH_SHORT).show()
            } else {
                val dataService = APIServices.getService()
                val callback = dataService.dangnhap(
                    edttendangnhap?.getText().toString(),
                    edtpassword?.getText().toString()
                )
                callback.enqueue(object : Callback<List<Taikhoan>> {
                    override fun onResponse(
                        call: Call<List<Taikhoan>>,
                        response: Response<List<Taikhoan>>
                    ) {
                        Log.d("AAA", "Dangnhap: $response")
                        if (response.isSuccessful) {
                            val arrayList = response.body() as ArrayList<Taikhoan>
                            if (arrayList.size > 0) {
                                if (arrayList[0].loai == 1) {
                                    val dialog = AlertDialog.Builder(this@DangNhap)
                                    dialog.setTitle("Bạn có muốn đăng nhập với quyền admin ?")
                                    dialog.setNegativeButton("Không") { dialog, which ->
                                        editor?.remove("admin")
                                        editor?.putString("khachhang", "khachhang")
                                        editor?.putString(
                                            "username",
                                            edttendangnhap?.getText().toString()
                                        )
                                        editor?.putString(
                                            "password",
                                            edtpassword?.getText().toString()
                                        )
                                        editor?.putInt("iduser", arrayList[0]?.idUser ?: 0)
                                        editor?.putString("email", arrayList[0].email)
                                        editor?.putString("sodienthoai", arrayList[0].soDienThoai)
                                        editor?.putString("diachi", arrayList[0].diaChi)
                                        if (arrayList[0].hinhanh?.endsWith("jpg") == true) {
                                            editor?.putString(
                                                "hinhanh",
                                                APIServices.urlhinh + arrayList[0].hinhanh
                                            )
                                        } else {
                                            editor?.putString("hinhanh", arrayList[0].hinhanh)
                                        }
                                        editor?.commit()
                                        Toast.makeText(
                                            this@DangNhap,
                                            "Đăng Nhập thành công",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        startActivity(
                                            Intent(
                                                applicationContext,
                                                MainActivity::class.java
                                            )
                                        )
                                        finish()
                                    }
                                    dialog.setPositiveButton("Có") { dialog, which ->
                                        editor?.putString("admin", "admin")
                                        editor?.remove("khachhang")
                                        editor?.commit()
                                        startActivity(Intent(applicationContext, Admin::class.java))
                                    }
                                    dialog.show()
                                } else if (arrayList[0].loai == 0) {
                                    editor?.remove("admin")
                                    editor?.putString("khachhang", "khachhang")
                                    editor?.putString(
                                        "username",
                                        edttendangnhap?.getText().toString() ?: ""
                                    )
                                    editor?.putString(
                                        "password",
                                        edtpassword?.getText().toString() ?: ""
                                    )
                                    editor?.putInt("iduser", arrayList[0]?.idUser ?: 0)
                                    editor?.putString("email", arrayList[0].email)
                                    editor?.putString("sodienthoai", arrayList[0].soDienThoai)
                                    editor?.putString("diachi", arrayList[0].diaChi)
                                    if (arrayList[0].hinhanh?.endsWith("jpg") == true) {
                                        editor?.putString(
                                            "hinhanh",
                                            APIServices.urlhinh + arrayList[0].hinhanh
                                        )
                                    } else {
                                        editor?.putString("hinhanh", arrayList[0].hinhanh)
                                    }
                                    editor?.commit()
                                    Toast.makeText(
                                        this@DangNhap,
                                        "Đăng Nhập thành công",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    startActivity(
                                        Intent(
                                            applicationContext,
                                            MainActivity::class.java
                                        )
                                    )
                                    finish()
                                } else if (arrayList[0].loai == 2) {
                                    startActivity(Intent(this@DangNhap,ShipperActivity::class.java))
                                }
                            } else {
                                Toast.makeText(
                                    this@DangNhap,
                                    "Tài khoản không chính sác",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<Taikhoan>>, t: Throwable) {
                        Toast.makeText(
                            this@DangNhap,
                            "Đăng Nhập Thất Bại",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        })
    }

    companion object {
        @JvmField
        var sharedPreferences: SharedPreferences? = null

        @JvmField
        var editor: SharedPreferences.Editor? = null
    }
}