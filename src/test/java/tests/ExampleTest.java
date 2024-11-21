package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ExampleTest {

    @Test
    @DisplayName("Пример теста для проверки Google")
    public void googleSearchTest() {
        step("Открываем Google", () -> {
            open("https://www.google.com");
        });
        step("Вводим запрос 'Selenide' и нажимаем Enter", () -> {
            $("[name='q']").setValue("Selenide").pressEnter();
        });
        step("Проверяем, что заголовок результата содержит 'Selenide'", () -> {
            $("h3").shouldBe(visible).shouldHave(text("Selenide"));
        });
    }

    @Step("Шаг: {description}")
    private void step(String description, Runnable code) {
        code.run();
    }

    @Test
    @DisplayName("Тест для проверки YouTube")
    @io.qameta.allure.Description("Проверяем, что открывается главная страница YouTube")
    public void youtubeTest() {
        step("Открываем YouTube", () -> {
            open("https://www.youtube.com");
        });
        step("Проверяем, что страница загрузилась", () -> {
            $("title").shouldHave(text("YouTube"));
        });
    }
}
