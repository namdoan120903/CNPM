package com.example.qlsanbong.Service;

import com.example.qlsanbong.DTO.DonHangDTO;
import com.example.qlsanbong.Model.ChiTietDatSan;
import com.example.qlsanbong.Model.ChiTietDonHang;
import com.example.qlsanbong.Model.DonHang;
import com.example.qlsanbong.Model.NguoiDung;
import com.example.qlsanbong.Model.SanPham;
import com.example.qlsanbong.Repository.ChiTietDatSanRepository;
import com.example.qlsanbong.Repository.ChiTietDonHangRepository;
import com.example.qlsanbong.Repository.DonHangRepository;
import com.example.qlsanbong.Repository.NguoiDungRepository;
import com.example.qlsanbong.Repository.SanPhamRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
  @Autowired
  SanPhamRepository sanPhamRepository;
  @Autowired
  NguoiDungRepository nguoiDungRepository;
  @Autowired
  DonHangRepository donHangRepository;
  @Autowired
  ChiTietDonHangRepository chiTietDonHangRepository;
  @Autowired
  ChiTietDatSanRepository chiTietDatSanRepository;

  public String themSanPham(SanPham sanPham){
    sanPhamRepository.save(sanPham);
    return "them san pham thanh cong";
  }
  public String xoaSanPham(Long id){
    SanPham sanPham = sanPhamRepository.findById(id).orElse(null);
    if(sanPham == null)  return "Không tìm thấy sản phẩm";
    sanPhamRepository.delete(sanPham);
    return "xoa san pham thanh cong";
  }
  public List<NguoiDung> danhSachTaiKhoan(){
    return nguoiDungRepository.findAll();
  }
  public List<DonHang> danhSachDonHang(){
    return donHangRepository.findAll();
  }

  public List<DonHang> danhSachDonHangSdt(String sdt){
    return donHangRepository.findByNguoiDung_Sdt(sdt);
  }
  // tim theo id don hang
  public DonHang donHangNguoiDung(Long id){
    return donHangRepository.findById(id).orElse(null);
  }

  public List<ChiTietDonHang> chiTietDonHang(Long id){
      return chiTietDonHangRepository.findByDonHang(id);
  }
  public List<ChiTietDatSan> chiTietDatSan(Long id){
    return chiTietDatSanRepository.findByDonHang(id);
  }
  // xu ly don hang
  public int doanhThu(){
      return donHangRepository.doanhThu("Đã thanh toán");
  }

  public String capNhatTrangThai(Long id){
    DonHang donHang = donHangRepository.findById(id).orElse(null);
    donHang.setTrangThai("Đã thanh toán");
    donHangRepository.save(donHang);
    return "Cập nhật đơn hàng thành công";
  }
}
