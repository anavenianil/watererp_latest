package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A BillFullDetails.
 */
@Entity
@Table(name = "bill_full_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BillFullDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "can", nullable = false)
    private String can;

    @NotNull
    @Column(name = "div_code", nullable = false)
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

    @NotNull
    @Column(name = "house_no", nullable = false)
    private String houseNo;

    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "category")
    private String category;

    @Column(name = "pipe_size", precision=20, scale=3)
    private BigDecimal pipeSize;

    @Column(name = "board_meter")
    private String boardMeter;

    @Column(name = "sewerage")
    private String sewerage;

    @Column(name = "prev_bill_type")
    private String prevBillType;

    @Column(name = "prev_bill_month")
    private LocalDate prevBillMonth;

    @Column(name = "prev_avg_kl", precision=20, scale=3)
    private BigDecimal prevAvgKl;

    @NotNull
    @Column(name = "met_reading_dt", nullable = false)
    private LocalDate metReadingDt;

    @Column(name = "prev_reading", precision=20, scale=3)
    private BigDecimal prevReading;

    @Column(name = "met_reading_mo")
    private LocalDate metReadingMo;

    @Column(name = "met_avg_kl", precision=20, scale=3)
    private BigDecimal metAvgKl;

    @Column(name = "arrears", precision=20, scale=3)
    private BigDecimal arrears;

    @Column(name = "reversal_amt", precision=20, scale=3)
    private BigDecimal reversalAmt;

    @Column(name = "installment", precision=20, scale=3)
    private BigDecimal installment;

    @Column(name = "other_charges", precision=20, scale=3)
    private BigDecimal otherCharges;

    @Column(name = "surcharge", precision=20, scale=3)
    private BigDecimal surcharge;

    @Column(name = "hrs_surcharge")
    private String hrsSurcharge;

    @Column(name = "res_units")
    private Long resUnits;

    @Column(name = "met_cost_installment", precision=20, scale=3)
    private BigDecimal metCostInstallment;

    @Column(name = "int_on_arrears", precision=20, scale=3)
    private BigDecimal intOnArrears;

    @Column(name = "last_pymt_dt")
    private LocalDate lastPymtDt;

    @Column(name = "last_pymt_amt", precision=20, scale=3)
    private BigDecimal lastPymtAmt;

    @Column(name = "bill_number")
    private String billNumber;

    @NotNull
    @Column(name = "bill_date", nullable = false)
    private LocalDate billDate;

    @Column(name = "bill_time")
    private String billTime;

    @Column(name = "meter_make")
    private String meterMake;

    @Column(name = "current_bill_type")
    private String currentBillType;

    @Column(name = "from_month")
    private String fromMonth;

    @Column(name = "to_month")
    private String toMonth;

    @Column(name = "meter_fix_date")
    private LocalDate meterFixDate;

    @Column(name = "initial_reading", precision=20, scale=3)
    private BigDecimal initialReading;

    @Column(name = "present_reading", precision=20, scale=3)
    private BigDecimal presentReading;

    @Column(name = "units", precision=20, scale=3)
    private BigDecimal units;

    @Column(name = "water_cess", precision=20, scale=3)
    private BigDecimal waterCess;

    @Column(name = "sewerage_cess", precision=20, scale=3)
    private BigDecimal sewerageCess;

    @Column(name = "service_charge", precision=20, scale=3)
    private BigDecimal serviceCharge;

    @Column(name = "meter_service_charge", precision=20, scale=3)
    private BigDecimal meterServiceCharge;

    @Column(name = "total_amount", precision=20, scale=3)
    private BigDecimal totalAmount;

    @Column(name = "net_payable_amount", precision=20, scale=3)
    private BigDecimal netPayableAmount;

    @Column(name = "telephone_no")
    private String telephoneNo;

    @Column(name = "meter_status")
    private String meterStatus;

    @Column(name = "bill_flag")
    private String billFlag;

    @Column(name = "svr_status")
    private String svrStatus;

    @Column(name = "terminal_id")
    private String terminalId;

    @Column(name = "meter_reader_id")
    private String meterReaderId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "notice_no")
    private String noticeNo;

    @Column(name = "lat")
    private String lat;

    @Column(name = "longi")
    private String longi;

    @Column(name = "no_meter_amt", precision=20, scale=3)
    private BigDecimal noMeterAmt;

    @Column(name = "lock_charges", precision=20, scale=3)
    private BigDecimal lockCharges;

    @ManyToOne
    @JoinColumn(name = "meter_details_id")
    private MeterDetails meterDetails;

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

    public BigDecimal getPipeSize() {
        return pipeSize;
    }

    public void setPipeSize(BigDecimal pipeSize) {
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

    public BigDecimal getPrevAvgKl() {
        return prevAvgKl;
    }

    public void setPrevAvgKl(BigDecimal prevAvgKl) {
        this.prevAvgKl = prevAvgKl;
    }

    public LocalDate getMetReadingDt() {
        return metReadingDt;
    }

    public void setMetReadingDt(LocalDate metReadingDt) {
        this.metReadingDt = metReadingDt;
    }

    public BigDecimal getPrevReading() {
        return prevReading;
    }

    public void setPrevReading(BigDecimal prevReading) {
        this.prevReading = prevReading;
    }

    public LocalDate getMetReadingMo() {
        return metReadingMo;
    }

    public void setMetReadingMo(LocalDate metReadingMo) {
        this.metReadingMo = metReadingMo;
    }

    public BigDecimal getMetAvgKl() {
        return metAvgKl;
    }

    public void setMetAvgKl(BigDecimal metAvgKl) {
        this.metAvgKl = metAvgKl;
    }

    public BigDecimal getArrears() {
        return arrears;
    }

    public void setArrears(BigDecimal arrears) {
        this.arrears = arrears;
    }

    public BigDecimal getReversalAmt() {
        return reversalAmt;
    }

    public void setReversalAmt(BigDecimal reversalAmt) {
        this.reversalAmt = reversalAmt;
    }

    public BigDecimal getInstallment() {
        return installment;
    }

    public void setInstallment(BigDecimal installment) {
        this.installment = installment;
    }

    public BigDecimal getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(BigDecimal otherCharges) {
        this.otherCharges = otherCharges;
    }

    public BigDecimal getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(BigDecimal surcharge) {
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

    public BigDecimal getMetCostInstallment() {
        return metCostInstallment;
    }

    public void setMetCostInstallment(BigDecimal metCostInstallment) {
        this.metCostInstallment = metCostInstallment;
    }

    public BigDecimal getIntOnArrears() {
        return intOnArrears;
    }

    public void setIntOnArrears(BigDecimal intOnArrears) {
        this.intOnArrears = intOnArrears;
    }

    public LocalDate getLastPymtDt() {
        return lastPymtDt;
    }

    public void setLastPymtDt(LocalDate lastPymtDt) {
        this.lastPymtDt = lastPymtDt;
    }

    public BigDecimal getLastPymtAmt() {
        return lastPymtAmt;
    }

    public void setLastPymtAmt(BigDecimal lastPymtAmt) {
        this.lastPymtAmt = lastPymtAmt;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }

    public String getMeterMake() {
        return meterMake;
    }

    public void setMeterMake(String meterMake) {
        this.meterMake = meterMake;
    }

    public String getCurrentBillType() {
        return currentBillType;
    }

    public void setCurrentBillType(String currentBillType) {
        this.currentBillType = currentBillType;
    }

    public String getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(String fromMonth) {
        this.fromMonth = fromMonth;
    }

    public String getToMonth() {
        return toMonth;
    }

    public void setToMonth(String toMonth) {
        this.toMonth = toMonth;
    }

    public LocalDate getMeterFixDate() {
        return meterFixDate;
    }

    public void setMeterFixDate(LocalDate meterFixDate) {
        this.meterFixDate = meterFixDate;
    }

    public BigDecimal getInitialReading() {
        return initialReading;
    }

    public void setInitialReading(BigDecimal initialReading) {
        this.initialReading = initialReading;
    }

    public BigDecimal getPresentReading() {
        return presentReading;
    }

    public void setPresentReading(BigDecimal presentReading) {
        this.presentReading = presentReading;
    }

    public BigDecimal getUnits() {
        return units;
    }

    public void setUnits(BigDecimal units) {
        this.units = units;
    }

    public BigDecimal getWaterCess() {
        return waterCess;
    }

    public void setWaterCess(BigDecimal waterCess) {
        this.waterCess = waterCess;
    }

    public BigDecimal getSewerageCess() {
        return sewerageCess;
    }

    public void setSewerageCess(BigDecimal sewerageCess) {
        this.sewerageCess = sewerageCess;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public BigDecimal getMeterServiceCharge() {
        return meterServiceCharge;
    }

    public void setMeterServiceCharge(BigDecimal meterServiceCharge) {
        this.meterServiceCharge = meterServiceCharge;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getNetPayableAmount() {
        return netPayableAmount;
    }

    public void setNetPayableAmount(BigDecimal netPayableAmount) {
        this.netPayableAmount = netPayableAmount;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public String getMeterStatus() {
        return meterStatus;
    }

    public void setMeterStatus(String meterStatus) {
        this.meterStatus = meterStatus;
    }

    public String getBillFlag() {
        return billFlag;
    }

    public void setBillFlag(String billFlag) {
        this.billFlag = billFlag;
    }

    public String getSvrStatus() {
        return svrStatus;
    }

    public void setSvrStatus(String svrStatus) {
        this.svrStatus = svrStatus;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getMeterReaderId() {
        return meterReaderId;
    }

    public void setMeterReaderId(String meterReaderId) {
        this.meterReaderId = meterReaderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
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

    public BigDecimal getNoMeterAmt() {
        return noMeterAmt;
    }

    public void setNoMeterAmt(BigDecimal noMeterAmt) {
        this.noMeterAmt = noMeterAmt;
    }

    public BigDecimal getLockCharges() {
        return lockCharges;
    }

    public void setLockCharges(BigDecimal lockCharges) {
        this.lockCharges = lockCharges;
    }

    public MeterDetails getMeterDetails() {
        return meterDetails;
    }

    public void setMeterDetails(MeterDetails meterDetails) {
        this.meterDetails = meterDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BillFullDetails billFullDetails = (BillFullDetails) o;
        return Objects.equals(id, billFullDetails.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BillFullDetails{" +
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
            ", billNumber='" + billNumber + "'" +
            ", billDate='" + billDate + "'" +
            ", billTime='" + billTime + "'" +
            ", meterMake='" + meterMake + "'" +
            ", currentBillType='" + currentBillType + "'" +
            ", fromMonth='" + fromMonth + "'" +
            ", toMonth='" + toMonth + "'" +
            ", meterFixDate='" + meterFixDate + "'" +
            ", initialReading='" + initialReading + "'" +
            ", presentReading='" + presentReading + "'" +
            ", units='" + units + "'" +
            ", waterCess='" + waterCess + "'" +
            ", sewerageCess='" + sewerageCess + "'" +
            ", serviceCharge='" + serviceCharge + "'" +
            ", meterServiceCharge='" + meterServiceCharge + "'" +
            ", totalAmount='" + totalAmount + "'" +
            ", netPayableAmount='" + netPayableAmount + "'" +
            ", telephoneNo='" + telephoneNo + "'" +
            ", meterStatus='" + meterStatus + "'" +
            ", billFlag='" + billFlag + "'" +
            ", svrStatus='" + svrStatus + "'" +
            ", terminalId='" + terminalId + "'" +
            ", meterReaderId='" + meterReaderId + "'" +
            ", userId='" + userId + "'" +
            ", mobileNo='" + mobileNo + "'" +
            ", noticeNo='" + noticeNo + "'" +
            ", lat='" + lat + "'" +
            ", longi='" + longi + "'" +
            ", noMeterAmt='" + noMeterAmt + "'" +
            ", lockCharges='" + lockCharges + "'" +
            '}';
    }
}
