package com.ipt.dashboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="proyectos")
public class Proyecto {
    @Id
    @Column(name="regionID")
    private int regionid;

}
