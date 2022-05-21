package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import java.io.Serializable

class Sanpham : Serializable {
    @SerializedName("ID")
    @Expose
    var id: Int? = 0

    @SerializedName("TenSP")
    @Expose
    var tenSP: String? = ""

    @SerializedName("GiaSP")
    @Expose
    var giaSP: Int? = 0

    @SerializedName("NgayGiamGia")
    @Expose
    var ngayGiamGia: String? = ""

    @SerializedName("GiamGia")
    @Expose
    var giamGia: Int? = 0

    @SerializedName("HinhAnhSP")
    @Expose
    var hinhAnhSP: String? = ""

    @SerializedName("MoTaSP")
    @Expose
    var moTaSP: String? = ""

    @SerializedName("IDSP")
    @Expose
    var iDSP: Int? = 0
}