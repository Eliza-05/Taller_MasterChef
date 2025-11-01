package edu.dosw.taller.model.entities;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.util.List;

/**
 * Entidad principal que representa una receta de MasterChef.
 * Cada receta pertenece a un autor (televidente, participante o chef jurado)
 * y puede tener ingredientes, pasos y una temporada si aplica.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "recipes")
public class Recipe {

    @Id
    private String id;

    @NotBlank(message = "El título de la receta es obligatorio")
    private String title;

    @NotEmpty(message = "Debe haber al menos un ingrediente")
    @Valid
    private List<Ingredient> ingredients;

    @NotEmpty(message = "Debe haber al menos un paso de preparación")
    @Valid
    private List<Step> steps;

    @NotNull(message = "El autor es obligatorio")
    @Valid
    private Author author;

    /**
     * Temporada de MasterChef en la que participa.
     * Solo aplica si el autor es de tipo PARTICIPANT.
     */
    private Integer season;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
