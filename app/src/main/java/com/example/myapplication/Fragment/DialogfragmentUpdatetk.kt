package com.example.myapplication.Fragment

import android.widget.EditText
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.myapplication.R
import com.example.myapplication.Activity.DangNhap
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.myapplication.Service.APIServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DialogfragmentUpdatetk : DialogFragment() {
    private lateinit var view1: View
    private var edtussername: EditText? = null
    private var edtpass: EditText? = null
    private var edtsdt: EditText? = null
    private var edtemail: EditText? = null
    private var edtdiachi: EditText? = null
    private var btnupdate: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view1 = inflater.inflate(R.layout.dialog_fragment_update, container, false)
        anhxa()
        init()
        return view1
    }

    private fun init() {
        val iduser = DangNhap.sharedPreferences!!.getInt("iduser", 0)
        edtussername?.setText(DangNhap.sharedPreferences!!.getString("username", ""))
        edtdiachi?.setText(DangNhap.sharedPreferences!!.getString("diachi", ""))
        edtemail?.setText(DangNhap.sharedPreferences!!.getString("email", ""))
        edtpass?.setText(DangNhap.sharedPreferences!!.getString("password", ""))
        edtsdt?.setText(DangNhap.sharedPreferences!!.getString("sodienthoai", ""))
        btnupdate?.setOnClickListener {
            if (edtussername?.text.toString() == "" || edtdiachi?.text.toString() == "" || edtemail?.text.toString() == "" || edtpass?.text.toString() == "" || edtsdt?.text.toString() == "") {
                Toast.makeText(activity, "Không được để trống dữ liệu", Toast.LENGTH_SHORT).show()
            } else {
                btnupdate?.setOnClickListener {
                    val dataService = APIServices.getService()
                    val call = dataService.Updatetaikhoan(
                        iduser.toString() + "",
                        edtussername?.text.toString(),
                        edtpass?.text.toString(),
                        edtdiachi?.text.toString(),
                        edtemail?.text.toString(),
                        edtsdt?.text.toString()
                    )
                    call.enqueue(object : Callback<String?> {
                        override fun onResponse(call: Call<String?>, response: Response<String?>) {
                            Log.d("AAA", "Update taikhoan: $response")
                            if (response.isSuccessful) {
                                Log.d("AAA", response.body())
                                Toast.makeText(activity, "Update Thanh cong", Toast.LENGTH_SHORT)
                                    .show()
                                DangNhap.editor?.putString(
                                    "username",
                                    edtussername?.text.toString()
                                )
                                DangNhap.editor?.putString("password", edtpass?.text.toString())
                                DangNhap.editor?.putString("email", edtemail?.text.toString())
                                DangNhap.editor?.putString("sodienthoai", edtsdt?.text.toString())
                                DangNhap.editor?.putString("diachi", edtdiachi?.text.toString())
                                DangNhap.editor?.commit()
                                val fragment =
                                    fragmentManager?.findFragmentByTag("updatethongtintk")
                                if (fragment != null) {
                                    val dialogFragmen = fragment as DialogFragment
                                    dialogFragmen.dismiss()
                                }
                            }
                        }

                        override fun onFailure(call: Call<String?>, t: Throwable) {}
                    })
                }
            }
        }
    }

    private fun anhxa() {
        btnupdate = view1?.findViewById(R.id.btnupdate)
        edtussername = view1?.findViewById(R.id.edtussername)
        edtpass = view1?.findViewById(R.id.edtpass)
        edtsdt = view1?.findViewById(R.id.edtsdt)
        edtemail = view1?.findViewById(R.id.edtemail)
        edtdiachi = view1?.findViewById(R.id.edtdiachi)
    }
}