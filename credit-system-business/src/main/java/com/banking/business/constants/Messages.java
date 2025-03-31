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

    public static class CreditType {
        public static final String NAME_ALREADY_EXISTS = "Credit type with this name already exists for this customer type";
        public static final String PARENT_TYPE_NOT_FOUND = "Parent credit type not found";
        public static final String INVALID_AMOUNT_RANGE = "Minimum amount must be less than maximum amount";
        public static final String INVALID_TERM_RANGE = "Minimum term must be less than maximum term";
        public static final String NOT_FOUND = "Credit type not found";
    }

    public static class CreditApplication {
        public static final String NOT_FOUND = "Credit application not found";
        public static final String CUSTOMER_TYPE_MISMATCH = "Customer type does not match credit type";
        public static final String AMOUNT_OUT_OF_RANGE = "Amount is out of allowed range for this credit type";
        public static final String TERM_OUT_OF_RANGE = "Term is out of allowed range for this credit type";
        public static final String CREDIT_TYPE_NOT_ACTIVE = "Credit type is not active";
        public static final String ALREADY_PROCESSED = "Credit application has already been processed";
    }
}