package org.example.modelo;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public @Data class model implements Serializable {
    private String nombre;
    private String autor;
    private String sinopsis;
    private String editorial;
    private String genero;
    private int idlibro;
    private double precio;
    private String idioma;
    private String Formato;


}



