package com.example.qlsanbong.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")

public class Product {
  @Id
  @Column(name = "masp")
  private String masp;
  @Column(name = "name")
  private String name;
  @Column(name = "gia")
  private int gia;
  @ManyToMany(mappedBy = "products")
  private List<Order> orders = new ArrayList<>();
}
