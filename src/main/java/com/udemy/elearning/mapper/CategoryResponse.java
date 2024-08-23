package com.udemy.elearning.mapper;
import com.udemy.elearning.models.Category;
import com.udemy.elearning.models.Role;
import com.udemy.elearning.models.User;
import lombok.Data;

import java.util.Set;

@Data
public class CategoryResponse {
    private String name;
    private long id;

    public CategoryResponse(Category category){
        this.setName(category.getName());
        this.setId(category.getId());
    }
}
