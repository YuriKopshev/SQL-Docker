package ru.netology.data;


import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getAuthInfoWithWrongPassword() {
        return new AuthInfo("vasya", "12345");
    }


    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode(){
        String verificationCode = "";
        val codesSQL = "SELECT * FROM auth_codes ORDER BY created DESC LIMIT 1;";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection("jdbc:mysql://192.168.99.100:3306/app",
                "app", "pass")) {
            val usersCode = runner.query(conn, codesSQL, new BeanHandler<>(User.class));
            verificationCode = usersCode.getCode();
        }
        catch (SQLException e){
            e.printStackTrace();

        }
        return new VerificationCode(verificationCode);
    }

    public static void cleanDataBase(){
        val cleanCards = "DELETE FROM cards";
        val cleanAuthCodes = "DELETE FROM auth_codes";
        val cleanUser = "DELETE FROM users";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection("jdbc:mysql://192.168.99.100:3306/app", "app", "pass")) {
            val cleanCardsUser = runner.execute(conn, cleanCards, new BeanHandler<>(User.class));
            val cleanAuthCodesUser = runner.execute(conn, cleanAuthCodes, new BeanHandler<>(User.class));
            val cleanUserUser = runner.execute(conn, cleanUser, new BeanHandler<>(User.class));
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}
