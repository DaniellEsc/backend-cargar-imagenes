package com.edisson.images.controller;

import com.edisson.images.model.Imagenes;
import com.edisson.images.services.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/imagenes")
public class ImagenController {

    @Autowired
    private ImagenService imagenService;


    @GetMapping("/all")
    public ResponseEntity<List<Imagenes>> getAllImagenes(){
        List<Imagenes> imagenes = imagenService.getAllImagenes();
        return ResponseEntity.ok(imagenes);
    }

    @PostMapping("/cargar-imagen")
    public ResponseEntity<?> cargarImagen(@RequestParam("file") MultipartFile file) {
        try {
            Imagenes imagen = imagenService.cargarImagen(file);
            return ResponseEntity.ok(imagen);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cargar la imagen");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImagen(@PathVariable Long id){
        Optional<Imagenes> imageOptional = imagenService.getImagenById(id);

        if (imageOptional.isPresent()) {
            Imagenes imagen = imageOptional.get();
            HttpHeaders headers =new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(imagen.getDatos(), headers, HttpStatus.OK);

            
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
