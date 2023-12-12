package com.example.qlsanbong.Service;

import com.example.qlsanbong.DTO.DonHangDTO;
import com.example.qlsanbong.DTO.SanPhamDTO;
import com.example.qlsanbong.Model.ChiTietDonHang;
import com.example.qlsanbong.Model.DonHang;
import com.example.qlsanbong.Model.NguoiDung;
import com.example.qlsanbong.Model.SanBong;
import com.example.qlsanbong.Model.SanPham;
import com.example.qlsanbong.Repository.ChiTietDonHangRepository;
import com.example.qlsanbong.Repository.NguoiDungRepository;
import com.example.qlsanbong.Repository.SanBongRepository;
import com.example.qlsanbong.Repository.SanPhamRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NguoiDungService {
  @Autowired
  NguoiDungRepository nguoiDungRepository;
  @Autowired
  SanPhamRepository sanPhamRepository;
  @Autowired
  SanBongRepository sanBongRepository;
  @Autowired
  ChiTietDonHangRepository chiTietDonHangRepository;
  public void dangkyNguoiDung(String sdt, String password){
    if(nguoiDungRepository.findBySdt(sdt) == null){
      NguoiDung nguoiDung = new NguoiDung();
      nguoiDung.setSdt(sdt);
      nguoiDung.setPassword(password);
      nguoiDung.setVaiTro("User");
      nguoiDungRepository.save(nguoiDung);
    }
    else {
      throw new RuntimeException("Tên đăng nhâp đã được sử dụng");
    }
  }

  public int dangnhapNguoiDung(String sdt, String password){
    if(nguoiDungRepository.findBySdt(sdt) == null){
      return 0;
    }
    else {
      NguoiDung nguoiDung = nguoiDungRepository.findBySdt(sdt);
      if(nguoiDung.getPassword().equals(password)) {
        if(nguoiDung.getVaiTro() == "Admin") return 3;
        else return 1;
      }
      else return 2;
    }
  }

  // xem danh sach san pham

  public List<SanPham> danhSachSanPham(){
    return sanPhamRepository.findAll();
  }
  public List<SanBong> danhsachSanBong(){
    return sanBongRepository.findAll();
  }
  // nhap don hang vao co so du lieu
  public String donHangNguoiDung(Long id, DonHangDTO donHangDTO){
    DonHang donHang = new DonHang();
    NguoiDung nguoiDung = nguoiDungRepository.findById(id).orElse(null);
    if(nguoiDung == null) return "Không tìm thấy người dùng";
    donHang.setNguoiDung(nguoiDung);
    List<SanPhamDTO> sanPhamDTOS = donHangDTO.getDonHangSanPham();
    for (SanPhamDTO sanPhamDTO: sanPhamDTOS) {
      SanPham sanPham = sanPhamRepository.findById( sanPhamDTO.getId()).orElse(null);
      int soLuongMua = sanPhamDTO.getSoLuongMua();
      ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
      chiTietDonHang.setDonHang(donHang);
      chiTietDonHang.setSanPham(sanPham);
      chiTietDonHang.setSoLuongMua(soLuongMua);
      chiTietDonHangRepository.save(chiTietDonHang);
    }
    return "Nhập đơn hàng thành công";
  }
}
