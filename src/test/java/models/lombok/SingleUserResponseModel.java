package models.lombok;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SingleUserResponseModel {

    private Data data;
    private Support support;

    @lombok.Data
    public static class Data {
        private Integer id;
        private String email;

        @JsonProperty("first_name")
        private String firstName;

        @JsonProperty("last_name")
        private String lastName;
        private String avatar;
    }

    @lombok.Data
    public static class Support {
        private String url;
        private String text;
    }
}
//
//    private UserData data;
//    private Support support;
//}
//
//@Data
//public class UserData {
//    private int id;
//    private String email;
//    private String first_name;
//    private String last_name;
//    private String avatar;
//}
//
//@Data
//public class Support {
//    private String url;
//    private String text;
//}
