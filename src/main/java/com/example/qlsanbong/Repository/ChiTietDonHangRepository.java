package com.example.qlsanbong.Repository;

import com.example.qlsanbong.Model.ChiTietDonHang;
import com.example.qlsanbong.Model.DonHang;
import com.example.qlsanbong.Model.NguoiDung;
import com.example.qlsanbong.Model.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChiTietDonHangRepository extends JpaRepository<ChiTietDonHang, Long> {

  ChiTietDonHang findByDonHangAndSanPham(DonHang donHang, SanPham sanPham);
}