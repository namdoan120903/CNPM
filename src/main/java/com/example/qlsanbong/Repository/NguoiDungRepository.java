package com.example.qlsanbong.Repository;

import com.example.qlsanbong.Model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
  NguoiDung findBySdt(String sdt);
}
