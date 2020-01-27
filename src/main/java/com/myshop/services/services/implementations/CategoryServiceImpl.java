package com.myshop.services.services.implementations;

import com.myshop.data.entities.Category;
import com.myshop.data.repositories.CategoryRepository;
import com.myshop.services.models.CategoryServiceModel;
import com.myshop.services.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(CategoryServiceModel model) {
        this.categoryRepository.saveAndFlush(this.modelMapper.map(model, Category.class));
    }

    @Override
    public void delete(CategoryServiceModel model) {

    }

    @Override
    public List<CategoryServiceModel> getAll() {
        return this.categoryRepository.findAll().stream().map(c -> this.modelMapper.map(c, CategoryServiceModel.class)).collect(Collectors.toList());
    }


    public boolean isCategoryFree(String name) {
        Category category = this.categoryRepository.findByName(name);
        return category == null;
    }
}
