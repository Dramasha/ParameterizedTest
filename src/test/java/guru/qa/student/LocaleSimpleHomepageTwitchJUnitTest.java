package guru.qa.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class LocaleSimpleHomepageTwitchJUnitTest extends BaseTest {

    @BeforeEach
    void openBaseUrl() {
    open("https://www.twitch.tv/directory");
    }

    static Stream<Arguments> twitchSiteTest() {
        return Stream.of(
                Arguments.of(Locale.ENGLISH, List.of("Categories", "Live Channels")),
                Arguments.of(Locale.RUSSIAN, List.of("Категории", "Активные каналы"))
        );
    }

    @MethodSource("twitchSiteTest")
    @ParameterizedTest
    void twitchSiteTest(Locale locale, List<String> expectedButtons){
        $("button[data-a-target='user-menu-toggle']").click();
        $("button[data-a-target='language-dropdown-link']").click();
        $(".user-menu-language-list").$(byText(locale.getLang())).click();
        $$(".ScTabsLayout-sc-17qqzr5-0 li").filter(visible).shouldHave(texts(expectedButtons));
    }
}