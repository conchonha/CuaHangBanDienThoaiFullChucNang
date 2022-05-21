package com.example.myapplication.Fragment

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapter.Adapter_RecyclerviewSanpham
import com.example.myapplication.Model.Sanpham
import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.example.myapplication.R
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myapplication.Activity.SanPham
import com.example.myapplication.Service.APIServices
import androidx.recyclerview.widget.GridLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class Fragment_4 : Fragment() {
    private var recyclerView: RecyclerView? = null
    private lateinit var view1: View
    var adapter_recyclerviewSanpham: Adapter_RecyclerviewSanpham? = null
    private var arrayList: ArrayList<Sanpham> = arrayListOf()
    private var txtXemThem4: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view1 = inflater.inflate(R.layout.fragment_4, container, false)
        anhxa()
        getdatasanpham()
        onclickxemthem()
        return view1
    }

    private fun onclickxemthem() {
        txtXemThem4?.setOnClickListener {
            val inten = Intent(context, SanPham::class.java)
            inten.putExtra("id", 4)
            startActivity(inten)
        }
    }

    private fun getdatasanpham() {
        val dataService = APIServices.getService()
        val callback = dataService.getdatahuawei()
        callback.enqueue(object : Callback<List<Sanpham?>?> {
            override fun onResponse(
                call: Call<List<Sanpham?>?>,
                response: Response<List<Sanpham?>?>
            ) {
                Log.d("AAA", "getdata huawei$response")
                if (response.isSuccessful) {
                    arrayList = response.body() as? ArrayList<Sanpham> ?: arrayListOf()
                    adapter_recyclerviewSanpham = Adapter_RecyclerviewSanpham(arrayList, requireContext())
                    recyclerView?.adapter = adapter_recyclerviewSanpham
                    adapter_recyclerviewSanpham?.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Sanpham?>?>, t: Throwable) {}
        })
    }

    private fun anhxa() {
        txtXemThem4 = view1?.findViewById(R.id.txtxemthemhuawei)
        arrayList = ArrayList()
        recyclerView = view1?.findViewById(R.id.recyclerviewhuawei)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.setLayoutManager(GridLayoutManager(context, 2))
    }
}