package service;

import data.History;
import data.VirtualAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService()
public class TransactionHistory {
    @WebMethod
    public History[] accountHistory(Integer account) {

        // Processing database
        Collection<History> result = new ArrayList<History>();
        try {
            // Getting data from the database
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/db_bank_pro", "root", "");

            Statement st = conn.createStatement();
            String query = "SELECT * FROM account_transaction_tbl WHERE transaction_type = \"DEBIT\" AND (account_transaction_tbl.account_number = " 
                            + account + " OR account_transaction_tbl.target_account = " 
                            + account + ")";
            String query1 = "SELECT transaction_id, account_transaction_tbl.account_number, target_account, amount, transaction_time " 
                            + "FROM account_transaction_tbl JOIN virtual_account_tbl ON account_transaction_tbl.target_account = virtual_account_tbl.virtual_number WHERE virtual_account_tbl.account_number = " 
                            + account + " AND transaction_type = \"DEBIT\"";
            ResultSet rawResult = st.executeQuery(query);
            ResultSet rawResult1 = st.executeQuery(query1);
            while (rawResult.next()) {
                History temp = new History();
                temp.setAccountNumber(rawResult.getInt("account_number"));
                temp.setTargetAccount(rawResult.getInt("target_account"));
                temp.setAmount(rawResult.getInt("amount"));
                temp.setTransactionTime(rawResult.getString("transaction_time"));
                result.add(temp);
            }
            while (rawResult1.next()) {
                History temp = new History();
                temp.setAccountNumber(rawResult1.getInt("account_number"));
                temp.setTargetAccount(rawResult1.getInt("target_account"));
                temp.setAmount(rawResult1.getInt("amount"));
                temp.setTransactionTime(rawResult1.getString("transaction_time"));
                result.add(temp);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return result.toArray(new History[0]);
    }
    @WebMethod
    public VirtualAccount[] getVirtualAccount() {
        // Processing database
        Collection<VirtualAccount> result = new ArrayList<VirtualAccount>();
        try {
            // Getting data from the database
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/db_bank_pro", "root", "");

            Statement st = conn.createStatement();
            String query = "SELECT * FROM virtual_account_tbl";
            ResultSet rawResult = st.executeQuery(query);

            while (rawResult.next()) {
                VirtualAccount temp = new VirtualAccount();
                temp.setAccountNumber(rawResult.getInt("account_number"));
                temp.setVirtualNumber(rawResult.getInt("virtual_number"));
                result.add(temp);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return result.toArray(new VirtualAccount[0]);
    }
    @WebMethod
    public boolean checkCredit(Integer account, Integer amount, String start, String end) {
        // Processing database
        try {
            // Getting data from the database
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/db_bank_pro", "root", "");

            Statement st = conn.createStatement();
            String query = "SELECT * FROM account_transaction_tbl WHERE amount = " 
                            + amount + " AND transaction_time BETWEEN " 
                            + start + " AND " + end + " AND ((account_number = " 
                            + account + " AND transaction_type = \"KREDIT\") OR (target_account = "
                            + account + " AND transaction_type = \"DEBIT\"))";
            ResultSet rawResult = st.executeQuery(query);
            return rawResult.next();
            
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return false;
    }
}