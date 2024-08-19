package com.project.surferthon_inha.login.user.service;

import com.project.surferthon_inha.login.jwt.service.TokenService;
import com.project.surferthon_inha.login.user.dto.response.UserInformResponseDTO;
import com.project.surferthon_inha.login.user.entity.CustomUserDetails;
import com.project.surferthon_inha.login.user.entity.User;
import com.project.surferthon_inha.login.user.entity.UserType;
import com.project.surferthon_inha.login.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;


    public Long register(User user) {
        if (user.getUserType().equals(UserType.DEFAULT))
            user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            return userRepository.save(user).getId();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("이미 가입되어 있는 사용자입니다.");
        }
    }

    // 함수 네이밍을 위한 wrapper
    public Optional<User> loadUserBySub(String sub) throws UsernameNotFoundException {
        return userRepository.findBySub(sub);
    }

    @Override // 일반 로그인
    public UserDetails loadUserByUsername(String sub) throws UsernameNotFoundException {
        User user = loadUserBySub(sub)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        return new CustomUserDetails(user);
    }

    public UserInformResponseDTO getUserInform(Long id) {
        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return new UserInformResponseDTO(user);
    }

    public void logout(String refreshToken) {
        tokenService.blackListToken(refreshToken);
    }



    public void withdraw(Long id) {
        userRepository.deleteById(id);
    }


}
