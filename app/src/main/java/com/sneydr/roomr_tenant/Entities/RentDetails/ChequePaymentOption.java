package com.sneydr.roomr_tenant.Entities.RentDetails;


public class ChequePaymentOption extends PaymentOption {

    public ChequePaymentOption(String rentMadePayableTo) {
        super();
        this.name = "Cheque";
        this.rentMadePayableTo = rentMadePayableTo;
    }

    @Override
    public String getRentMadePayableTo()  {
        return this.rentMadePayableTo;
    }
}
