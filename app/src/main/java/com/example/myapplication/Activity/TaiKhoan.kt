package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.Fragment.DialogfragmentUpdatetk
import android.content.Intent
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.Service.DataService
import com.example.myapplication.Service.APIServices
import com.example.myapplication.Activity.DangNhap
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException

class TaiKhoan : AppCompatActivity() {
    private var btnluu: Button? = null
    private var btnupdatethongtin: Button? = null
    private var imgthumuc: ImageView? = null
    private var imghinh: ImageView? = null
    private var toolbar23: Toolbar? = null
    private var txtuser: TextView? = null
    private var txtpassword: TextView? = null
    private var txtsodienthoai: TextView? = null
    private var email: TextView? = null
    private var txtdiachi: TextView? = null
    private val Request_code_image = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tai_khoan)
        anhxa()
        init()
        updatehinh()
        updatethongtin()
    }

    private fun updatethongtin() {
        btnupdatethongtin?.setOnClickListener {
            val fragmentManager = supportFragmentManager
            val dialog = DialogfragmentUpdatetk()
            dialog.show(fragmentManager, "updatethongtintk")
        }
    }

    private fun updatehinh() {
        imgthumuc?.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_PICK
            intent.type = "image/*"
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, Request_code_image)
            }
            btnluu?.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Request_code_image && resultCode == RESULT_OK && data != null) {
            val uri = data.data
            try {
                val inputStream = contentResolver.openInputStream(uri!!)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                imghinh?.setImageBitmap(bitmap)
                val dataService = APIServices.getService()
                val call =
                    dataService.Updatehinhanh(imagetostring(bitmap), txtuser?.text.toString())
                call.enqueue(object : Callback<String?> {
                    override fun onResponse(call: Call<String?>, response: Response<String?>) {
                        Log.d("AAA", "UpdateHinhanh: $response")
                        if (response.isSuccessful) {
                            DangNhap.editor?.putString(
                                "hinhanh",
                                APIServices.urlhinh + "img/" + txtuser?.text.toString() + ".jpg"
                            )
                            DangNhap.editor?.commit()
                            Log.d("AAA", DangNhap.sharedPreferences?.getString("hinhanh", ""))
                            finish()
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<String?>, t: Throwable) {}
                })
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun imagetostring(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val hinhanh = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(hinhanh, Base64.DEFAULT)
    }

    private fun init() {
        setSupportActionBar(toolbar23)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar23?.setNavigationIcon(R.drawable.back)
        toolbar23?.setNavigationOnClickListener { finish() }
        txtuser?.text = DangNhap.sharedPreferences?.getString("username", "")
        txtpassword?.text = DangNhap.sharedPreferences?.getString("password", "")
        txtsodienthoai?.text = DangNhap.sharedPreferences?.getString("sodienthoai", "")
        email?.text = DangNhap.sharedPreferences?.getString("email", "")
        txtdiachi?.text = DangNhap.sharedPreferences?.getString("diachi", "")
        Picasso.with(applicationContext).load(DangNhap.sharedPreferences?.getString("hinhanh", ""))
            .into(imghinh)
    }

    private fun anhxa() {
        btnupdatethongtin = findViewById(R.id.btnupdatethongtin)
        btnluu = findViewById(R.id.btnluu)
        imgthumuc = findViewById(R.id.imgthumuc)
        imghinh = findViewById(R.id.immm)
        toolbar23 = findViewById(R.id.toolbar23)
        txtuser = findViewById(R.id.user)
        txtpassword = findViewById(R.id.pass)
        txtsodienthoai = findViewById(R.id.sdt)
        email = findViewById(R.id.idd)
        txtdiachi = findViewById(R.id.diachi)
    }
}