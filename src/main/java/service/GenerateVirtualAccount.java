package service;

import java.sql.*;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService()
public class GenerateVirtualAccount {
    @WebMethod
    public int GenerateVA(Integer account) {
        // Processing database
        try {
            // Getting data from the database
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/db_bank_pro", "root", "");
            Statement st = conn.createStatement();
            String query = "SELECT account_number, max(virtual_number) AS maxva FROM virtual_account_tbl WHERE account_number = "
                    + account;
            ResultSet rawResult = st.executeQuery(query);
            int an = -999;
            int max = -999;
            if (rawResult.next()) {
                an = rawResult.getInt("account_number");
                max = rawResult.getInt("maxva") + 1;
                String query1 = "INSERT INTO virtual_account_tbl VALUES('" + an + "','" + max + "')";
                int rawResult1 = st.executeUpdate(query1);
                if (rawResult1 == 1) {
                    return max;
                } else {
                    return -999;
                }
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return -999;
    }
}
