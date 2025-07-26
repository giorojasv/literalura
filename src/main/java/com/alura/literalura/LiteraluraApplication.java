package com.alura.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    @Autowired
    private CatalogoService catalogo;

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("""
                    \n--- Menú LiterAlura ---
                    1. Buscar libro por título
                    2. Listar todos los libros
                    3. Listar autores
                    4. Listar autores vivos en un año
                    5. Listar libros por idioma
                    0. Salir
                    ------------------------
                    """);

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese título: ");
                    catalogo.buscarYGuardarLibro(sc.nextLine());
                }
                case 2 -> catalogo.listarLibros();
                case 3 -> catalogo.listarAutores();
                case 4 -> {
                    System.out.print("Año: ");
                    catalogo.autoresVivosEn(Integer.parseInt(sc.nextLine()));
                }
                case 5 -> {
                    System.out.print("Idioma (ES, EN, PT...): ");
                    catalogo.listarPorIdioma(sc.nextLine());
                }
                case 0 -> System.out.println("¡Hasta pronto!");
                default -> System.out.println("Opción inválida.");
            }
        }
    }
}