package com.callippus.water.erp.domain;

public class UserDefinedFields
{
    private Invoice invoice;

    public Invoice getInvoice ()
    {
        return invoice;
    }

    public void setInvoice (Invoice invoice)
    {
        this.invoice = invoice;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [invoice = "+invoice+"]";
    }
}