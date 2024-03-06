package com.edisson.images.dto;

import com.edisson.images.model.Imagenes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagenRepository extends JpaRepository<Imagenes, Long> {
}
