package edu.dosw.taller.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.dosw.taller.controller.dtos.*;
import edu.dosw.taller.controller.Exception.*;
import edu.dosw.taller.model.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecipeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test") 
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RecipeService recipeService;

    private RecipeRequestDto recipeRequestDto;
    private RecipeResponseDto recipeResponseDto;

    @BeforeEach
    void setUp() {

        AuthorDto authorDto = new AuthorDto();
        authorDto.setName("Chef Mario");
        authorDto.setType("CHEF_JUDGE");

        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setName("pasta");
        ingredientDto.setQuantity("200 gramos");
        ingredientDto.setUnit("gramos");

        StepDto stepDto = new StepDto();
        stepDto.setOrder(1);
        stepDto.setDescription("Hervir agua con sal");

        recipeRequestDto = new RecipeRequestDto();
        recipeRequestDto.setTitle("Pasta Carbonara");
        recipeRequestDto.setAuthor(authorDto);
        recipeRequestDto.setIngredients(Arrays.asList(ingredientDto));
        recipeRequestDto.setSteps(Arrays.asList(stepDto));


        recipeResponseDto = new RecipeResponseDto();
        recipeResponseDto.setId("507f1f77bcf86cd799439011");
        recipeResponseDto.setTitle("Pasta Carbonara");
        recipeResponseDto.setAuthor(authorDto);
        recipeResponseDto.setIngredients(Arrays.asList(ingredientDto));
        recipeResponseDto.setSteps(Arrays.asList(stepDto));
        recipeResponseDto.setCreatedAt(Instant.now());
        recipeResponseDto.setUpdatedAt(Instant.now());
    }

    @Test
    void testCreateViewerRecipe() throws Exception {

        recipeRequestDto.getAuthor().setType("VIEWER");
        recipeResponseDto.getAuthor().setType("VIEWER");
        when(recipeService.createRecipe(any(RecipeRequestDto.class))).thenReturn(recipeResponseDto);
        

        mockMvc.perform(post("/api/recetas/televidente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Pasta Carbonara"))
                .andExpect(jsonPath("$.author.type").value("VIEWER"))
                .andExpect(jsonPath("$.author.name").value("Chef Mario"))
                .andExpect(jsonPath("$.ingredients[0].name").value("pasta"));

        verify(recipeService).createRecipe(any(RecipeRequestDto.class));
    }

    @Test
    void testCreateParticipantRecipe() throws Exception {

        recipeRequestDto.getAuthor().setType("PARTICIPANT");
        recipeRequestDto.setSeason(2);
        recipeResponseDto.getAuthor().setType("PARTICIPANT");
        recipeResponseDto.setSeason(2);
        when(recipeService.createRecipe(any(RecipeRequestDto.class))).thenReturn(recipeResponseDto);
        

        mockMvc.perform(post("/api/recetas/participante")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Pasta Carbonara"))
                .andExpect(jsonPath("$.author.type").value("PARTICIPANT"))
                .andExpect(jsonPath("$.season").value(2))
                .andExpect(jsonPath("$.ingredients[0].name").value("pasta"));

        verify(recipeService).createRecipe(any(RecipeRequestDto.class));
    }

    @Test
    void testCreateChefRecipe() throws Exception {

        when(recipeService.createRecipe(any(RecipeRequestDto.class))).thenReturn(recipeResponseDto);
        

        mockMvc.perform(post("/api/recetas/chef")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Pasta Carbonara"))
                .andExpect(jsonPath("$.author.type").value("CHEF_JUDGE"))
                .andExpect(jsonPath("$.ingredients[0].name").value("pasta"))
                .andExpect(jsonPath("$.steps[0].description").value("Hervir agua con sal"));

        verify(recipeService).createRecipe(any(RecipeRequestDto.class));
    }

    @Test
    void testGetAllRecipes() throws Exception {

        when(recipeService.searchAllRecipes()).thenReturn(Arrays.asList(recipeResponseDto));
        

        mockMvc.perform(get("/api/recetas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].title").value("Pasta Carbonara"));

        verify(recipeService).searchAllRecipes();
    }

    @Test
    void testGetRecipeById_Success() throws Exception {

        when(recipeService.searchRecipeById("507f1f77bcf86cd799439011")).thenReturn(recipeResponseDto);
        

        mockMvc.perform(get("/api/recetas/507f1f77bcf86cd799439011"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Pasta Carbonara"));

        verify(recipeService).searchRecipeById("507f1f77bcf86cd799439011");
    }

    @Test
    void testGetRecipeById_NotFound() throws Exception {

        when(recipeService.searchRecipeById("999999"))
                .thenThrow(new RecipeNotFoundException("Receta no encontrada con ID: 999999"));
        

        mockMvc.perform(get("/api/recetas/999999"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").exists());

        verify(recipeService).searchRecipeById("999999");
    }

    @Test
    void testGetParticipantRecipes() throws Exception {

        when(recipeService.searchRecipesByAuthorType("PARTICIPANT")).thenReturn(Arrays.asList(recipeResponseDto));
        

        mockMvc.perform(get("/api/recetas/participantes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(recipeService).searchRecipesByAuthorType("PARTICIPANT");
    }

    @Test
    void testGetViewerRecipes() throws Exception {

        when(recipeService.searchRecipesByAuthorType("VIEWER")).thenReturn(Arrays.asList(recipeResponseDto));
        

        mockMvc.perform(get("/api/recetas/televidentes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(recipeService).searchRecipesByAuthorType("VIEWER");
    }

    @Test
    void testGetChefRecipes() throws Exception {

        when(recipeService.searchRecipesByAuthorType("CHEF_JUDGE")).thenReturn(Arrays.asList(recipeResponseDto));
        

        mockMvc.perform(get("/api/recetas/chefs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(recipeService).searchRecipesByAuthorType("CHEF_JUDGE");
    }

    @Test
    void testGetRecipesBySeason() throws Exception {

        when(recipeService.searchRecipesBySeason(1)).thenReturn(Arrays.asList(recipeResponseDto));
        

        mockMvc.perform(get("/api/recetas/temporada/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(recipeService).searchRecipesBySeason(1);
    }

    @Test
    void testGetRecipesBySeason_InvalidSeason() throws Exception {

        when(recipeService.searchRecipesBySeason(-1))
                .thenThrow(new InvalidSeasonException("La temporada debe ser un número positivo"));
        

        mockMvc.perform(get("/api/recetas/temporada/-1"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400));

        verify(recipeService).searchRecipesBySeason(-1);
    }

    @Test
    void testGetRecipesByIngredient() throws Exception {

        when(recipeService.searchRecipesByIngredient("pasta")).thenReturn(Arrays.asList(recipeResponseDto));
        

        mockMvc.perform(get("/api/recetas/ingrediente/pasta"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());

        verify(recipeService).searchRecipesByIngredient("pasta");
    }

    @Test
    void testGetRecipesByIngredient_EmptyResult() throws Exception {

        when(recipeService.searchRecipesByIngredient("ingrediente-inexistente"))
                .thenReturn(Collections.emptyList());
        

        mockMvc.perform(get("/api/recetas/ingrediente/ingrediente-inexistente"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());

        verify(recipeService).searchRecipesByIngredient("ingrediente-inexistente");
    }

    @Test
    void testDeleteRecipe_NotFound() throws Exception {

        doThrow(new RecipeNotFoundException("No se puede eliminar. Receta no encontrada con ID: 999999"))
                .when(recipeService).deleteRecipe("999999");
        

        mockMvc.perform(delete("/api/recetas/999999"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").exists());

        verify(recipeService).deleteRecipe("999999");
    }

    @Test
    void testDeleteRecipe_Success() throws Exception {

        String recipeId = "507f1f77bcf86cd799439011";
        when(recipeService.createRecipe(any(RecipeRequestDto.class))).thenReturn(recipeResponseDto);
        doNothing().when(recipeService).deleteRecipe(recipeId);
        

        mockMvc.perform(delete("/api/recetas/" + recipeId))
                .andExpect(status().isNoContent());

        verify(recipeService).deleteRecipe(recipeId);
    }

    @Test
    void testUpdateRecipe_NotFound() throws Exception {

        when(recipeService.updateRecipe(eq("999999"), any(RecipeRequestDto.class)))
                .thenThrow(new RecipeNotFoundException("Receta no encontrada con ID: 999999"));
        

        mockMvc.perform(put("/api/recetas/999999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").exists());

        verify(recipeService).updateRecipe(eq("999999"), any(RecipeRequestDto.class));
    }

    @Test
    void testUpdateRecipe_Success() throws Exception {

        String recipeId = "507f1f77bcf86cd799439011";
        recipeRequestDto.setTitle("Pasta Carbonara Actualizada");
        
        RecipeResponseDto updatedResponse = new RecipeResponseDto();
        updatedResponse.setId(recipeId);
        updatedResponse.setTitle("Pasta Carbonara Actualizada");
        updatedResponse.setAuthor(recipeRequestDto.getAuthor());
        updatedResponse.setIngredients(recipeRequestDto.getIngredients());
        updatedResponse.setSteps(recipeRequestDto.getSteps());
        
        when(recipeService.updateRecipe(eq(recipeId), any(RecipeRequestDto.class))).thenReturn(updatedResponse);
        

        mockMvc.perform(put("/api/recetas/" + recipeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Pasta Carbonara Actualizada"));

        verify(recipeService).updateRecipe(eq(recipeId), any(RecipeRequestDto.class));
    }

    @Test
    void testCreateRecipe_EmptyTitle() throws Exception {

        recipeRequestDto.setTitle("");
        when(recipeService.createRecipe(any(RecipeRequestDto.class)))
                .thenThrow(new RecipeValidationException("El título de la receta es obligatorio"));
        

        mockMvc.perform(post("/api/recetas/chef")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto)))
                .andExpect(status().isBadRequest());

        verify(recipeService).createRecipe(any(RecipeRequestDto.class));
    }

    @Test
    void testCreateRecipe_NoIngredients() throws Exception {

        recipeRequestDto.setIngredients(Arrays.asList());
        when(recipeService.createRecipe(any(RecipeRequestDto.class)))
                .thenThrow(new RecipeValidationException("La receta debe tener al menos un ingrediente"));
        

        mockMvc.perform(post("/api/recetas/chef")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto)))
                .andExpect(status().isBadRequest());

        verify(recipeService).createRecipe(any(RecipeRequestDto.class));
    }

    @Test
    void testCreateRecipe_NoSteps() throws Exception {

        recipeRequestDto.setSteps(Arrays.asList());
        when(recipeService.createRecipe(any(RecipeRequestDto.class)))
                .thenThrow(new RecipeValidationException("La receta debe tener al menos un paso"));
        

        mockMvc.perform(post("/api/recetas/chef")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto)))
                .andExpect(status().isBadRequest());

        verify(recipeService).createRecipe(any(RecipeRequestDto.class));
    }
}