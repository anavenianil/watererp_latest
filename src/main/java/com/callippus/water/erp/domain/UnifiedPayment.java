package com.callippus.water.erp.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="UnifiedPayment")
public class UnifiedPayment
{
	@XmlElement
    private String RedirectUrl;

	@XmlElement
    private String ResponseCode;

    public String getRedirectUrl ()
    {
        return RedirectUrl;
    }

    public void setRedirectUrl (String RedirectUrl)
    {
        this.RedirectUrl = RedirectUrl;
    }

    public String getResponseCode ()
    {
        return ResponseCode;
    }

    public void setResponseCode (String ResponseCode)
    {
        this.ResponseCode = ResponseCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [RedirectUrl = "+RedirectUrl+", ResponseCode = "+ResponseCode+"]";
    }
}