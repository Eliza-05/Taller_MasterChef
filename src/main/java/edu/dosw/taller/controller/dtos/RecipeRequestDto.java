package edu.dosw.taller.controller.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class RecipeRequestDto {
    @NotBlank
    private String title;

    @NotEmpty
    @Valid
    private List<IngredientDto> ingredients;

    @NotEmpty
    @Valid
    private List<StepDto> steps;

    @NotNull
    @Valid
    private AuthorDto author;

   
    private Integer season;
}
