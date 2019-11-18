package service;    
import data.BankAccount;

import java.sql.*;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService()
public class Transfer {
    @WebMethod
    public BankAccount TransferResult(Integer senderAcc, Integer receiverAcc, Integer trfAmount) {
        // Processing database
        BankAccount result = new BankAccount();
        try {
            // Getting data from the database
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/db_bank_pro", "root", "");
            
            result.setAccNumber(senderAcc);
            
            Statement st = conn.createStatement();
            String query = "SELECT * FROM virtual_account_tbl WHERE virtual_number = " + receiverAcc + " OR account_number = " + receiverAcc;
            ResultSet rawResult = st.executeQuery(query);

            if (rawResult.next()) {
                result.setReceiverNumber(rawResult.getInt("account_number"));
                result.setStatus(200);
            }
            else {
                result.setStatus(4001);
            }

            Statement st2 = conn.createStatement();
            String query2 = "SELECT * FROM account_tbl WHERE id = " + result.getAccNumber();
            ResultSet rawResult2 = st2.executeQuery(query2);

            if (rawResult2.next()) {
                result.setBalance(rawResult2.getInt("balance"));
                if(result.getBalance() < trfAmount){
                    result.setStatus(4002);
                }
            }
            else {
                result.setStatus(4003);
            }

            if ((result.getStatus() == 200)) {
                Statement st3 = conn.createStatement();
                String query3 = "UPDATE account_tbl SET balance = (select balance from account_tbl where id = " + result.getAccNumber() +") - " + trfAmount + " WHERE id = " + result.getAccNumber();
                ResultSet rawResult3 = st3.executeQuery(query3);
                if (rawResult3.next()) {
                    result.setBalance(result.getBalance() - trfAmount);
                    result.setStatus(2000);
                }
                else {
                    result.setStatus(4004);
                }

                Statement st4 = conn.createStatement();
                String query4 = "UPDATE account_tbl SET balance = (select balance from account_tbl where id = " + result.getReceiverNumber() + ") + " + trfAmount + " WHERE id = " + result.getReceiverNumber();
                int rawResult4 = st4.executeUpdate(query4);
                if (rawResult4 == 1) {
                    result.setStatus(2001);
                }
                else {
                    result.setStatus(4005);
                }

                Statement st5 = conn.createStatement();
                String query5 = "INSERT INTO account_transaction_tbl VALUES(NULL," + result.getAccNumber() + "," + result.getReceiverNumber() + ", 'DEBIT'," + trfAmount + ", DEFAULT)";
                int rawResult5 = st5.executeUpdate(query5);

                Statement st6 = conn.createStatement();
                String query6 = "INSERT INTO account_transaction_tbl VALUES(NULL," + result.getReceiverNumber() + "," + result.getAccNumber() + ", 'KREDIT'," + trfAmount + ", DEFAULT)";
                int rawResult6 = st6.executeUpdate(query6);
                if ((rawResult5 == 1) && (rawResult6 == 1)) {
                    result.setStatus(2002);
                }
                else {
                    result.setStatus(4006);
                }
            }

            rawResult2 = st2.executeQuery(query2);

            if (rawResult2.next()) {
                result.setBalance(rawResult2.getInt("balance"));
                if(result.getBalance() < trfAmount){
                    result.setStatus(4002);
                }
            }
            else {
                result.setStatus(4007);
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return result;
    }
}
