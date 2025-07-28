package com.demoqa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBookBodyModel {

    private String userId;
    private List<IsbnItem> collectionOfIsbns;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IsbnItem {
        private String isbn;
    }
}
