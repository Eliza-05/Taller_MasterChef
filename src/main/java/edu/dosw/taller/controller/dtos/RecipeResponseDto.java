package edu.dosw.taller.controller.dtos;

import lombok.Data;
import java.time.Instant;
import java.util.List;

@Data
public class RecipeResponseDto {
    private String id;
    private String title;
    private List<IngredientDto> ingredients;
    private List<StepDto> steps;
    private AuthorDto author;
    private Integer season;
    private Instant createdAt;
    private Instant updatedAt;
}