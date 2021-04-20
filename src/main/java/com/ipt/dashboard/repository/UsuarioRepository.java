package com.ipt.dashboard.repository;

import com.ipt.dashboard.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    List<Usuario> findAllByIdarea(int areaid);

}
