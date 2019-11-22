package data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "status",
        "account_number",
        "receiver_number",
        "balance"
})
public class BankAccount {
    public BankAccount() {
        status = 400;
        account_number = 0;
        receiver_number = 0;
        balance = 0;
    }

    @XmlElement(name = "status", required = true)
    private int status;
    @XmlElement(name = "account_number", required = true)
    private int account_number;
    @XmlElement(name = "receiver_number", required = true)
    private int receiver_number;
    @XmlElement(name = "balance", required = true)
    private int balance;

    public int getStatus() {
        return status;
    }

    public int getAccNumber() {
        return account_number;
    }

    public int getReceiverNumber() {
        return receiver_number;
    }

    public int getBalance() {
        return balance;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setAccNumber(int account_number) {
        this.account_number = account_number;
    }

    public void setReceiverNumber(int receiver_number) {
        this.receiver_number = receiver_number;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
