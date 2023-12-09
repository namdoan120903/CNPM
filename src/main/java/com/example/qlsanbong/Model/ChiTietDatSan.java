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
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name = "chi_tiet_dat_san")
public class ChiTietDatSan {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_chi_tiet_dat_san")
  private Long id;

  @Column(name = "kip")
  private String kip;

  @Column(name = "ngay")
  private Date ngay;

  @ManyToOne
  @JoinColumn(name = "id_don_hang")
  private DonHang donHang;

  @ManyToOne
  @JoinColumn(name = "id_san_bong")
  private SanBong sanBong;

}
