package com.example.myapplication.Activity.admin;

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.doAfterTextChanged
import com.example.myapplication.R
import com.example.myapplication.Service.APIServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.util.*

public class ThemSanPham : AppCompatActivity() {
    private val REQUEST_CODE_IMAGEVIEW = 1000
    private var urlImg: String = ""
    private lateinit var NgayGiamGia : TextView
    private lateinit var GiamGia : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        val toolbarquanlytaikhoan = findViewById<Toolbar>(R.id.toobarAddSanPham)
        setSupportActionBar(toolbarquanlytaikhoan)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbarquanlytaikhoan?.setNavigationIcon(R.drawable.back)
        toolbarquanlytaikhoan?.setNavigationOnClickListener(View.OnClickListener { finish() })

        themsanpham()
    }

    private fun themsanpham() {
        val HinhAnhSP = findViewById<TextView>(R.id.HinhAnhSP)

        HinhAnhSP.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_CODE_IMAGEVIEW)
        })

        val calendar = Calendar.getInstance()
        val nam = calendar[Calendar.YEAR]
        val thang = calendar[Calendar.MONTH]
        val ngay = calendar[Calendar.DATE]

        NgayGiamGia = findViewById(R.id.ngayGiamGia)
        NgayGiamGia.text = "$nam/${thang+1}/$ngay"
        NgayGiamGia.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this@ThemSanPham,
                { view, year, month, dayOfMonth -> NgayGiamGia.setText("$year/${month+1}/$dayOfMonth") },
                nam,
                thang,
                ngay
            )
            datePickerDialog.show()
        }


        GiamGia = findViewById<EditText>(R.id.GiamGia)
        GiamGia.doAfterTextChanged {
            if(it.toString().toIntOrNull() == null || it.toString().toInt() > 100){
                GiamGia.setText("0")
            }
        }

        val IDSP = findViewById<EditText>(R.id.IDSP)
        IDSP.doAfterTextChanged {
            if(it.toString().toIntOrNull() == null || !(4..8).contains(it.toString().toInt())){
                IDSP.setText("4")
            }
        }
        findViewById<Button>(R.id.btnAdd).setOnClickListener {
            val TenSP = findViewById<EditText>(R.id.TenSP)
            val GiaSP = findViewById<EditText>(R.id.GiaSP)
            val MoTaSP = findViewById<EditText>(R.id.MoTaSP)

            if(TenSP.text.toString().equals("")){
                Toast.makeText(this@ThemSanPham,"Vui lòng điền đúng formart",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val dataService = APIServices.getService()
            val calback = dataService.insertSanPham(
                TenSP.text.toString(),
                GiaSP.text.toString(),
                NgayGiamGia.text.toString(),
                GiamGia.text.toString(),
                urlImg,
                MoTaSP.text.toString(),
                IDSP.text.toString()
            )

            calback.enqueue(object : Callback<String?> {
                override fun onResponse(call: Call<String?>, response: Response<String?>) {
                    if(response.isSuccessful && response.body().equals("Success")){
                        Toast.makeText(this@ThemSanPham,"Thêm sản phẩm thành công",Toast.LENGTH_LONG).show()
                        setResult(RESULT_OK)
                        finish()
                    }else{
                        Toast.makeText(this@ThemSanPham,"Vui lòng điền đúng formart",Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<String?>, t: Throwable) {
                    Toast.makeText(this@ThemSanPham,"Thêm sản phẩm Thất bại",Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_IMAGEVIEW && resultCode == RESULT_OK && data != null) {
            val uri = data.data
            try {
                val inputStream = contentResolver.openInputStream(uri!!)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                val img = findViewById<ImageView>(R.id.imgInsert)
                img.setImageBitmap(bitmap)
                urlImg = toArrayString(bitmap)
                Log.d("SangTB", "onActivityResult: $urlImg")
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    private fun toArrayString(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val array = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(array, Base64.DEFAULT)
    }
}
