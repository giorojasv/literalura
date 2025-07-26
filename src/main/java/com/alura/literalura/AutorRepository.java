package com.alura.literalura;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.nacimiento <= :anio AND (a.fallecimiento IS NULL OR a.fallecimiento >= :anio)")
    List<Autor> encontrarVivosEn(Integer anio);
}