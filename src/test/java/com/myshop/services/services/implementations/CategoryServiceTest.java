package com.myshop.services.services.implementations;

import com.myshop.data.repositories.CategoryRepository;
import com.myshop.services.models.CategoryServiceModel;
import com.myshop.services.services.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class CategoryServiceTest extends BaseTest {

    @MockBean
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @Test
    public void save_shouldBeSuccessful() {
        CategoryServiceModel category = new CategoryServiceModel();
        category.setName("TestCategory");
        category.setImageUrl("www.test.com");

        this.categoryService.save(category);
    }

    @Test
    public void save_whenInputIsInvalid_shouldReturnError() {
        Assertions.assertThrows(Exception.class, () -> this.categoryService.save(null));
    }

}
