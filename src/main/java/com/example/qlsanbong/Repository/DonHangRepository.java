package com.example.qlsanbong.Repository;

import com.example.qlsanbong.Model.DonHang;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DonHangRepository extends JpaRepository<DonHang, Long> {
  List<DonHang> findByNguoiDung_Id(Long id);
  @Query("select sum(dh.tongTien) from DonHang dh")
  int doanhThu();
}
