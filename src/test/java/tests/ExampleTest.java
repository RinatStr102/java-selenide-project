package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ExampleTest {

    @Test
    @DisplayName("Пример теста для проверки Google")
    public void googleSearchTest() {
        try {
            step("Открываем Google", () -> open("https://www.google.com"));
            step("Вводим запрос 'Selenide' и нажимаем Enter", () -> $("[name='q']").setValue("Selenide").pressEnter());
            step("Проверяем, что заголовок результата содержит 'Selenide'", () -> $("h3").shouldBe(visible).shouldHave(text("Selenide")));
        } finally {
            attachScreenshot(); // Скриншот страницы
            attachPageSource(); // Исходный код страницы
            attachConsoleLogs(); // Логи браузера
        }
    }

    @Test
    @DisplayName("Тест для проверки YouTube")
    @io.qameta.allure.Description("Проверяем, что открывается главная страница YouTube")
    public void youtubeTest() {
        try {
            step("Открываем YouTube", () -> open("https://www.youtube.com"));
            step("Проверяем, что страница загрузилась", () -> $("title").shouldHave(text("YouTube")));
        } finally {
            attachScreenshot();
            attachPageSource();
            attachConsoleLogs();
        }
    }

    @Step("Шаг: {description}")
    private void step(String description, Runnable code) {
        code.run();
    }

    // Вложение: Скриншот
    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreenshot() {
        return Selenide.screenshot(OutputType.BYTES);
    }

    // Вложение: Исходный код страницы
    @Attachment(value = "Page Source", type = "text/html")
    public String attachPageSource() {
        return WebDriverRunner.getWebDriver().getPageSource();
    }

    // Вложение: Логи браузера
    @Attachment(value = "Console Logs", type = "text/plain")
    public String attachConsoleLogs() {
        return String.join("\n", Selenide.getWebDriverLogs("browser"));
    }
}
