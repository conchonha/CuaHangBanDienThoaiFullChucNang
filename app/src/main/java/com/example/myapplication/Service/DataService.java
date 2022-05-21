package com.example.myapplication.Service;


import com.example.myapplication.Activity.DonHang;
import com.example.myapplication.Model.Danhgia;
import com.example.myapplication.Model.DonDatHang;
import com.example.myapplication.Model.GioHang;
import com.example.myapplication.Model.HoaDon;
import com.example.myapplication.Model.Sanpham;
import com.example.myapplication.Model.Taikhoan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("model/sanpham/huawei")
    Call<List<Sanpham>>getdatahuawei();
    @GET("model/sanpham/xiaomi")
    Call<List<Sanpham>>getdataxiaomi();
    @GET("model/sanpham/realme")
    Call<List<Sanpham>>getdatarealme();
    @GET("model/sanpham/vivo")
    Call<List<Sanpham>>getdatavivo();
    @GET("model/sanpham/sony")
    Call<List<Sanpham>>getdatasony();

    @GET("model/sanpham/getAllSanPham")
    Call<List<Sanpham>>getAllSanPham();

    @FormUrlEncoded
    @POST("model/sanpham/sanpham")
    Call<List<Sanpham>>getdatasanpham(@Field("id") String idsp);
    @FormUrlEncoded
    @POST("model/taikhoan/dangnhap")
    Call<List<Taikhoan>>dangnhap(@Field("username") String username,
                                 @Field("pass") String pass);
    @FormUrlEncoded
    @POST("model/giohang/themvaogiohang")
    Call<String>themvaogiohang(@Field("idsp") String idsp,
                               @Field("iduser") String iduser);
    @FormUrlEncoded
    @POST("model/giohang/getdatagiohang")
    Call<List<GioHang>>getdatagiohang(@Field("iduser") String iduser);

    @FormUrlEncoded
    @POST("model/giohang/deletegiohang")
    Call<String>deletegiohang(@Field("idgiohang") String idgiohang);

    @FormUrlEncoded
    @POST("model/giohang/tang")
    Call<String>tang(@Field("idgiohang") String idgiohang);

    @FormUrlEncoded
    @POST("model/giohang/giam")
    Call<String>giam(@Field("idgiohang") String idgiohang);

    @FormUrlEncoded
    @POST("model/giohang/postthanhtoan")
    Call<String>postgiohang(@Field("idtaikhoan") String idtaikhoan,
                            @Field("tentaikhoan") String tentaikhoan,
                            @Field("email") String email,
                            @Field("diachi") String diachi,
                            @Field("sodienthoai") String sodienthoai
    );

    @FormUrlEncoded
    @POST("model/dondathang/getAlldatadonhang")
    Call<List<HoaDon>>getAlldatadonhang(@Field("iddondathang") String iddondathang);

    @FormUrlEncoded
    @POST("model/taikhoan/updatehinhanh")
    Call<String>Updatehinhanh(@Field("hinhanh") String hinhanh,
                              @Field("username") String username);

    @FormUrlEncoded
    @POST("model/taikhoan/updatethongtintaikhoan")
    Call<String>Updatetaikhoan(@Field("iduser") String iduser,
                               @Field("username") String username,
                               @Field("password") String password,
                               @Field("diachi") String diachi,
                               @Field("email") String email,
                               @Field("sodienthoai") String sodienthoai);

    @FormUrlEncoded
    @POST("model/donhang/getdatadonhang")
    Call<List<DonDatHang>>getdatadonhang(@Field("idusser") String idusser);

    @FormUrlEncoded
    @POST("model/danhgia/postDanhgia")
    Call<String>postDanhgia(@Field("username") String username,
                            @Field("id_sanpham") String idsanpham,
                            @Field("ngaydanhgia") String ngaydanhgia,
                            @Field("namsao") int namsao,
                            @Field("bonsao") int bonsao,
                            @Field("basao") int basao,
                            @Field("haisao") int haisao,
                            @Field("motsao") int motsao,
                            @Field("comment") String comment);

    @FormUrlEncoded
    @POST("model/danhgia/getdanhgia")
    Call<List<Danhgia>>getdataDanhgia(@Field("masanpham") String masanpham);

    @FormUrlEncoded
    @POST("model/sanpham/timkim")
    Call<List<Sanpham>>getdatasanphamsearch(@Field("timkim") String timkim);

    @GET("model/taikhoan/getdatataikhoan")
    Call<List<Taikhoan>>getdataalltaikhoan();

    @FormUrlEncoded
    @POST("model/taikhoan/dangkytaikhoan")
    Call<String>dangkytaikhoan(@Field("text_UserName") String text_UserName,
                               @Field("text_PassWord") String text_PassWord,
                               @Field("text_PhoneNumBer") String text_PhoneNumBer,
                               @Field("text_Email") String text_Email,
                               @Field("text_Adress") String text_Adress,
                               @Field("loai") String loai
                               );

    @GET("model/donhang/getdataAlldonhangAdmin")
    Call<List<DonDatHang>>getdataAlldonhangAdmin();

    @FormUrlEncoded
    @POST("model/donhang/updatedonhang")
    Call<String>updatedondathangadmin(@Field("iddonhang") String iddonhang
    );

    @FormUrlEncoded
    @POST("model/sanpham/insert_sanpham")
    Call<String>insertSanPham(@Field("TenSP") String TenSP,
                              @Field("GiaSP") String GiaSP,
                              @Field("NgayGiamGia") String NgayGiamGia,
                              @Field("GiamGia") String GiamGia,
                              @Field("HinhAnhSP") String HinhAnhSP,
                              @Field("MoTaSP") String MoTaSP,
                              @Field("IDSP") String IDSP);

    @FormUrlEncoded
    @POST("model/taikhoan/delete")
    Call<String>deleteTaikhoan(@Field("idtaikhoan") String idtaikhoan);

    @FormUrlEncoded
    @POST("model/dondathang/delete")
    Call<String>deleteDonDatHang(@Field("iddondathang") String iddondathang);

    @FormUrlEncoded
    @POST("model/sanpham/delete")
    Call<String>deleteSanPham(@Field("idSanPham") String idSanPham);

    @GET("model/dondathang/getTrangThaiDonHangDangVanChuyen")
    Call<List<DonDatHang>>trangThaiDonHangDangVanChuyen();

    @FormUrlEncoded
    @POST("model/dondathang/updatedonhangHoanThanh")
    Call<String>updatedonhangHoanThanh(@Field("iddonhang") String iddonhang);
}
