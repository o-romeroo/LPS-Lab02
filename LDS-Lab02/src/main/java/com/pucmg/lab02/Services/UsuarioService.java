package com.pucmg.lab02.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import com.pucmg.lab02.Models.Cliente;
import com.pucmg.lab02.Models.Empresa;
import com.pucmg.lab02.Models.Usuario;
import com.pucmg.lab02.Repositories.UsuarioRepository;
import com.pucmg.lab02.dto.RequiredPermissionDTO;
import com.pucmg.lab02.dto.AuthResponseDTO;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Optional<Usuario> findByUsernameAndPassword(String username, String password) {
        return usuarioRepository.findByLoginAndSenha(username, password);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public ResponseEntity<?> validaUsuarioCliente(Long usuarioId) {
    Usuario usuario = findById(usuarioId);
    RequiredPermissionDTO requiredPermission;

    if (usuario instanceof Cliente) {
        requiredPermission = new RequiredPermissionDTO(usuario.getId(), "CLIENTE");
        return ResponseEntity.ok(requiredPermission);
    } else {
        return ResponseEntity.status(401).body(new AuthResponseDTO(false, "Invalid credentials"));
    }
}


    public ResponseEntity<?> validaUsuarioEmpresa(Long usuarioId) {
    Usuario usuario = findById(usuarioId);
    RequiredPermissionDTO requiredPermission;

    if (usuario instanceof Empresa) {
        requiredPermission = new RequiredPermissionDTO(usuario.getId(), "EMPRESA");
        return ResponseEntity.ok(requiredPermission);
    } else {
        return ResponseEntity.status(401).body(new AuthResponseDTO(false, "Invalid credentials"));
    }
}

}
