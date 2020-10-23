package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import javax.security.auth.kerberos.KerberosKey;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public void errorNotificationCreate() {
        errorNotification.shouldBe(visible);
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        $("[data-test-id=login] input").setValue(info.getLogin());
        $("[data-test-id=password] input").setValue(info.getPassword());
        $("[data-test-id=action-login]").click();
        return new VerificationPage();
    }

    public void searchErrorMessage() {
        $("[data-test-id='error-notification']").waitUntil(visible, 10000).
                shouldHave(text("Система заблокирована"));
    }

    public void cleanField(){
        $("[data-test-id=login] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=password] input").doubleClick().sendKeys(Keys.BACK_SPACE);
    }
}
