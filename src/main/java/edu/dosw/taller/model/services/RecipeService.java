package edu.dosw.taller.model.services;

import edu.dosw.taller.controller.dtos.RecipeRequestDto;
import edu.dosw.taller.controller.dtos.RecipeResponseDto;
//import edu.dosw.taller.model.components.util.*;
import java.util.List;

public interface RecipeService {
    RecipeResponseDto createRecipe(RecipeRequestDto dto);
    List<RecipeResponseDto> searchAllRecipes();
    RecipeResponseDto searchRecipeById(String id);
    List<RecipeResponseDto> searchRecipesByAuthorType(String type);
    List<RecipeResponseDto> searchRecipesBySeason(Integer season);
    List<RecipeResponseDto> searchRecipesByIngredient(String ingredient);
    void deleteRecipe(String id);
    RecipeResponseDto updateRecipe(String id, RecipeRequestDto dto);
}