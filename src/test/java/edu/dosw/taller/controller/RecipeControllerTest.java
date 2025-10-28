package edu.dosw.taller.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.dosw.taller.controller.dtos.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test") 
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private RecipeRequestDto recipeRequestDto;

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
    }

   

    @Test
    void testCreateViewerRecipe() throws Exception {
        recipeRequestDto.getAuthor().setType("VIEWER");
        
        mockMvc.perform(post("/api/recetas/televidente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Pasta Carbonara"))
                .andExpect(jsonPath("$.author.type").value("VIEWER"))
                .andExpect(jsonPath("$.author.name").value("Chef Mario"))
                .andExpect(jsonPath("$.ingredients[0].name").value("pasta"));
    }

    @Test
    void testCreateParticipantRecipe() throws Exception {
        recipeRequestDto.getAuthor().setType("PARTICIPANT");
        recipeRequestDto.setSeason(2);
        
        mockMvc.perform(post("/api/recetas/participante")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Pasta Carbonara"))
                .andExpect(jsonPath("$.author.type").value("PARTICIPANT"))
                .andExpect(jsonPath("$.season").value(2))
                .andExpect(jsonPath("$.ingredients[0].name").value("pasta"));
    }

    @Test
    void testCreateChefRecipe() throws Exception {
        recipeRequestDto.getAuthor().setType("CHEF_JUDGE");
        
        mockMvc.perform(post("/api/recetas/chef")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Pasta Carbonara"))
                .andExpect(jsonPath("$.author.type").value("CHEF_JUDGE"))
                .andExpect(jsonPath("$.ingredients[0].name").value("pasta"))
                .andExpect(jsonPath("$.steps[0].description").value("Hervir agua con sal"));
    }



    @Test
    void testGetAllRecipes() throws Exception {
        mockMvc.perform(get("/api/recetas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetRecipeById_NotFound() throws Exception {
        mockMvc.perform(get("/api/recetas/999999"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void testGetParticipantRecipes() throws Exception {
        mockMvc.perform(get("/api/recetas/participantes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetViewerRecipes() throws Exception {
        mockMvc.perform(get("/api/recetas/televidentes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetChefRecipes() throws Exception {
        mockMvc.perform(get("/api/recetas/chefs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetRecipesBySeason() throws Exception {
        mockMvc.perform(get("/api/recetas/temporada/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetRecipesBySeason_InvalidSeason() throws Exception {
        mockMvc.perform(get("/api/recetas/temporada/-1"))
                .andExpect(status().isBadRequest()) 
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void testGetRecipesByIngredient() throws Exception {
        mockMvc.perform(get("/api/recetas/ingrediente/pasta"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetRecipesByIngredient_EmptyResult() throws Exception {
        mockMvc.perform(get("/api/recetas/ingrediente/ingrediente-inexistente"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty()); 
    }

   

    @Test
    void testDeleteRecipe_NotFound() throws Exception {
        mockMvc.perform(delete("/api/recetas/999999"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").exists());
    }

        @Test
        void testDeleteRecipe_Success() throws Exception {
       
        String createResponse = mockMvc.perform(post("/api/recetas/chef")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String id = objectMapper.readTree(createResponse).get("id").asText();

        mockMvc.perform(delete("/api/recetas/" + id))
                .andExpect(status().isNoContent());


        mockMvc.perform(get("/api/recetas/" + id))
                .andExpect(status().isNotFound());
        }



    @Test
    void testUpdateRecipe_NotFound() throws Exception {
        mockMvc.perform(put("/api/recetas/999999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").exists());
    }

        @Test
        void testUpdateRecipe_Success() throws Exception {

        String createResponse = mockMvc.perform(post("/api/recetas/chef")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto))

                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String id = objectMapper.readTree(createResponse).get("id").asText();


        recipeRequestDto.setTitle("Pasta Carbonara Actualizada");

        mockMvc.perform(put("/api/recetas/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto))

                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Pasta Carbonara Actualizada"));
        }



    @Test
    void testCreateRecipe_EmptyTitle() throws Exception {
        recipeRequestDto.setTitle(""); // Título vacío
        
        mockMvc.perform(post("/api/recetas/chef")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateRecipe_NoIngredients() throws Exception {
        recipeRequestDto.setIngredients(Arrays.asList());
        
        mockMvc.perform(post("/api/recetas/chef")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateRecipe_NoSteps() throws Exception {
        recipeRequestDto.setSteps(Arrays.asList()); 
        mockMvc.perform(post("/api/recetas/chef")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeRequestDto)))
                .andExpect(status().isBadRequest());
    }
}