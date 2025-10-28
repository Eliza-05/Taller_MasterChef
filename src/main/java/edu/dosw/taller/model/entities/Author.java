package edu.dosw.taller.model.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa al autor de una receta: televidente, participante o chef jurado.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @NotBlank(message = "El nombre del autor es obligatorio")
    private String name;

    @NotNull(message = "El tipo de autor es obligatorio")
    private AuthorType type;
}
