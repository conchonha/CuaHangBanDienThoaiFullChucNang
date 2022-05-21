package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class GioHang {
    @SerializedName("idgiohang")
    @Expose
    var idgiohang: Int? = 0

    @SerializedName("idsanpham")
    @Expose
    var idsanpham: Int? = 0

    @SerializedName("idtaikhoan")
    @Expose
    var idtaikhoan: String? = ""

    @SerializedName("tensp")
    @Expose
    var tensp: String? = ""

    @SerializedName("hinhsp")
    @Expose
    var hinhsp: String? = ""

    @SerializedName("soluong")
    @Expose
    var soluong: Int? = 0

    @SerializedName("gia")
    @Expose
    var gia: Int? = 0
}