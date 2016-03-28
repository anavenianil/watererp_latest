package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CurrentUsers.
 */
@Entity
@Table(name = "current_users")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CurrentUsers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "terminal_id")
    private String terminalId;
    
    @Column(name = "meter_reader_id")
    private String meterReaderId;
    
    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "request_type")
    private String requestType;
    
    @Column(name = "login_time")
    private ZonedDateTime loginTime;
    
    @Column(name = "ip")
    private String ip;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRequestType() {
        return requestType;
    }
    
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public ZonedDateTime getLoginTime() {
        return loginTime;
    }
    
    public void setLoginTime(ZonedDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CurrentUsers currentUsers = (CurrentUsers) o;
        if(currentUsers.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, currentUsers.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CurrentUsers{" +
            "id=" + id +
            ", terminalId='" + terminalId + "'" +
            ", meterReaderId='" + meterReaderId + "'" +
            ", userId='" + userId + "'" +
            ", requestType='" + requestType + "'" +
            ", loginTime='" + loginTime + "'" +
            ", ip='" + ip + "'" +
            '}';
    }
}
