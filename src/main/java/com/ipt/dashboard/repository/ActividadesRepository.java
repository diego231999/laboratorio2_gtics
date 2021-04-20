package com.ipt.dashboard.repository;

import com.ipt.dashboard.entity.Actividades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadesRepository extends JpaRepository<Actividades, Integer> {

    List<Actividades> findByIdproyecto(int idProyecto);
    @Query(value="select sum(peso) from actividades where estado = 1 and idproyecto=?1;", nativeQuery = true)
    double sumaPesos(int idproyecto);

    @Query(value="select sum(peso) from actividades where estado = 1 and idproyecto=?1;", nativeQuery = true)
    double sumaPesosTotal(int idproyecto);
}
