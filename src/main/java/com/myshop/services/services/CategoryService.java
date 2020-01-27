package com.myshop.services.services;


import com.myshop.services.models.CategoryServiceModel;

import java.util.List;

public interface CategoryService {
    void save(CategoryServiceModel model);
    void delete(CategoryServiceModel model);
    List<CategoryServiceModel> getAll();
    boolean isCategoryFree(String name);
}
