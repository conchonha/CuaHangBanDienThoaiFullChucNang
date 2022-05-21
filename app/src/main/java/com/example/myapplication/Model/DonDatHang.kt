package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class DonDatHang {
    @SerializedName("id")
    @Expose
    var id: Int? = 0

    @SerializedName("tentaikhoan")
    @Expose
    var tentaikhoan: String? = ""

    @SerializedName("email")
    @Expose
    var email: String? = ""

    @SerializedName("diachi")
    @Expose
    var diachi: String? = ""

    @SerializedName("sodienthoai")
    @Expose
    var sodienthoai: String? = ""

    @SerializedName("ngaydat")
    @Expose
    var ngaydat: String? = ""

    @SerializedName("trinhtrang")
    @Expose
    var trinhtrang: String? = ""

    @SerializedName("idtaikhoan")
    @Expose
    var idtaikhoan: Int? = 0
}