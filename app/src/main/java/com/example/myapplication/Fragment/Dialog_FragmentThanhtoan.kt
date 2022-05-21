package com.example.myapplication.Fragment

import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.example.myapplication.R
import android.widget.Toast
import com.example.myapplication.Service.APIServices
import com.example.myapplication.Activity.DangNhap
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.myapplication.Activity.HoaDon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Dialog_FragmentThanhtoan : DialogFragment() {
    private lateinit var view1: View
    private var txttenkhachang: TextView? = null
    private var txtemail: TextView? = null
    private var txtdiachi: TextView? = null
    private var txtsdt: TextView? = null
    private var btnthanhtoandialog: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view1 = inflater.inflate(R.layout.dialog_fragment_thanhtoan, container, false)
        anhxa()
        btnthanhtoandialog?.setOnClickListener {
            if (txtdiachi?.text.toString() == "" || txtemail?.text.toString() == "" || txtsdt?.text.toString() == "" || txttenkhachang?.text.toString() == "") {
                Toast.makeText(activity, "Không để trống dữ liệu", Toast.LENGTH_SHORT).show()
            } else if (!txtemail?.text.toString().endsWith("@gmail.com")) {
                Toast.makeText(activity, "Sai email", Toast.LENGTH_SHORT).show()
            } else if (txtsdt?.text.toString().length != 10) {
                Toast.makeText(activity, "Sai sdt", Toast.LENGTH_SHORT).show()
            } else {
                val dataService = APIServices.getService()
                val call = dataService.postgiohang(
                    DangNhap.sharedPreferences?.getInt("iduser", 0).toString() + "",
                    txttenkhachang?.text.toString(),
                    txtemail?.text.toString(),
                    txtdiachi?.text.toString(),
                    txtsdt?.text.toString()
                )
                call.enqueue(object : Callback<String?> {
                    override fun onResponse(call: Call<String?>, response: Response<String?>) {
                        Log.d("AAA", "loi: $response")
                        if (response.isSuccessful) {
                            var id: String? = ""
                            id = response.body()
                            Log.d("AAA", "iddonhang: $response")
                            Log.d("AAA", "iddonhang: $id")
                            Toast.makeText(activity, "Thanh Toán Thành Công", Toast.LENGTH_SHORT)
                                .show()
                            activity?.finish()
                            startActivity(activity?.intent)
                            val intent = Intent(activity, HoaDon::class.java)
                            intent.putExtra("iddonhang", id)
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<String?>, t: Throwable) {}
                })
            }
        }
        return view1
    }

    private fun anhxa() {
        btnthanhtoandialog = view1?.findViewById(R.id.btnthanhtoandialog)
        txttenkhachang = view1?.findViewById(R.id.txttenkhachang)
        txtemail = view1?.findViewById(R.id.txtemail)
        txtdiachi = view1?.findViewById(R.id.txtdiachi)
        txtsdt = view1?.findViewById(R.id.txtsdt)
        txttenkhachang = view1?.findViewById(R.id.txttenkhachang)
        if (DangNhap.sharedPreferences?.getString("username", "") == "") {
            startActivity(Intent(context, DangNhap::class.java))
        } else {
            txttenkhachang?.setText(DangNhap.sharedPreferences?.getString("username", ""))
            txtdiachi?.setText(DangNhap.sharedPreferences?.getString("diachi", ""))
            txtemail?.setText(DangNhap.sharedPreferences?.getString("email", ""))
            txtsdt?.setText(DangNhap.sharedPreferences?.getString("sodienthoai", ""))
        }
    }
}