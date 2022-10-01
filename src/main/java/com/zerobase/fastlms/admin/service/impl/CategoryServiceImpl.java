package com.zerobase.fastlms.admin.service.impl;

import com.zerobase.fastlms.admin.dto.CategoryDto;
import com.zerobase.fastlms.admin.entity.Category;
import com.zerobase.fastlms.admin.model.CategoryInput;
import com.zerobase.fastlms.admin.repository.CategoryRepository;
import com.zerobase.fastlms.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private Sort getSortBySortValueDesc() {
        return Sort.by(Sort.Direction.DESC, "sortValue");
    }

    @Override
    public List<CategoryDto> list() {
        List<Category> categories = categoryRepository.findAll(getSortBySortValueDesc());

        return CategoryDto.of(categories);
    }

    @Override
    public boolean add(String categoryName) {

        Category category = Category.builder()
                .categoryName(categoryName)
                .usingYn(true)
                .sortValue(0)
                .build();
        categoryRepository.save(category);

        return true;
    }

    @Override
    public boolean update(CategoryInput input) {

        Optional<Category> optionalCategory = categoryRepository.findById(input.getId());
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();

            category.setCategoryName(input.getCategoryName());
            category.setSortValue(input.getSortValue());
            category.setUsingYn(input.isUsingYn());
            categoryRepository.save(category);
        }

        return true;
    }

    @Override
    public boolean delete(long id) {

        categoryRepository.deleteById(id);

        return true;
    }
}
