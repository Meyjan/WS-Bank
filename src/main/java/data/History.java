package data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "accountnumber",
        "targetaccount",
        "amount",
        "transactiontime"
})
public class History {
    public History() {
        accountnumber = 0;
        targetaccount = 0;
        amount = 0;
        transactiontime = "";
    }

    @XmlElement(name = "accountnumber", required = true)
    private int accountnumber;
    @XmlElement(name = "targetaccount", required = true)
    private int targetaccount;
    @XmlElement(name = "amount", required = true)
    private int amount;
    @XmlElement(name = "transactiontime", required = true)
    private String transactiontime;

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
