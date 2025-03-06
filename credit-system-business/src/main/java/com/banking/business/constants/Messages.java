package com.banking.business.constants;

public class Messages {
    
    public static class Customer {
        public static final String EMAIL_ALREADY_EXISTS = "Customer with this email already exists";
        public static final String NOT_FOUND = "Customer not found";
    }
    
    public static class IndividualCustomer {
        public static final String NATIONAL_ID_ALREADY_EXISTS = "Customer with this national ID already exists";
    }
    
    public static class CorporateCustomer {
        public static final String TAX_NUMBER_ALREADY_EXISTS = "Customer with this tax number already exists";
        public static final String TRADE_REGISTER_NUMBER_ALREADY_EXISTS = "Customer with this trade register number already exists";
    }
}  