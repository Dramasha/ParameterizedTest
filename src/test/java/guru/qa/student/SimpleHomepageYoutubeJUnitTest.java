package guru.qa.student;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SimpleHomepageYoutubeJUnitTest extends BaseTest {

    @CsvSource(value = {
            "Подписки    |  Тогда в этом разделе появятся новые видео с каналов, на которые вы подписаны",
            "Библиотека  |  Здесь вы увидите сохраненные видео и те, которые вам понравились",
            "История     |  Чтобы посмотреть историю просмотра, войдите в аккаунт"
    },
            delimiter = '|')

    @ParameterizedTest(name = "После перехода на страницу {0} неавторизованным пользователем выдается сообщение {1}")
    @DisplayName("Переход на соответствующие страницы после клика " +
            "в левой колонке (навигационной панеле) на главной странице")
    void successfulHomePageYoutubeTest(String valueTab, String valueText) {
        open("https://www.youtube.com/");
        $("#guide-renderer").$(byText(valueTab)).click();
        $("ytd-item-section-renderer").
                shouldHave(text(valueText));
    }

    @ValueSource(strings = {"Водка", "Вода"})
    @ParameterizedTest(name = "Поисковая выдача Красное и Белое не пустая для запроса {0}")
    void checkSearchAfterRequest(String searchResult) {
        open("https://krasnoeibeloe.ru/");
        $("#title-search-input").setValue(searchResult).pressEnter();
        $$("[id=page_content] div").shouldHave(CollectionCondition.sizeGreaterThan(0));
    }
}