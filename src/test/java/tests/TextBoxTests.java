package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class TextBoxTests extends TestBase{

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void fillFormTest() {

        textBoxPage.openPage()
                .setName("Alex")
                .setEmail("alex@morozov.com")
                .setCurrentAddress("Some street 1 ")
                .setPermanentAddress("Another street 1 ")
                .clickSubmitBtn();

        textBoxPage.checkResult("#name", "Alex")
                        .checkResult("#email", "alex@morozov.com")
                        .checkResult("#currentAddress", "Some street 1 ")
                        .checkResult("#permanentAddress", "Another street 1 ");

    }
}
