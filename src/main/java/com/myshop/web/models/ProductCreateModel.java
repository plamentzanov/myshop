package com.myshop.web.models;

import com.myshop.data.entities.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductCreateModel {

    @Size(min = 3, max = 35)
    private String name;

    @NotNull
    private BigDecimal price;

    private String description;

    @NotNull
    private String categoryName;

    @NotNull
    private MultipartFile image;

}
