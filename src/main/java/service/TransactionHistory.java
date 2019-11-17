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
    public History[] AccountHistory(final Integer account) {
        // Processing database
        Collection<History> result = new ArrayList<History>();
        try {
            // Getting data from the database
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/db_bank_pro", "root", "");

            Statement st = conn.createStatement();
            String query = "SELECT * FROM account_tbl JOIN account_transaction_tbl WHERE account_tbl.id = " 
                            + account +  " AND (account_transaction_tbl.account_number = " 
                            + account + " OR account_transaction_tbl.target_account = " 
                            + account + " ) ORDER BY account_transaction_tbl.transaction_time";
            String query1 = "SELECT id, customer_name, balance, transaction_id, account_transaction_tbl.account_number, target_account, amount, transaction_time " +
                            "FROM account_tbl JOIN account_transaction_tbl JOIN virtual_accoutn_tbl ON account_transaction_tbl.target_account = virtual_accoutn_tbl.virtual_number WHERE virtual_accoutn_tbl.account_number = " 
                            + account + " AND account_tbl.id = " + account + " ORDER BY account_transaction_tbl.transaction_time";
            ResultSet rawResult = st.executeQuery(query);
            ResultSet rawResult1 = st.executeQuery(query1);

            while(rawResult.next()) {
                History temp = new History();
                temp.setId(rawResult.getInt("id"));   
                temp.setBalance(rawResult.getInt("balance"));
                temp.setAccountNumber(rawResult.getInt("account_number"));
                temp.setTargetAccount(rawResult.getInt("target_account"));
                temp.setAmount(rawResult.getInt("amount"));
                temp.setTransactionTime(rawResult.getString("transaction_time"));
                result.add(temp);
            };
            while(rawResult1.next()) {
                History temp = new History();
                temp.setId(rawResult1.getInt("id"));   
                temp.setBalance(rawResult1.getInt("balance"));
                temp.setAccountNumber(rawResult1.getInt("account_number"));
                temp.setTargetAccount(rawResult1.getInt("target_account"));
                temp.setAmount(rawResult1.getInt("amount"));
                temp.setTransactionTime(rawResult1.getString("transaction_time"));
                result.add(temp);
            };
        } 
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return result.toArray(new History[0]);
    }
    @WebMethod
    public VirtualAccount[] GetVirtualAccount() {
        // Processing database
        Collection<VirtualAccount> result = new ArrayList<VirtualAccount>();
        try {
            // Getting data from the database
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/db_bank_pro", "root", "");

            Statement st = conn.createStatement();
            String query = "SELECT * FROM virtual_accoutn_tbl";
            ResultSet rawResult = st.executeQuery(query);

            while(rawResult.next()) {
                VirtualAccount temp = new VirtualAccount();
                temp.setAccountNumber(rawResult.getInt("account_number"));
                temp.setVirtualNumber(rawResult.getInt("virtual_number"));
                result.add(temp);
            };
        } 
        catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return result.toArray(new VirtualAccount[0]);
    }
}