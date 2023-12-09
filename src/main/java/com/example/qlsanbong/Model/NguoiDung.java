package com.example.qlsanbong.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "nguoi_dung")
public class NguoiDung {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_nguoi_dung")
  private Long id;

  @Column(name = "sdt")
  private String sdt;

  @JsonIgnore
  @Column(name = "password")
  private String password;

  @Column(name = "ho_ten")
  private String hoTen;

  @Column(name = "vai_tro")
  private String vaiTro;

  @Column(name = "dia_chi")
  private String diaChi;

}
