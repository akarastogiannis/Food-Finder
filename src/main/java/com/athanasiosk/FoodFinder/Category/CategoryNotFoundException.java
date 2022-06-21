package com.athanasiosk.FoodFinder.Category;

class CategoryNotFoundException  extends RuntimeException{

    CategoryNotFoundException(Long id) {
        super("Could not find the Category with id  " + id);
    }
}
