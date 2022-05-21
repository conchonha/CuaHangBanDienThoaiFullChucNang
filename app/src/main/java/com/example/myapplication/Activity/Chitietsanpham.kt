package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.Model.Sanpham
import android.widget.TextView
import android.os.Bundle
import com.example.myapplication.R
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.myapplication.Fragment.Fragment_Danhgiasanpham
import com.example.myapplication.Activity.DangNhap
import com.example.myapplication.Service.DataService
import com.example.myapplication.Service.APIServices
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.Adapter.Banner_Adapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Chitietsanpham : AppCompatActivity() {
    private var toolbarchitiet: Toolbar? = null
    private var viewpagerchitietsanpham: ViewPager? = null
    private var sanpham: Sanpham? = null
    private var arrayhinh: Array<String> = arrayOf()
    private var txttensanpham1: TextView? = null
    private var txtgiasanpham1: TextView? = null
    private val txtngaydang1: TextView? = null
    private var txttrinhtrang: TextView? = null
    private var txtmota1: TextView? = null
    private var btnthemvaogiohang: Button? = null
    private val idsp = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chitietsanpham)
        anhxa()
        actionbar()
        val intent = intent
        sanpham = intent.getSerializableExtra("sanpham") as Sanpham
        sanpham?.moTaSP?.split("@")?.toTypedArray()?.let { arrayhinh = it }
        viewpagerbanner()
        themvaogiohang()
        addFragment(sanpham?.id.toString() + "")
    }

    private fun addFragment(masp: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = Fragment_Danhgiasanpham()
        val bundle = Bundle()
        bundle.putString("masp", masp)
        fragment.arguments = bundle
        fragmentTransaction.add(R.id.linner12, fragment)
        fragmentTransaction.commit()
    }

    private fun themvaogiohang() {
        btnthemvaogiohang?.setOnClickListener {
            if (DangNhap.sharedPreferences?.getString("username", "") == "") {
                startActivity(Intent(applicationContext, DangNhap::class.java))
            } else {
                Log.d("AAA", "idsp: " + sanpham?.id)
                Log.d("AAA", "iduser: " + DangNhap.sharedPreferences?.getInt("iduser", 0))
                val dataService = APIServices.getService()
                val calback = dataService.themvaogiohang(
                    sanpham?.id.toString() + "",
                    DangNhap.sharedPreferences?.getInt("iduser", 0).toString() + ""
                )
                calback.enqueue(object : Callback<String?> {
                    override fun onResponse(call: Call<String?>, response: Response<String?>) {
                        Log.d("AAA", "Themvaogiohang$response")
                        if (response.isSuccessful) {
                            Toast.makeText(
                                this@Chitietsanpham,
                                "Them Thanh cong",
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(applicationContext, GioHang::class.java))
                        }
                    }

                    override fun onFailure(call: Call<String?>, t: Throwable) {}
                })
            }
        }
    }

    private fun anhxa() {
        btnthemvaogiohang = findViewById(R.id.btnthemvaogiohang)
        txttensanpham1 = findViewById(R.id.txttensanpham1)
        txtgiasanpham1 = findViewById(R.id.txtgiasanpham1)
        txttrinhtrang = findViewById(R.id.txttrinhtrang)
        txtmota1 = findViewById(R.id.txtmota1)
    }

    private fun viewpagerbanner() {
        viewpagerchitietsanpham = findViewById(R.id.viewpagerchitietsanpham)
        val array = ArrayList<String>()
        array.add(sanpham?.hinhAnhSP ?: "")
        for (i in 1 until arrayhinh.size) {
            array.add(arrayhinh[i])
        }
        val adapter = Banner_Adapter(this@Chitietsanpham, array.toTypedArray())
        viewpagerchitietsanpham = findViewById(R.id.viewpagerchitietsanpham)
        viewpagerchitietsanpham?.setAdapter(adapter)

        txtmota1?.text = arrayhinh[0]
        txttensanpham1?.text = sanpham?.tenSP
        txttrinhtrang?.text = "Trình Trạng: Còn Hàng"
        val calendar = Calendar.getInstance()
        val simpleDateFormat = DecimalFormat("###,###,###")
        val format = SimpleDateFormat("yyyy-MM-dd")
        if (sanpham?.giamGia ?: 0 > 0 && sanpham?.ngayGiamGia != "") {
            var ngaykhuyenmai: Date? = null
            var ngayhientai: Date? = null
            try {
                ngaykhuyenmai = format.parse(sanpham?.ngayGiamGia + "")
                ngayhientai =
                    format.parse(calendar[Calendar.YEAR].toString() + "-" + calendar[Calendar.MONTH] + "-" + calendar[Calendar.DATE])
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            if (ngaykhuyenmai?.compareTo(ngayhientai) ?: 0 > 0) {
                val giagiam = (100 - (sanpham?.giamGia ?: 1)).toFloat() / 100
                val giaspsaukhuyenmai = giagiam * (sanpham?.giaSP ?: 0)
                txtgiasanpham1?.text =
                    "Giá" + simpleDateFormat?.format(giaspsaukhuyenmai.toLong()) + "Đ"
            }
        } else {
            txtgiasanpham1?.text = "Giá" + simpleDateFormat.format(sanpham?.giaSP) + "Đ"
        }
    }

    private fun actionbar() {
        toolbarchitiet = findViewById(R.id.toolbarchitiet)
        setSupportActionBar(toolbarchitiet)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbarchitiet?.setNavigationIcon(R.drawable.back)
        toolbarchitiet?.setNavigationOnClickListener(View.OnClickListener { finish() })
    }
}