package edu.dosw.taller.model.persistence.repository;

import edu.dosw.taller.model.entities.AuthorType;
import edu.dosw.taller.model.entities.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {

    List<Recipe> findByAuthorType(AuthorType type);
    List<Recipe> findBySeason(Integer season);
    List<Recipe> findByIngredientsNameContainingIgnoreCase(String ingredient);
    
}