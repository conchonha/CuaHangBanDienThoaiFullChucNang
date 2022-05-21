package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import java.io.Serializable

class Taikhoan : Serializable{
    @SerializedName("id_user")
    @Expose
    var idUser: Int? = 0

    @SerializedName("password")
    @Expose
    var password: String? = ""

    @SerializedName("username")
    @Expose
    var username: String? = ""

    @SerializedName("so_dien_thoai")
    @Expose
    var soDienThoai: String? = ""

    @SerializedName("email")
    @Expose
    var email: String? = ""

    @SerializedName("dia_chi")
    @Expose
    var diaChi: String? = ""

    @SerializedName("hinhanh")
    @Expose
    var hinhanh: String? = ""

    @SerializedName("loai")
    @Expose
    var loai: Int? = 0
}