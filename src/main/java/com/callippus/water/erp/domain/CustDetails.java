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
    private String pipeSize;
    
    @Column(name = "board_meter")
    private String boardMeter;
    
    @Column(name = "sewerage")
    private String sewerage;
    
    @Column(name = "meter_no")
    private String meterNo;
    
    @Column(name = "prev_bill_type")
    private String prevBillType;
    
    @Column(name = "prev_bill_month")
    private String prevBillMonth;
    
    @Column(name = "prev_avg_kl")
    private String prevAvgKl;
    
    @Column(name = "met_reading_dt")
    private LocalDate metReadingDt;
    
    @Column(name = "prev_reading")
    private String prevReading;
    
    @Column(name = "met_reading_mo")
    private String metReadingMo;
    
    @Column(name = "met_avg_kl")
    private String metAvgKl;
    
    @Column(name = "arrears")
    private String arrears;
    
    @Column(name = "reversal_amt")
    private String reversalAmt;
    
    @Column(name = "installment")
    private String installment;
    
    @Column(name = "other_charges")
    private String otherCharges;
    
    @Column(name = "sur_charge")
    private String surCharge;
    
    @Column(name = "hrs_sur_charge")
    private String hrsSurCharge;
    
    @Column(name = "res_units")
    private String resUnits;
    
    @Column(name = "met_cost_installment")
    private String metCostInstallment;
    
    @Column(name = "int_on_arrears")
    private String intOnArrears;
    
    @Column(name = "last_pymt_dt")
    private String lastPymtDt;
    
    @Column(name = "last_pymt_amt")
    private Float lastPymtAmt;
    
    @Column(name = "mobile_no")
    private String mobileNo;
    
    @Column(name = "bill_number")
    private String billNumber;
    
    @Column(name = "bill_date")
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
    private String meterFixDate;
    
    @Column(name = "initial_reading")
    private String initialReading;
    
    @Column(name = "present_reading")
    private String presentReading;
    
    @Column(name = "units")
    private String units;
    
    @Column(name = "water_cess")
    private String waterCess;
    
    @Column(name = "sewerage_cess")
    private String sewerageCess;
    
    @Column(name = "service_charge")
    private String serviceCharge;
    
    @Column(name = "meter_service_charge")
    private String meterServiceCharge;
    
    @Column(name = "total_amount")
    private Float totalAmount;
    
    @Column(name = "net_payable_amount")
    private String netPayableAmount;
    
    @Column(name = "telephone_no")
    private String telephoneNo;
    
    @Column(name = "meter_status")
    private String meterStatus;
    
    @Column(name = "mc_met_reader_code")
    private String mcMetReaderCode;
    
    @Column(name = "bill_flag")
    private String billFlag;
    
    @Column(name = "docket")
    private String docket;
    
    @Column(name = "oc_flag")
    private String ocFlag;
    
    @Column(name = "oc_date")
    private LocalDate ocDate;
    
    @Column(name = "lat")
    private String lat;
    
    @Column(name = "long_i")
    private String longI;
    
    @Column(name = "no_meter_flag")
    private String noMeterFlag;
    
    @Column(name = "no_meter_ack_dt")
    private String noMeterAckDt;
    
    @Column(name = "no_meter_amt")
    private Float noMeterAmt;
    
    @Column(name = "meter_tamp_amt")
    private Float meterTampAmt;
    
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

    public String getPipeSize() {
        return pipeSize;
    }
    
    public void setPipeSize(String pipeSize) {
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

    public String getPrevBillMonth() {
        return prevBillMonth;
    }
    
    public void setPrevBillMonth(String prevBillMonth) {
        this.prevBillMonth = prevBillMonth;
    }

    public String getPrevAvgKl() {
        return prevAvgKl;
    }
    
    public void setPrevAvgKl(String prevAvgKl) {
        this.prevAvgKl = prevAvgKl;
    }

    public LocalDate getMetReadingDt() {
        return metReadingDt;
    }
    
    public void setMetReadingDt(LocalDate metReadingDt) {
        this.metReadingDt = metReadingDt;
    }

    public String getPrevReading() {
        return prevReading;
    }
    
    public void setPrevReading(String prevReading) {
        this.prevReading = prevReading;
    }

    public String getMetReadingMo() {
        return metReadingMo;
    }
    
    public void setMetReadingMo(String metReadingMo) {
        this.metReadingMo = metReadingMo;
    }

    public String getMetAvgKl() {
        return metAvgKl;
    }
    
    public void setMetAvgKl(String metAvgKl) {
        this.metAvgKl = metAvgKl;
    }

    public String getArrears() {
        return arrears;
    }
    
    public void setArrears(String arrears) {
        this.arrears = arrears;
    }

    public String getReversalAmt() {
        return reversalAmt;
    }
    
    public void setReversalAmt(String reversalAmt) {
        this.reversalAmt = reversalAmt;
    }

    public String getInstallment() {
        return installment;
    }
    
    public void setInstallment(String installment) {
        this.installment = installment;
    }

    public String getOtherCharges() {
        return otherCharges;
    }
    
    public void setOtherCharges(String otherCharges) {
        this.otherCharges = otherCharges;
    }

    public String getSurCharge() {
        return surCharge;
    }
    
    public void setSurCharge(String surCharge) {
        this.surCharge = surCharge;
    }

    public String getHrsSurCharge() {
        return hrsSurCharge;
    }
    
    public void setHrsSurCharge(String hrsSurCharge) {
        this.hrsSurCharge = hrsSurCharge;
    }

    public String getResUnits() {
        return resUnits;
    }
    
    public void setResUnits(String resUnits) {
        this.resUnits = resUnits;
    }

    public String getMetCostInstallment() {
        return metCostInstallment;
    }
    
    public void setMetCostInstallment(String metCostInstallment) {
        this.metCostInstallment = metCostInstallment;
    }

    public String getIntOnArrears() {
        return intOnArrears;
    }
    
    public void setIntOnArrears(String intOnArrears) {
        this.intOnArrears = intOnArrears;
    }

    public String getLastPymtDt() {
        return lastPymtDt;
    }
    
    public void setLastPymtDt(String lastPymtDt) {
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

    public String getMeterFixDate() {
        return meterFixDate;
    }
    
    public void setMeterFixDate(String meterFixDate) {
        this.meterFixDate = meterFixDate;
    }

    public String getInitialReading() {
        return initialReading;
    }
    
    public void setInitialReading(String initialReading) {
        this.initialReading = initialReading;
    }

    public String getPresentReading() {
        return presentReading;
    }
    
    public void setPresentReading(String presentReading) {
        this.presentReading = presentReading;
    }

    public String getUnits() {
        return units;
    }
    
    public void setUnits(String units) {
        this.units = units;
    }

    public String getWaterCess() {
        return waterCess;
    }
    
    public void setWaterCess(String waterCess) {
        this.waterCess = waterCess;
    }

    public String getSewerageCess() {
        return sewerageCess;
    }
    
    public void setSewerageCess(String sewerageCess) {
        this.sewerageCess = sewerageCess;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }
    
    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getMeterServiceCharge() {
        return meterServiceCharge;
    }
    
    public void setMeterServiceCharge(String meterServiceCharge) {
        this.meterServiceCharge = meterServiceCharge;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getNetPayableAmount() {
        return netPayableAmount;
    }
    
    public void setNetPayableAmount(String netPayableAmount) {
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

    public String getMcMetReaderCode() {
        return mcMetReaderCode;
    }
    
    public void setMcMetReaderCode(String mcMetReaderCode) {
        this.mcMetReaderCode = mcMetReaderCode;
    }

    public String getBillFlag() {
        return billFlag;
    }
    
    public void setBillFlag(String billFlag) {
        this.billFlag = billFlag;
    }

    public String getDocket() {
        return docket;
    }
    
    public void setDocket(String docket) {
        this.docket = docket;
    }

    public String getOcFlag() {
        return ocFlag;
    }
    
    public void setOcFlag(String ocFlag) {
        this.ocFlag = ocFlag;
    }

    public LocalDate getOcDate() {
        return ocDate;
    }
    
    public void setOcDate(LocalDate ocDate) {
        this.ocDate = ocDate;
    }

    public String getLat() {
        return lat;
    }
    
    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongI() {
        return longI;
    }
    
    public void setLongI(String longI) {
        this.longI = longI;
    }

    public String getNoMeterFlag() {
        return noMeterFlag;
    }
    
    public void setNoMeterFlag(String noMeterFlag) {
        this.noMeterFlag = noMeterFlag;
    }

    public String getNoMeterAckDt() {
        return noMeterAckDt;
    }
    
    public void setNoMeterAckDt(String noMeterAckDt) {
        this.noMeterAckDt = noMeterAckDt;
    }

    public Float getNoMeterAmt() {
        return noMeterAmt;
    }
    
    public void setNoMeterAmt(Float noMeterAmt) {
        this.noMeterAmt = noMeterAmt;
    }

    public Float getMeterTampAmt() {
        return meterTampAmt;
    }
    
    public void setMeterTampAmt(Float meterTampAmt) {
        this.meterTampAmt = meterTampAmt;
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
        if(custDetails.id == null || id == null) {
            return false;
        }
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
            ", surCharge='" + surCharge + "'" +
            ", hrsSurCharge='" + hrsSurCharge + "'" +
            ", resUnits='" + resUnits + "'" +
            ", metCostInstallment='" + metCostInstallment + "'" +
            ", intOnArrears='" + intOnArrears + "'" +
            ", lastPymtDt='" + lastPymtDt + "'" +
            ", lastPymtAmt='" + lastPymtAmt + "'" +
            ", mobileNo='" + mobileNo + "'" +
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
            ", mcMetReaderCode='" + mcMetReaderCode + "'" +
            ", billFlag='" + billFlag + "'" +
            ", docket='" + docket + "'" +
            ", ocFlag='" + ocFlag + "'" +
            ", ocDate='" + ocDate + "'" +
            ", lat='" + lat + "'" +
            ", longI='" + longI + "'" +
            ", noMeterFlag='" + noMeterFlag + "'" +
            ", noMeterAckDt='" + noMeterAckDt + "'" +
            ", noMeterAmt='" + noMeterAmt + "'" +
            ", meterTampAmt='" + meterTampAmt + "'" +
            '}';
    }
}
