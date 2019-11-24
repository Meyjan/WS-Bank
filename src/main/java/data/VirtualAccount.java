package data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "accountnumber",
        "virtualnumber"
})
public class VirtualAccount {
    public VirtualAccount() {
        accountnumber = 0;
        virtualnumber = 0;
    }

    @XmlElement(name = "accountnumber", required = true)
    private int accountnumber;
    @XmlElement(name = "virtualnumber", required = true)
    private int virtualnumber;

    public int getAccountNumber() {
        return accountnumber;
    }

    public int getVirtualNumber() {
        return virtualnumber;
    }

    public void setAccountNumber(int newAccountnumber) {
        this.accountnumber = newAccountnumber;
    }

    public void setVirtualNumber(int newVirtualnumber) {
        this.virtualnumber = newVirtualnumber;
    }
}
