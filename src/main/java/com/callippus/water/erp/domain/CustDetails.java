package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CustDetails.
 */
@Entity
@Table(name = "cust_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CustDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "can", nullable = false)
    private String can;

    @Column(name = "div_code")
    private String divCode;

    @Column(name = "sec_code")
    private String secCode;

    @Column(name = "sec_name")
    private String secName;

    @Column(name = "met_reader_code")
    private String metReaderCode;

    @Column(name = "conn_date")
    private LocalDate connDate;

    @NotNull
    @Column(name = "cons_name", nullable = false)
    private String consName;

    @Column(name = "house_no")
    private String houseNo;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "category")
    private String category;

    @Column(name = "pipe_size")
    private Float pipeSize;

    @Column(name = "board_meter")
    private String boardMeter;

    @Column(name = "sewerage")
    private String sewerage;

    @Column(name = "meter_no")
    private String meterNo;

    @Column(name = "prev_bill_type")
    private String prevBillType;

    @Column(name = "prev_bill_month")
    private LocalDate prevBillMonth;

    @Column(name = "prev_avg_kl")
    private Float prevAvgKl;

    @Column(name = "met_reading_dt")
    private LocalDate metReadingDt;

    @Column(name = "prev_reading")
    private Float prevReading;

    @Column(name = "met_reading_mo")
    private LocalDate metReadingMo;

    @Column(name = "met_avg_kl")
    private Float metAvgKl;

    @Column(name = "arrears")
    private Float arrears;

    @Column(name = "reversal_amt")
    private Float reversalAmt;

    @Column(name = "installment")
    private Float installment;

    @Column(name = "other_charges")
    private Float otherCharges;

    @Column(name = "surcharge")
    private Float surcharge;

    @Column(name = "hrs_surcharge")
    private String hrsSurcharge;

    @Column(name = "res_units")
    private Long resUnits;

    @Column(name = "met_cost_installment")
    private Float metCostInstallment;

    @Column(name = "int_on_arrears")
    private Float intOnArrears;

    @Column(name = "last_pymt_dt")
    private LocalDate lastPymtDt;

    @Column(name = "last_pymt_amt")
    private Float lastPymtAmt;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "cc_flag")
    private String ccFlag;

    @Column(name = "cp_flag")
    private String cpFlag;

    @Column(name = "notice_flag")
    private String noticeFlag;

    @Column(name = "dr_flag")
    private String drFlag;

    @Column(name = "lat")
    private String lat;

    @Column(name = "longi")
    private String longi;

    @Column(name = "meter_fix_date")
    private LocalDate meterFixDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCan() {
        return can;
    }

    public void setCan(String can) {
        this.can = can;
    }

    public String getDivCode() {
        return divCode;
    }

    public void setDivCode(String divCode) {
        this.divCode = divCode;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getSecName() {
        return secName;
    }

    public void setSecName(String secName) {
        this.secName = secName;
    }

    public String getMetReaderCode() {
        return metReaderCode;
    }

    public void setMetReaderCode(String metReaderCode) {
        this.metReaderCode = metReaderCode;
    }

    public LocalDate getConnDate() {
        return connDate;
    }

    public void setConnDate(LocalDate connDate) {
        this.connDate = connDate;
    }

    public String getConsName() {
        return consName;
    }

    public void setConsName(String consName) {
        this.consName = consName;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getPipeSize() {
        return pipeSize;
    }

    public void setPipeSize(Float pipeSize) {
        this.pipeSize = pipeSize;
    }

    public String getBoardMeter() {
        return boardMeter;
    }

    public void setBoardMeter(String boardMeter) {
        this.boardMeter = boardMeter;
    }

    public String getSewerage() {
        return sewerage;
    }

    public void setSewerage(String sewerage) {
        this.sewerage = sewerage;
    }

    public String getMeterNo() {
        return meterNo;
    }

    public void setMeterNo(String meterNo) {
        this.meterNo = meterNo;
    }

    public String getPrevBillType() {
        return prevBillType;
    }

    public void setPrevBillType(String prevBillType) {
        this.prevBillType = prevBillType;
    }

    public LocalDate getPrevBillMonth() {
        return prevBillMonth;
    }

    public void setPrevBillMonth(LocalDate prevBillMonth) {
        this.prevBillMonth = prevBillMonth;
    }

    public Float getPrevAvgKl() {
        return prevAvgKl;
    }

    public void setPrevAvgKl(Float prevAvgKl) {
        this.prevAvgKl = prevAvgKl;
    }

    public LocalDate getMetReadingDt() {
        return metReadingDt;
    }

    public void setMetReadingDt(LocalDate metReadingDt) {
        this.metReadingDt = metReadingDt;
    }

    public Float getPrevReading() {
        return prevReading;
    }

    public void setPrevReading(Float prevReading) {
        this.prevReading = prevReading;
    }

    public LocalDate getMetReadingMo() {
        return metReadingMo;
    }

    public void setMetReadingMo(LocalDate metReadingMo) {
        this.metReadingMo = metReadingMo;
    }

    public Float getMetAvgKl() {
        return metAvgKl;
    }

    public void setMetAvgKl(Float metAvgKl) {
        this.metAvgKl = metAvgKl;
    }

    public Float getArrears() {
        return arrears;
    }

    public void setArrears(Float arrears) {
        this.arrears = arrears;
    }

    public Float getReversalAmt() {
        return reversalAmt;
    }

    public void setReversalAmt(Float reversalAmt) {
        this.reversalAmt = reversalAmt;
    }

    public Float getInstallment() {
        return installment;
    }

    public void setInstallment(Float installment) {
        this.installment = installment;
    }

    public Float getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(Float otherCharges) {
        this.otherCharges = otherCharges;
    }

    public Float getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(Float surcharge) {
        this.surcharge = surcharge;
    }

    public String getHrsSurcharge() {
        return hrsSurcharge;
    }

    public void setHrsSurcharge(String hrsSurcharge) {
        this.hrsSurcharge = hrsSurcharge;
    }

    public Long getResUnits() {
        return resUnits;
    }

    public void setResUnits(Long resUnits) {
        this.resUnits = resUnits;
    }

    public Float getMetCostInstallment() {
        return metCostInstallment;
    }

    public void setMetCostInstallment(Float metCostInstallment) {
        this.metCostInstallment = metCostInstallment;
    }

    public Float getIntOnArrears() {
        return intOnArrears;
    }

    public void setIntOnArrears(Float intOnArrears) {
        this.intOnArrears = intOnArrears;
    }

    public LocalDate getLastPymtDt() {
        return lastPymtDt;
    }

    public void setLastPymtDt(LocalDate lastPymtDt) {
        this.lastPymtDt = lastPymtDt;
    }

    public Float getLastPymtAmt() {
        return lastPymtAmt;
    }

    public void setLastPymtAmt(Float lastPymtAmt) {
        this.lastPymtAmt = lastPymtAmt;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCcFlag() {
        return ccFlag;
    }

    public void setCcFlag(String ccFlag) {
        this.ccFlag = ccFlag;
    }

    public String getCpFlag() {
        return cpFlag;
    }

    public void setCpFlag(String cpFlag) {
        this.cpFlag = cpFlag;
    }

    public String getNoticeFlag() {
        return noticeFlag;
    }

    public void setNoticeFlag(String noticeFlag) {
        this.noticeFlag = noticeFlag;
    }

    public String getDrFlag() {
        return drFlag;
    }

    public void setDrFlag(String drFlag) {
        this.drFlag = drFlag;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public LocalDate getMeterFixDate() {
        return meterFixDate;
    }

    public void setMeterFixDate(LocalDate meterFixDate) {
        this.meterFixDate = meterFixDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustDetails custDetails = (CustDetails) o;
        return Objects.equals(id, custDetails.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CustDetails{" +
            "id=" + id +
            ", can='" + can + "'" +
            ", divCode='" + divCode + "'" +
            ", secCode='" + secCode + "'" +
            ", secName='" + secName + "'" +
            ", metReaderCode='" + metReaderCode + "'" +
            ", connDate='" + connDate + "'" +
            ", consName='" + consName + "'" +
            ", houseNo='" + houseNo + "'" +
            ", address='" + address + "'" +
            ", city='" + city + "'" +
            ", pinCode='" + pinCode + "'" +
            ", category='" + category + "'" +
            ", pipeSize='" + pipeSize + "'" +
            ", boardMeter='" + boardMeter + "'" +
            ", sewerage='" + sewerage + "'" +
            ", meterNo='" + meterNo + "'" +
            ", prevBillType='" + prevBillType + "'" +
            ", prevBillMonth='" + prevBillMonth + "'" +
            ", prevAvgKl='" + prevAvgKl + "'" +
            ", metReadingDt='" + metReadingDt + "'" +
            ", prevReading='" + prevReading + "'" +
            ", metReadingMo='" + metReadingMo + "'" +
            ", metAvgKl='" + metAvgKl + "'" +
            ", arrears='" + arrears + "'" +
            ", reversalAmt='" + reversalAmt + "'" +
            ", installment='" + installment + "'" +
            ", otherCharges='" + otherCharges + "'" +
            ", surcharge='" + surcharge + "'" +
            ", hrsSurcharge='" + hrsSurcharge + "'" +
            ", resUnits='" + resUnits + "'" +
            ", metCostInstallment='" + metCostInstallment + "'" +
            ", intOnArrears='" + intOnArrears + "'" +
            ", lastPymtDt='" + lastPymtDt + "'" +
            ", lastPymtAmt='" + lastPymtAmt + "'" +
            ", mobileNo='" + mobileNo + "'" +
            ", ccFlag='" + ccFlag + "'" +
            ", cpFlag='" + cpFlag + "'" +
            ", noticeFlag='" + noticeFlag + "'" +
            ", drFlag='" + drFlag + "'" +
            ", lat='" + lat + "'" +
            ", longi='" + longi + "'" +
            ", meterFixDate='" + meterFixDate + "'" +
            '}';
    }
}
