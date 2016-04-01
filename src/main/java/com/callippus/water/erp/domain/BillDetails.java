package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

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
    private String bill_number;
    
    @NotNull
    @Column(name = "bill_date", nullable = false)
    private LocalDate bill_date;
    
    @Column(name = "bill_time")
    private String bill_time;
    
    @Column(name = "meter_make")
    private String meter_make;
    
    @Column(name = "current_bill_type")
    private String current_bill_type;
    
    @Column(name = "from_month")
    private String from_month;
    
    @Column(name = "to_month")
    private String to_month;
    
    @Column(name = "meter_fix_date")
    private String meter_fix_date;
    
    @Column(name = "initial_reading")
    private String initial_reading;
    
    @Column(name = "present_reading")
    private String present_reading;
    
    @Column(name = "units")
    private String units;
    
    @Column(name = "water_cess")
    private String water_cess;
    
    @Column(name = "sewerage_cess")
    private String sewerage_cess;
    
    @Column(name = "service_charge")
    private String service_charge;
    
    @Column(name = "meter_service_charge")
    private String meter_service_charge;
    
    @Column(name = "total_amount")
    private Float total_amount;
    
    @Column(name = "net_payable_amount")
    private Float net_payable_amount;
    
    @Column(name = "telephone_no")
    private String telephone_no;
    
    @Column(name = "meter_status")
    private String meter_status;
    
    @Column(name = "mc_met_reader_code")
    private String mc_met_reader_code;
    
    @Column(name = "bill_flag")
    private String bill_flag;
    
    @Column(name = "svr_status")
    private String svr_status;
    
    @Column(name = "terminal_id")
    private String terminal_id;
    
    @Column(name = "meter_reader_id")
    private String meter_reader_id;
    
    @Column(name = "user_id")
    private String user_id;
    
    @Column(name = "mobile_no")
    private String mobile_no;
    
    @Column(name = "notice_no")
    private String notice_no;
    
    @Column(name = "lat")
    private String lat;
    
    @Column(name = "long_i")
    private String longI;
    
    @Column(name = "nometer_amt")
    private String nometer_amt;
    
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

    public String getBill_number() {
        return bill_number;
    }
    
    public void setBill_number(String bill_number) {
        this.bill_number = bill_number;
    }

    public LocalDate getBill_date() {
        return bill_date;
    }
    
    public void setBill_date(LocalDate bill_date) {
        this.bill_date = bill_date;
    }

    public String getBill_time() {
        return bill_time;
    }
    
    public void setBill_time(String bill_time) {
        this.bill_time = bill_time;
    }

    public String getMeter_make() {
        return meter_make;
    }
    
    public void setMeter_make(String meter_make) {
        this.meter_make = meter_make;
    }

    public String getCurrent_bill_type() {
        return current_bill_type;
    }
    
    public void setCurrent_bill_type(String current_bill_type) {
        this.current_bill_type = current_bill_type;
    }

    public String getFrom_month() {
        return from_month;
    }
    
    public void setFrom_month(String from_month) {
        this.from_month = from_month;
    }

    public String getTo_month() {
        return to_month;
    }
    
    public void setTo_month(String to_month) {
        this.to_month = to_month;
    }

    public String getMeter_fix_date() {
        return meter_fix_date;
    }
    
    public void setMeter_fix_date(String meter_fix_date) {
        this.meter_fix_date = meter_fix_date;
    }

    public String getInitial_reading() {
        return initial_reading;
    }
    
    public void setInitial_reading(String initial_reading) {
        this.initial_reading = initial_reading;
    }

    public String getPresent_reading() {
        return present_reading;
    }
    
    public void setPresent_reading(String present_reading) {
        this.present_reading = present_reading;
    }

    public String getUnits() {
        return units;
    }
    
    public void setUnits(String units) {
        this.units = units;
    }

    public String getWater_cess() {
        return water_cess;
    }
    
    public void setWater_cess(String water_cess) {
        this.water_cess = water_cess;
    }

    public String getSewerage_cess() {
        return sewerage_cess;
    }
    
    public void setSewerage_cess(String sewerage_cess) {
        this.sewerage_cess = sewerage_cess;
    }

    public String getService_charge() {
        return service_charge;
    }
    
    public void setService_charge(String service_charge) {
        this.service_charge = service_charge;
    }

    public String getMeter_service_charge() {
        return meter_service_charge;
    }
    
    public void setMeter_service_charge(String meter_service_charge) {
        this.meter_service_charge = meter_service_charge;
    }

    public Float getTotal_amount() {
        return total_amount;
    }
    
    public void setTotal_amount(Float total_amount) {
        this.total_amount = total_amount;
    }

    public Float getNet_payable_amount() {
        return net_payable_amount;
    }
    
    public void setNet_payable_amount(Float net_payable_amount) {
        this.net_payable_amount = net_payable_amount;
    }

    public String getTelephone_no() {
        return telephone_no;
    }
    
    public void setTelephone_no(String telephone_no) {
        this.telephone_no = telephone_no;
    }

    public String getMeter_status() {
        return meter_status;
    }
    
    public void setMeter_status(String meter_status) {
        this.meter_status = meter_status;
    }

    public String getMc_met_reader_code() {
        return mc_met_reader_code;
    }
    
    public void setMc_met_reader_code(String mc_met_reader_code) {
        this.mc_met_reader_code = mc_met_reader_code;
    }

    public String getBill_flag() {
        return bill_flag;
    }
    
    public void setBill_flag(String bill_flag) {
        this.bill_flag = bill_flag;
    }

    public String getSvr_status() {
        return svr_status;
    }
    
    public void setSvr_status(String svr_status) {
        this.svr_status = svr_status;
    }

    public String getTerminal_id() {
        return terminal_id;
    }
    
    public void setTerminal_id(String terminal_id) {
        this.terminal_id = terminal_id;
    }

    public String getMeter_reader_id() {
        return meter_reader_id;
    }
    
    public void setMeter_reader_id(String meter_reader_id) {
        this.meter_reader_id = meter_reader_id;
    }

    public String getUser_id() {
        return user_id;
    }
    
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMobile_no() {
        return mobile_no;
    }
    
    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getNotice_no() {
        return notice_no;
    }
    
    public void setNotice_no(String notice_no) {
        this.notice_no = notice_no;
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

    public String getNometer_amt() {
        return nometer_amt;
    }
    
    public void setNometer_amt(String nometer_amt) {
        this.nometer_amt = nometer_amt;
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
        if(billDetails.id == null || id == null) {
            return false;
        }
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
            ", bill_number='" + bill_number + "'" +
            ", bill_date='" + bill_date + "'" +
            ", bill_time='" + bill_time + "'" +
            ", meter_make='" + meter_make + "'" +
            ", current_bill_type='" + current_bill_type + "'" +
            ", from_month='" + from_month + "'" +
            ", to_month='" + to_month + "'" +
            ", meter_fix_date='" + meter_fix_date + "'" +
            ", initial_reading='" + initial_reading + "'" +
            ", present_reading='" + present_reading + "'" +
            ", units='" + units + "'" +
            ", water_cess='" + water_cess + "'" +
            ", sewerage_cess='" + sewerage_cess + "'" +
            ", service_charge='" + service_charge + "'" +
            ", meter_service_charge='" + meter_service_charge + "'" +
            ", total_amount='" + total_amount + "'" +
            ", net_payable_amount='" + net_payable_amount + "'" +
            ", telephone_no='" + telephone_no + "'" +
            ", meter_status='" + meter_status + "'" +
            ", mc_met_reader_code='" + mc_met_reader_code + "'" +
            ", bill_flag='" + bill_flag + "'" +
            ", svr_status='" + svr_status + "'" +
            ", terminal_id='" + terminal_id + "'" +
            ", meter_reader_id='" + meter_reader_id + "'" +
            ", user_id='" + user_id + "'" +
            ", mobile_no='" + mobile_no + "'" +
            ", notice_no='" + notice_no + "'" +
            ", lat='" + lat + "'" +
            ", longI='" + longI + "'" +
            ", nometer_amt='" + nometer_amt + "'" +
            '}';
    }
}
