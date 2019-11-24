package data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "status",
        "id",
        "name",
        "balance"
})
public class Account {
    public Account() {
        status = 400;
        id = 0;
        name = "";
        balance = 0;
    }

    @XmlElement(name = "status", required = true)
    private int status;
    @XmlElement(name = "id", required = true)
    private int id;
    @XmlElement(name = "name", required = true)
    private String name;
    @XmlElement(name = "balance", required = true)
    private int balance;

    public int getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setStatus(int newStatus) {
        this.status = newStatus;
    }

    public void setBalance(int newBalance) {
        this.balance = newBalance;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public void setName(String newName) {
        this.name = newName;
    }
}
