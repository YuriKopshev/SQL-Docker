package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class LoginPageTest {

    @AfterAll
    static void cleanBase()  {
        DataHelper.cleanDataBase();

    }

    @Test
    void shouldLoginWithSmsCode() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCode();
        val dashboardPage = verificationPage.validVerify(verificationCode.getCode());
        dashboardPage.dashboardPage();
    }

    @Test
    void loginWithWrongPassword() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfoWithWrongPassword();
        loginPage.validLogin(authInfo);
        loginPage.errorNotificationCreate();
    }

    @Test
    void loginFourTimesWithWrongPassword()  {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfoWithWrongPassword();
        loginPage.validLogin(authInfo);
        loginPage.errorNotificationCreate();
        loginPage.cleanField();

        loginPage.validLogin(authInfo);
        loginPage.errorNotificationCreate();
        loginPage.cleanField();

        loginPage.validLogin(authInfo);
        loginPage.errorNotificationCreate();
        loginPage.cleanField();

        loginPage.validLogin(authInfo);
        loginPage.searchErrorMessage();
    }
}
