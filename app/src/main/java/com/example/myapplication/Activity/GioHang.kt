package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import com.example.myapplication.Fragment.Dialog_FragmentThanhtoan
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.Service.DataService
import com.example.myapplication.Service.APIServices
import com.example.myapplication.Activity.DangNhap
import com.example.myapplication.Adapter.Adapter_Giohang
import com.example.myapplication.Model.GioHang
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat
import java.util.ArrayList

class GioHang : AppCompatActivity() {
    private var recyclerviewgiohang: RecyclerView? = null
    private var toolbargiohang: Toolbar? = null
    private var txtgiatong: TextView? = null
    private var btnthanhtoan: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gio_hang)
        anhxa()
        getdatagiohang()
        btnthanhtoan()
    }

    private fun btnthanhtoan() {
        btnthanhtoan?.setOnClickListener {
            val fragmentManager = supportFragmentManager
            val dialog_fragmentThanhtoan = Dialog_FragmentThanhtoan()
            dialog_fragmentThanhtoan.show(fragmentManager, "thanhtoan")
        }
    }

    private fun anhxa() {
        btnthanhtoan = findViewById(R.id.btnthanhtoan)
        txtgiatong = findViewById(R.id.txtgiatong)
        toolbargiohang = findViewById(R.id.toolbargiohang)
        setSupportActionBar(toolbargiohang)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbargiohang?.setNavigationIcon(R.drawable.back)
        toolbargiohang?.setNavigationOnClickListener(View.OnClickListener { finish() })
        recyclerviewgiohang = findViewById(R.id.recyclerviewgiohang)
        recyclerviewgiohang?.setHasFixedSize(true)
        recyclerviewgiohang?.setLayoutManager(GridLayoutManager(applicationContext, 1))
    }

    private fun getdatagiohang() {
        val dataService = APIServices.getService()
        val callback = dataService.getdatagiohang(
            DangNhap.sharedPreferences
                ?.getInt("iduser", 0).toString() + ""
        )
        callback.enqueue(object : Callback<List<GioHang>> {
            override fun onResponse(call: Call<List<GioHang>>, response: Response<List<GioHang>>) {
                Log.d("AAA", "getdata giohang: $response")
                if (response.isSuccessful) {
                    val arrayList = response.body() as ArrayList<GioHang>
                    val adapter = Adapter_Giohang(arrayList, this@GioHang)
                    recyclerviewgiohang?.adapter = adapter
                    adapter.notifyDataSetChanged()
                    var tong = 0
                    for (i in arrayList.indices) {
                        tong += arrayList[i]?.gia ?: 0
                    }
                    val decimalFormat = DecimalFormat("###,###,###")
                    txtgiatong?.text = decimalFormat.format(tong.toLong()) + " Đ"
                }
            }

            override fun onFailure(call: Call<List<GioHang>>, t: Throwable) {}
        })
    }

    fun Deletegiohang(id: String?) {
        val dataService = APIServices.getService()
        val call = dataService.deletegiohang(id)
        call.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                Log.d("AAA", "deletegiohang: $response")
                if (response.isSuccessful) {
                    finish()
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {}
        })
    }

    fun tang(id: String?) {
        val dataService = APIServices.getService()
        val call = dataService.tang(id)
        call.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                Log.d("AAA", "tanggiohang: $response")
                if (response.isSuccessful) {
                    finish()
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {}
        })
    }

    fun giam(id: String?) {
        val dataService = APIServices.getService()
        val call = dataService.giam(id)
        call.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                Log.d("AAA", "tanggiohang: $response")
                if (response.isSuccessful) {
                    finish()
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {}
        })
    }
}