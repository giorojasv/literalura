package com.alura.literalura;

import lombok.Data;
import java.util.List;

@Data
public class LibroDTO {
    private String title;
    private List<AutorDTO> authors;
    private List<String> languages;
    private Integer download_count;
}