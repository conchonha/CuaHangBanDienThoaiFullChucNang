package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class HoaDon {
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

    @SerializedName("stt")
    @Expose
    var stt: Int? = 0

    @SerializedName("masanpham")
    @Expose
    var masanpham: Int? = 0

    @SerializedName("tensanpham")
    @Expose
    var tensanpham: String? = ""

    @SerializedName("soluongsanpham")
    @Expose
    var soluongsanpham: Int? = 0

    @SerializedName("giasanpham")
    @Expose
    var giasanpham: Int? = 0

    @SerializedName("hinhanhsanpham")
    @Expose
    var hinhanhsanpham: String? = ""

    @SerializedName("iddondathang")
    @Expose
    var iddondathang: Int? = 0
}