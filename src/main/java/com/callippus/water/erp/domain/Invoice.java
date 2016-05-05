package com.callippus.water.erp.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Invoice
{
	@XmlElement(name = "UserDefinedField")
    private String userDefinedField;

    public String getUserDefinedField ()
    {
        return userDefinedField;
    }

    public void setUserDefinedField (String UserDefinedField)
    {
        this.userDefinedField = userDefinedField;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [UserDefinedField = "+userDefinedField+"]";
    }
}