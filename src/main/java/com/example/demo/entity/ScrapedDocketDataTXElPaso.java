package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@IdClass(ScrapedDocketDataTXElPasoPK.class)
@Table(schema = "DOCKETS_PREPROCESSOR", name = "SCRAPED_DOCKET_DATA_TXELPASO")
public class ScrapedDocketDataTXElPaso {

    public ScrapedDocketDataTXElPaso() {
    }

    @Column(name = "DOCKET_NUMBER", length = 30, nullable = false)
    @Id
    private String docketNumber;

    @Column(name = "FULL_CASE_TYPE", length = 100, nullable = false)
    @Id
    private String fullCaseType;

    @Column(name = "OFFENSE", length = 1000, nullable = false)
    @Id
    private String offense;

    @Column(name = "CASE_TYPE", length = 10, nullable = false)
    private String caseType;

    @Column(name = "DOCKET_DATA_FIELDS", nullable = false)
    @Lob
    private String docketDataFields;

    @Column(name = "LAST_SCRAPE_DATE", nullable = false)
    private Timestamp lastScrapeDate;

    @Column(name = "LAST_SOURCE_FILENAME", length = 50, nullable = false)
    private String lastSourceFilename;

    @Column(name = "FILING_DATE", length = 15)
    private String filingDate;

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

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getDocketDataFields() {
        return docketDataFields;
    }

    public void setDocketDataFields(String docketDataFields) {
        this.docketDataFields = docketDataFields;
    }

    public Timestamp getLastScrapeDate() {
        return lastScrapeDate;
    }

    public void setLastScrapeDate(Timestamp lastScrapeDate) {
        this.lastScrapeDate = lastScrapeDate;
    }

    public String getLastSourceFilename() {
        return lastSourceFilename;
    }

    public void setLastSourceFilename(String lastSourceFilename) {
        this.lastSourceFilename = lastSourceFilename;
    }

    public void setFilingDate(String filingDate) {
        this.filingDate = filingDate;
    }

    public String getFilingDate() {
        return filingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScrapedDocketDataTXElPaso that = (ScrapedDocketDataTXElPaso) o;

        if (!docketNumber.equals(that.docketNumber)) return false;
        if (!fullCaseType.equals(that.fullCaseType)) return false;
        if (!offense.equals(that.offense)) return false;
        if (!caseType.equals(that.caseType)) return false;
        if (!docketDataFields.equals(that.docketDataFields)) return false;
        if (!lastScrapeDate.equals(that.lastScrapeDate)) return false;
        if (!filingDate.equals(that.filingDate)) return false;
        return lastSourceFilename.equals(that.lastSourceFilename);
    }

    @Override
    public int hashCode() {
        int result = docketNumber.hashCode();
        result = 31 * result + fullCaseType.hashCode();
        result = 31 * result + offense.hashCode();
        result = 31 * result + caseType.hashCode();
        result = 31 * result + docketDataFields.hashCode();
        result = 31 * result + lastScrapeDate.hashCode();
        result = 31 * result + lastSourceFilename.hashCode();
        result = 31 * result + filingDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ScrapedDocketDataTXElPaso{" +
                "docketNumber='" + docketNumber + '\'' +
                ", fullCaseType='" + fullCaseType + '\'' +
                ", offense='" + offense + '\'' +
                ", caseType='" + caseType + '\'' +
                ", docketDataFields='" + docketDataFields + '\'' +
                ", lastScrapeDate=" + lastScrapeDate +
                ", lastSourceFilename='" + lastSourceFilename + '\'' +
                ", filingDate=" + filingDate +
                '}';
    }
}
