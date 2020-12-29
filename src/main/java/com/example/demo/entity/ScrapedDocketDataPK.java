package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;

import javax.xml.bind.annotation.XmlElement;

public class ScrapedDocketDataPK implements Serializable {
    private static final long serialVersionUID = 1L;

    public ScrapedDocketDataPK() {
    }

    @Column(name = "LEGACY_ID", length = 30, nullable = false)
    @Basic(fetch = FetchType.EAGER)
    @Id
    public String legacyId;

    @Column(name = "WESTLAW_CLUSTER_NAME", length = 30, nullable = false)
    @Basic(fetch = FetchType.EAGER)
    @Id
    public String westlawClusterName;

    @Column(name = "DOCKET_NUMBER", length = 30, nullable = false)
    @Basic(fetch = FetchType.EAGER)
    @Id
    public String docketNumber;

    @Column(name = "SUBDIVISION_NAME", length = 100)
    @Basic(fetch = FetchType.EAGER)
    @XmlElement
    String subdivisionName;

    @Column(name = "VENDOR", nullable = false)
    @Basic(fetch = FetchType.EAGER)
    @XmlElement
    Long vendor;

    public void setWestlawClusterName(String westlawClusterName) {
        this.westlawClusterName = westlawClusterName;
    }

    public String getWestlawClusterName() {
        return this.westlawClusterName;
    }

    public void setDocketNumber(String docketNumber) {
        this.docketNumber = docketNumber;
    }

    public String getDocketNumber() {
        return this.docketNumber;
    }

    public void setLegacyId(String legacyId) {
        this.legacyId = legacyId;
    }

    public String getLegacyId() {
        return this.legacyId;
    }

    public String getSubdivisionName() {
        return subdivisionName;
    }

    public void setSubdivisionName(String subdivisionName) {
        this.subdivisionName = subdivisionName;
    }

    public Long getVendor() {
        return vendor;
    }

    public void setVendor(Long vendor) {
        this.vendor = vendor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((docketNumber == null) ? 0 : docketNumber.hashCode());
        result = prime * result + ((subdivisionName == null) ? 0 : subdivisionName.hashCode());
        result = prime * result + ((vendor == null) ? 0 : vendor.hashCode());
        result = prime * result + ((westlawClusterName == null) ? 0 : westlawClusterName.hashCode());
        result = prime * result + ((legacyId == null) ? 0 : legacyId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ScrapedDocketDataPK other = (ScrapedDocketDataPK) obj;
        if (docketNumber == null) {
            if (other.docketNumber != null)
                return false;
        } else if (!docketNumber.equals(other.docketNumber))
            return false;
        if (subdivisionName == null) {
            if (other.subdivisionName != null)
                return false;
        } else if (!subdivisionName.equals(other.subdivisionName))
            return false;
        if (vendor == null) {
            if (other.vendor != null)
                return false;
        } else if (!vendor.equals(other.vendor))
            return false;
        if (westlawClusterName == null) {
            if (other.westlawClusterName != null)
                return false;
        } else if (!westlawClusterName.equals(other.westlawClusterName))
            return false;
        if (legacyId == null) {
            return other.legacyId == null;
        } else return legacyId.equals(other.legacyId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ScrapedDocketDataPK");
        sb.append(" westlawClusterName: ").append(getWestlawClusterName());
        sb.append(" docketNumber: ").append(getDocketNumber());
        sb.append(" subdivisionName: ").append(getSubdivisionName());
        sb.append(" Vendor: ").append(getVendor());
        sb.append(" legacyId: ").append(getVendor());
        return sb.toString();
    }
}

