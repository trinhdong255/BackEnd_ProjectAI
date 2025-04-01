package com.example.eccommerce_ai.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String phoneNumber;
    @Column(nullable = false)
    private String password; // mã hóa (bcrypt)
    private String avatar;

    @Column(nullable = false)
    private boolean activated = false;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false) //  Liên kết với bảng Role
    private Role role;  //  Không dùng CascadeType.REMOVE để tránh xóa Role

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();




    // ✅ Phương thức của UserDetails (Spring Security)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName())); // Lấy quyền từ Role.name
    }



    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }



    @Override
    public boolean isAccountNonLocked() {
        return true; // Nếu có cơ chế khóa tài khoản thì sửa lại
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Nếu có cơ chế hết hạn mật khẩu thì sửa lại
    }

    @Override
    public boolean isEnabled() {
        return activated; // Nếu chưa kích hoạt thì không cho đăng nhập
    }







}
