package com.example.qlsanbong.Repository;

import com.example.qlsanbong.Model.NguoiDung;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {
  @Query("select nd from NguoiDung nd where nd.sdt = ?1")
  NguoiDung findBySdt(String sdt);
}
