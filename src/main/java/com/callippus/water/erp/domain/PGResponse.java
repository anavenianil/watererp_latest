package com.callippus.water.erp.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="OrderResponse")
public class PGResponse
{
	@XmlElement	
    private String MerchantCode;

	@XmlElement
	private String PaymentMode;

	@XmlElement
    private String MerchantRefNumber;

	@XmlElement
    private String UserDefinedField;

	@XmlElement
    private String Message;

	@XmlElement
    private String ServiceCode;

	@XmlElement
    private String ResponseCode;

	@XmlElement
    private String Currency;

	@XmlElement
    private String TotalAmountPaid;

    public String getMerchantCode ()
    {
        return MerchantCode;
    }

    public void setMerchantCode (String MerchantCode)
    {
        this.MerchantCode = MerchantCode;
    }

    public String getPaymentMode ()
    {
        return PaymentMode;
    }

    public void setPaymentMode (String PaymentMode)
    {
        this.PaymentMode = PaymentMode;
    }

    public String getMerchantRefNumber ()
    {
        return MerchantRefNumber;
    }

    public void setMerchantRefNumber (String MerchantRefNumber)
    {
        this.MerchantRefNumber = MerchantRefNumber;
    }

    public String getUserDefinedField ()
    {
        return UserDefinedField;
    }

    public void setUserDefinedField (String UserDefinedField)
    {
        this.UserDefinedField = UserDefinedField;
    }

    public String getMessage ()
    {
        return Message;
    }

    public void setMessage (String Message)
    {
        this.Message = Message;
    }

    public String getServiceCode ()
    {
        return ServiceCode;
    }

    public void setServiceCode (String ServiceCode)
    {
        this.ServiceCode = ServiceCode;
    }

    public String getResponseCode ()
    {
        return ResponseCode;
    }

    public void setResponseCode (String ResponseCode)
    {
        this.ResponseCode = ResponseCode;
    }

    public String getCurrency ()
    {
        return Currency;
    }

    public void setCurrency (String Currency)
    {
        this.Currency = Currency;
    }

    public String getTotalAmountPaid ()
    {
        return TotalAmountPaid;
    }

    public void setTotalAmountPaid (String TotalAmountPaid)
    {
        this.TotalAmountPaid = TotalAmountPaid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MerchantCode = "+MerchantCode+", PaymentMode = "+PaymentMode+", MerchantRefNumber = "+MerchantRefNumber+", UserDefinedField = "+UserDefinedField+", Message = "+Message+", ServiceCode = "+ServiceCode+", ResponseCode = "+ResponseCode+", Currency = "+Currency+", TotalAmountPaid = "+TotalAmountPaid+"]";
    }
}