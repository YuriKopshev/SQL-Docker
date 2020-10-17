package ru.netology.page;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.data.User;;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.$;


public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public void dashboardPage() {
        heading.shouldBe(Condition.visible);
    }

    public static void cleanDataBase(){
        val cleanCards = "DELETE FROM cards";
        val cleanAuthCodes = "DELETE FROM auth_codes";
        val cleanUser = "DELETE FROM users";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass")) {
            val cleanCardsUser = runner.execute(conn, cleanCards, new BeanHandler<>(User.class));
            val cleanAuthCodesUser = runner.execute(conn, cleanAuthCodes, new BeanHandler<>(User.class));
            val cleanUserUser = runner.execute(conn, cleanUser, new BeanHandler<>(User.class));
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
    }

}
