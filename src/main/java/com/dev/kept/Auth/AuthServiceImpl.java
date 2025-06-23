package com.dev.kept.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.kept.Beans.User;
import com.dev.kept.Config.CustomUserDetails;
import com.dev.kept.Config.JwtUtils;
import com.dev.kept.Repository.UserRepository;
import com.dev.kept.dto.AuthResponseDto;
import com.dev.kept.dto.LoginRequestDto;
import com.dev.kept.dto.RegisterRequestDto;


@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public ResponseEntity<?> login(LoginRequestDto request) {

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
            )
        );
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String token = jwtUtils.generateToken(userDetails);

        AuthResponseDto response = new AuthResponseDto(token, userDetails.getUsername(), userDetails.getUsername());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> register(RegisterRequestDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email already in use.");
        }

        User user = new User();

        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        UserDetails userDetails = new CustomUserDetails(user);

        String token = jwtUtils.generateToken(userDetails);

        // AuthResponseDto response = new AuthResponseDto(token, user.getUsername(), user.getEmail());

        return ResponseEntity.ok(token);

    }

}
