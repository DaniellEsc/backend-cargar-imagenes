package com.edisson.images.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Imagenes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "BYTEA")
    private byte[] datos;

    public Imagenes() {
    }

    public Imagenes(Long id, String nombre, byte[] datos) {
        this.id = id;
        this.nombre = nombre;
        this.datos = datos;
    }
}
