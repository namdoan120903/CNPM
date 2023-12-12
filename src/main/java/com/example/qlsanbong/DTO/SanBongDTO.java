package com.example.qlsanbong.DTO;

import java.time.LocalDate;
import lombok.Data;

@Data
public class SanBongDTO {
  private Long id;
  private int kip;
  private LocalDate ngay;
}
