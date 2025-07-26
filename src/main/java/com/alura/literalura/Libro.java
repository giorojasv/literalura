package com.alura.literalura;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private Integer descargas;

    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;
}