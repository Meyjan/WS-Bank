package service;
import data.Account;

import java.sql.*;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService()
public class Login {
    @WebMethod
    public Account accountLogin(Integer account) {

        // Processing database
        Account result = new Account();
        try {
            // Getting data from the database
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/db_bank_pro", "root", "");

            Statement st = conn.createStatement();
            String query = "SELECT * FROM account_tbl WHERE id = " + account;
            ResultSet rawResult = st.executeQuery(query);

            if (rawResult.next()) {
                result.setStatus(200);
                result.setId(rawResult.getInt("id"));
                result.setName(rawResult.getString("customer_name"));
                result.setBalance(rawResult.getInt("balance"));
            } else {
                result.setStatus(400);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return result;
    }
}
