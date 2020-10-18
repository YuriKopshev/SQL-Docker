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



}
