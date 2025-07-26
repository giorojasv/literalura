package com.alura.literalura;

import lombok.Data;
import java.util.List;

@Data
public class GutendexResponse {
    private List<LibroDTO> results;
}