package edu.dosw.taller.model.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un ingrediente usado en una receta.
 * Puede incluir la cantidad o unidad si es necesario.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @NotBlank(message = "El nombre del ingrediente es obligatorio")
    
    private String name;

    private String quantity;

    private String unit;
}
