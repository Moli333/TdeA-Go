package com.tdea.go.service;

import com.tdea.go.dto.*;
import com.tdea.go.model.*;
import com.tdea.go.repository.UsuarioRepository;
import com.tdea.go.security.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public Map<String, Object> register(RegisterRequest request) {

        if (usuarioRepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya esta registrado");
        }

        Usuario u = new Usuario();

        u.setNombre(request.getNombre());
        u.setEmail(request.getEmail());
        u.setPassword(
                passwordEncoder.encode(request.getPassword())
        );
        u.setCodigoEstudiante(
                request.getCodigoEstudiante()
        );
        u.setTelefono(request.getTelefono());

        u.setRol(
                request.getRol() != null
                        ? request.getRol()
                        : RolUsuario.PASAJERO
        );

        Usuario guardado = usuarioRepo.save(u);

        String token = jwtUtil.generateToken(
                guardado.getEmail()
        );

        Map<String, Object> res = new HashMap<>();

        res.put("token", token);
        res.put("userId", guardado.getId());
        res.put("nombre", guardado.getNombre());
        res.put("email", guardado.getEmail());
        res.put("rol", guardado.getRol());

        return res;
    }

    public Map<String, Object> login(LoginRequest request) {

        Usuario u = usuarioRepo
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                u.getPassword())) {

            throw new RuntimeException(
                    "Contrasena incorrecta"
            );
        }

        String token = jwtUtil.generateToken(u.getEmail());

        Map<String, Object> res = new HashMap<>();

        res.put("token", token);
        res.put("userId", u.getId());
        res.put("nombre", u.getNombre());
        res.put("email", u.getEmail());
        res.put("rol", u.getRol());

        return res;
    }
}