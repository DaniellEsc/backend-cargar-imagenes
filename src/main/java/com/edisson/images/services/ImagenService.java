package com.edisson.images.services;

import com.edisson.images.dto.ImagenRepository;
import com.edisson.images.model.Imagenes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    public Imagenes cargarImagen(MultipartFile file) throws IOException {
        Imagenes imagen = new Imagenes();
        imagen.setNombre(file.getOriginalFilename());
        imagen.setDatos(file.getBytes());
        return imagenRepository.save(imagen);
    }

    public List<Imagenes> getAllImagenes(){
        return imagenRepository.findAll();
    }

    public Optional<Imagenes> getImagenById(Long id){
         return imagenRepository.findById(id);
    }
}
