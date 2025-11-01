package edu.dosw.taller.model.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un paso dentro del proceso de preparación de la receta.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step {

    @Min(value = 1, message = "El número de paso debe ser mayor o igual a 1")
    private int order;

    @NotBlank(message = "La descripción del paso no puede estar vacía")
    private String description;
}

