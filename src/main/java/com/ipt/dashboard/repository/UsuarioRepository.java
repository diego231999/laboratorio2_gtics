package com.ipt.dashboard.repository;

import com.ipt.dashboard.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    List<Usuario> findAllByIdarea(int areaid);

}
