package com.demoqa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseModel {

    public String userId, username;
    public List<BookItem> books;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookItem {
        private String isbn;
        private String title;
        private String subTitle;
        private String author;
        private String publish_date;
        private String publisher;
        private int pages;
        private String description;
        private String website;
    }
}
