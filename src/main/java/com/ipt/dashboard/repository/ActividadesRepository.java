package com.ipt.dashboard.repository;

import com.ipt.dashboard.entity.Actividades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadesRepository extends JpaRepository<Actividades, Integer> {

    List<Actividades> findByIdproyecto(int idProyecto);
}
