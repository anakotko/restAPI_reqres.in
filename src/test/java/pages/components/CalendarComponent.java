package pages.components;

import static com.codeborne.selenide.Condition.text;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class CalendarComponent {

    private final SelenideElement calendarMonthSelect = $(".react-datepicker__month-select");
    private final SelenideElement calendarYearSelect = $(".react-datepicker__year-select");
    
    public void setDate(String day, String month, String year) {
        calendarMonthSelect.selectOption(month);
        calendarYearSelect.selectOption(year);
        $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();
    }
}
