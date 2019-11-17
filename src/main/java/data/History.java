package data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "id",
        "balance",
        "accountnumber",
        "targetaccount",
        "amount",
        "transactiontime"
})
public class History {
    public History() {
        id = 0;
        balance = 0;
        accountnumber = 0;
        targetaccount = 0;
        amount = 0;
        transactiontime = "";
    }

    @XmlElement(name = "id", required = true)
    private int id;
    @XmlElement(name = "balance", required = true)
    private int balance;
    @XmlElement(name = "accountnumber", required = true)
    private int accountnumber;
    @XmlElement(name = "targetaccount", required = true)
    private int targetaccount;
    @XmlElement(name = "amount", required = true)
    private int amount;
    @XmlElement(name = "transactiontime", required = true)
    private String transactiontime;

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountnumber;
    }

    public int getTargetAccount() {
        return targetaccount;
    }

    public int getAmount() {
        return amount;
    }

    public String getTransactionTime() {
        return transactiontime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setAccountNumber(int accountnumber) {
        this.accountnumber = accountnumber;
    }

    public void setTargetAccount(int targetaccount) {
        this.targetaccount = targetaccount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTransactionTime(String transactiontime) {
        this.transactiontime = transactiontime;
    }
}
