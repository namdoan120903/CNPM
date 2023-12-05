package com.example.qlsanbong.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pitch")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pitch {
  @Id
  @Column(name = "masan")
  private String masan;
  @Column(name = "kip")
  private String kip;
  @Column(name = "ngay")
  private Date ngay;
  @Column(name = "gia")
  private int gia;
}
