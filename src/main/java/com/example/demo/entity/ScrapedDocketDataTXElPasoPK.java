package com.example.demo.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.io.Serializable;

public class ScrapedDocketDataTXElPasoPK implements Serializable {

    static final long serialVersionUID = 1L;

    public ScrapedDocketDataTXElPasoPK() {
    }

    @Column(name = "DOCKET_NUMBER", length = 30, nullable = false)
    @Basic(fetch = FetchType.EAGER)
    @Id
    private String docketNumber;

    @Column(name = "FULL_CASE_TYPE", length = 100, nullable = false)
    @Basic(fetch = FetchType.EAGER)
    @Id
    private String fullCaseType;

    @Column(name = "OFFENSE", length = 1000, nullable = false)
    @Basic(fetch = FetchType.EAGER)
    @Id
    private String offense;

    public String getDocketNumber() {
        return docketNumber;
    }

    public void setDocketNumber(String docketNumber) {
        this.docketNumber = docketNumber;
    }

    public String getFullCaseType() {
        return fullCaseType;
    }

    public void setFullCaseType(String fullCaseType) {
        this.fullCaseType = fullCaseType;
    }

    public String getOffense() {
        return offense;
    }

    public void setOffense(String offense) {
        this.offense = offense;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScrapedDocketDataTXElPasoPK that = (ScrapedDocketDataTXElPasoPK) o;

        if (!docketNumber.equals(that.docketNumber)) return false;
        if (!fullCaseType.equals(that.fullCaseType)) return false;
        return offense.equals(that.offense);
    }

    @Override
    public int hashCode() {
        int result = docketNumber.hashCode();
        result = 31 * result + fullCaseType.hashCode();
        result = 31 * result + offense.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ScrapedDocketDataTXElPasoPK{" +
                "docketNumber='" + docketNumber + '\'' +
                ", fullCaseType='" + fullCaseType + '\'' +
                ", offense='" + offense + '\'' +
                '}';
    }
}
