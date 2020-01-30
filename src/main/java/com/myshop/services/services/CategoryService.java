package com.myshop.services.services;


import com.myshop.services.models.CategoryServiceModel;

import java.util.List;

public interface CategoryService {
    void save(CategoryServiceModel model);
    void delete(String id);
    void update(CategoryServiceModel model, String id);
    CategoryServiceModel getById(String id);
    List<CategoryServiceModel> getAll();
    boolean isCategoryFree(String name);
}
