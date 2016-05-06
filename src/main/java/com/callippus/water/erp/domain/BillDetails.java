package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.callippus.water.erp.domain.enumeration.BillingStatus;

/**
 * A BillDetails.
 */
@Entity
@Table(name = "bill_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BillDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "can")
    private String can;

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

    @Column(name = "initial_reading")
    private Float initialReading;

    @Column(name = "present_reading")
    private Float presentReading;

    @Column(name = "units")
    private Float units;

    @Column(name = "water_cess")
    private Float waterCess;

    @Column(name = "sewerage_cess")
    private Float sewerageCess;

    @Column(name = "service_charge")
    private Float serviceCharge;

    @Column(name = "meter_service_charge")
    private Float meterServiceCharge;

    @Column(name = "total_amount")
    private Float totalAmount;

    @Column(name = "net_payable_amount")
    private Float netPayableAmount;

    @Column(name = "telephone_no")
    private String telephoneNo;

    @Column(name = "meter_status")
    private String meterStatus;

    @Column(name = "met_reader_code")
    private String metReaderCode;

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

    @Column(name = "no_meter_amt")
    private Float noMeterAmt;

    @Column(name = "met_reading_dt")
    private LocalDate metReadingDt;

    @Column(name = "insert_dt")
    private ZonedDateTime insertDt;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BillingStatus status;

    @ManyToOne
    @JoinColumn(name = "mtr_reader_id")
    private User mtrReader;

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

    public Float getInitialReading() {
        return initialReading;
    }

    public void setInitialReading(Float initialReading) {
        this.initialReading = initialReading;
    }

    public Float getPresentReading() {
        return presentReading;
    }

    public void setPresentReading(Float presentReading) {
        this.presentReading = presentReading;
    }

    public Float getUnits() {
        return units;
    }

    public void setUnits(Float units) {
        this.units = units;
    }

    public Float getWaterCess() {
        return waterCess;
    }

    public void setWaterCess(Float waterCess) {
        this.waterCess = waterCess;
    }

    public Float getSewerageCess() {
        return sewerageCess;
    }

    public void setSewerageCess(Float sewerageCess) {
        this.sewerageCess = sewerageCess;
    }

    public Float getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Float serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Float getMeterServiceCharge() {
        return meterServiceCharge;
    }

    public void setMeterServiceCharge(Float meterServiceCharge) {
        this.meterServiceCharge = meterServiceCharge;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getNetPayableAmount() {
        return netPayableAmount;
    }

    public void setNetPayableAmount(Float netPayableAmount) {
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

    public String getMetReaderCode() {
        return metReaderCode;
    }

    public void setMetReaderCode(String metReaderCode) {
        this.metReaderCode = metReaderCode;
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

    public Float getNoMeterAmt() {
        return noMeterAmt;
    }

    public void setNoMeterAmt(Float noMeterAmt) {
        this.noMeterAmt = noMeterAmt;
    }

    public LocalDate getMetReadingDt() {
        return metReadingDt;
    }

    public void setMetReadingDt(LocalDate metReadingDt) {
        this.metReadingDt = metReadingDt;
    }

    public ZonedDateTime getInsertDt() {
        return insertDt;
    }

    public void setInsertDt(ZonedDateTime insertDt) {
        this.insertDt = insertDt;
    }

    public BillingStatus getStatus() {
        return status;
    }

    public void setStatus(BillingStatus status) {
        this.status = status;
    }

    public User getMtrReader() {
        return mtrReader;
    }

    public void setMtrReader(User user) {
        this.mtrReader = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BillDetails billDetails = (BillDetails) o;
        return Objects.equals(id, billDetails.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BillDetails{" +
            "id=" + id +
            ", can='" + can + "'" +
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
            ", metReaderCode='" + metReaderCode + "'" +
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
            ", metReadingDt='" + metReadingDt + "'" +
            ", insertDt='" + insertDt + "'" +
            ", status='" + status + "'" +
            '}';
    }
}
