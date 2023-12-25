package com.example.qlsanbong.Repository;

import com.example.qlsanbong.Model.DonHang;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DonHangRepository extends JpaRepository<DonHang, Long> {
  List<DonHang> findByNguoiDung_Sdt(String sdt);
  @Query("select sum(dh.tongTien) from DonHang dh where dh.trangThai = ?1")
  int doanhThu(String trangthai);
}
