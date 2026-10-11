package com.educa.msusuarios.service;

import com.educa.msusuarios.exception.CorreoDuplicadoException;
import com.educa.msusuarios.exception.CredencialesInvalidasException;
import com.educa.msusuarios.exception.RecursoNoEncontradoException;
import com.educa.msusuarios.model.Usuario;
import com.educa.msusuarios.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con id " + id));
    }

    @Transactional
    public Usuario crear(Usuario usuario) {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new CorreoDuplicadoException("Ya existe un usuario con el correo " + usuario.getCorreo());
        }
        usuario.setId(null);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario actualizar(Long id, Usuario datos) {
        Usuario existente = buscarPorId(id);

        boolean cambioCorreo = !existente.getCorreo().equalsIgnoreCase(datos.getCorreo());
        if (cambioCorreo && usuarioRepository.existsByCorreo(datos.getCorreo())) {
            throw new CorreoDuplicadoException("Ya existe un usuario con el correo " + datos.getCorreo());
        }

        existente.setNombre(datos.getNombre());
        existente.setCorreo(datos.getCorreo());
        existente.setRol(datos.getRol());
        existente.setPassword(passwordEncoder.encode(datos.getPassword()));
        return usuarioRepository.save(existente);
    }

    @Transactional
    public void eliminar(Long id) {
        usuarioRepository.delete(buscarPorId(id));
    }

    
    public Usuario autenticar(String correo, String password) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new CredencialesInvalidasException("Credenciales invalidas"));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new CredencialesInvalidasException("Credenciales invalidas");
        }
        return usuario;
    }
}