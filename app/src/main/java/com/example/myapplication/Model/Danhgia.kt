package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Danhgia {
    @SerializedName("Id_DanGgia")
    @Expose
    var idDanGgia: Int? = 0

    @SerializedName("namsao")
    @Expose
    private var _5sao: Int? = 0

    @SerializedName("bonsao")
    @Expose
    private var _4sao: Int? = 0

    @SerializedName("basao")
    @Expose
    private var _3sao: Int? = 0

    @SerializedName("haisao")
    @Expose
    private var _2sao: Int? = 0

    @SerializedName("motsao")
    @Expose
    private var _1sao: Int? = 0

    @SerializedName("ComMent")
    @Expose
    var comMent: String? = ""

    @SerializedName("Id_SanPham")
    @Expose
    var idSanPham: Int? = 0

    @SerializedName("username")
    @Expose
    var idUser: String? = ""

    @SerializedName("NgayDanhGia")
    @Expose
    var ngayDanhGia: String? = ""
    fun get5sao(): Int? {
        return _5sao
    }

    fun set5sao(_5sao: Int?) {
        this._5sao = _5sao
    }

    fun get4sao(): Int? {
        return _4sao
    }

    fun set4sao(_4sao: Int?) {
        this._4sao = _4sao
    }

    fun get3sao(): Int? {
        return _3sao
    }

    fun set3sao(_3sao: Int?) {
        this._3sao = _3sao
    }

    fun get2sao(): Int? {
        return _2sao
    }

    fun set2sao(_2sao: Int?) {
        this._2sao = _2sao
    }

    fun get1sao(): Int? {
        return _1sao
    }

    fun set1sao(_1sao: Int?) {
        this._1sao = _1sao
    }
}