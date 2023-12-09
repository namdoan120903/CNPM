package com.example.qlsanbong.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name = "don_hang")
public class DonHang {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_don_hang")
  private Long id;

  @Column(name = "tong_tien")
  private int tongTien;

  @Column(name = "trang_thai")
  private String trangThai;

  @ManyToOne
  @JoinColumn(name = "id_nguoi_dung")
  private NguoiDung nguoiDung;

}
