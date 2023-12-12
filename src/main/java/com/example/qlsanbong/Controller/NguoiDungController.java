package com.example.qlsanbong.Controller;

import com.example.qlsanbong.DTO.DonHangDTO;
import com.example.qlsanbong.DTO.NguoiDungDTO;
import com.example.qlsanbong.Model.SanBong;
import com.example.qlsanbong.Model.SanPham;
import com.example.qlsanbong.Service.NguoiDungService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nguoidung")
public class NguoiDungController {
  @Autowired
  private NguoiDungService nguoiDungService;
  // đăng ký nguời dùng
  @PostMapping("/dangky")
  public String dangkyNguoiDung(@RequestBody NguoiDungDTO nguoiDungDTO){
    nguoiDungService.dangkyNguoiDung(nguoiDungDTO.getSdt(), nguoiDungDTO.getPassword());
    return "Đăng ký thành công";
  }
  //
  @PostMapping("/dangnhap")
  public String dangnhapNguoiDung(@RequestBody NguoiDungDTO nguoiDungDTO){
    int check = nguoiDungService.dangnhapNguoiDung(nguoiDungDTO.getSdt(), nguoiDungDTO.getPassword());
    if(check == 0) return "Số điện thoại đăng nhập không tồn tại";
    else if(check == 2) return "Mật khẩu sai";
    else if(check == 3) return "Đăng nhập thành công với tài khoản admin";
    else return "Đăng nhập thành công";
  }

  @GetMapping("/sanpham")
  public List<SanPham> danhSachSanPham(){
    return nguoiDungService.danhSachSanPham();
  }

  @GetMapping("/sanbong")
  public List<SanBong> danhSachSanBong(){
    return nguoiDungService.danhsachSanBong();
  }

  @PostMapping("/donhang/{id}")
  public String donHangNguoiDung( @PathVariable Long id, @RequestBody DonHangDTO donHangDTO){
    return nguoiDungService.donHangNguoiDung(id, donHangDTO);
  }

}
