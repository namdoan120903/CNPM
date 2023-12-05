package com.example.qlsanbong.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
  @Id
  @Column(name = "sdt")
  private String sdt;

  @Column(name = "name")
  private String name;

  @Column(name = "ngaysinh")
  private String ngaysinh;

  @Column(name = "diachi")
  private String diachi;
  @OneToOne
  @JoinColumn(name = "account_id", referencedColumnName = "id")
  private Account account;

  @OneToMany(mappedBy = "user")
  private Set<Order> orders = new HashSet<>();


}
