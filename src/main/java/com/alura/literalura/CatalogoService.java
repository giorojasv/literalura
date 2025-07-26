package com.alura.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CatalogoService {

    @Autowired
    private GutendexService gutendexService;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;

    public void buscarYGuardarLibro(String titulo) {
        if (libroRepository.findByTituloIgnoreCase(titulo).isPresent()) {
            System.out.println("El libro ya está registrado.");
            return;
        }

        Optional<LibroDTO> resultado = gutendexService.buscarLibroPorTitulo(titulo);
        if (resultado.isEmpty()) {
            System.out.println("Libro no encontrado.");
            return;
        }

        LibroDTO dto = resultado.get();
        AutorDTO autorDTO = dto.getAuthors().get(0);

        Autor autor = autorRepository
            .findAll()
            .stream()
            .filter(a -> a.getNombre().equalsIgnoreCase(autorDTO.getName()))
            .findFirst()
            .orElseGet(() -> {
                Autor nuevo = new Autor();
                nuevo.setNombre(autorDTO.getName());
                nuevo.setNacimiento(autorDTO.getBirth_year());
                nuevo.setFallecimiento(autorDTO.getDeath_year());
                return autorRepository.save(nuevo);
            });

        Libro libro = new Libro();
        libro.setTitulo(dto.getTitle());
        libro.setIdioma(dto.getLanguages().get(0));
        libro.setDescargas(dto.getDownload_count());
        libro.setAutor(autor);

        libroRepository.save(libro);
        System.out.println("Libro guardado: " + libro.getTitulo());
    }

    public void listarLibros() {
        libroRepository.findAll().forEach(l -> System.out.println(l.getTitulo()));
    }

    public void listarAutores() {
        autorRepository.findAll().forEach(a -> System.out.println(a.getNombre()));
    }

    public void autoresVivosEn(Integer anio) {
        autorRepository.encontrarVivosEn(anio).forEach(a -> System.out.println(a.getNombre()));
    }

    public void listarPorIdioma(String idioma) {
        libroRepository.findByIdioma(idioma).forEach(l -> System.out.println(l.getTitulo()));
    }
}