package com.boommanpro.xportsreserve.global;

public class GlobalAccountStatus {
    public static boolean accountStatus = true;

    public static boolean isAccountStatus() {
        return accountStatus;
    }

    public static void setAccountStatus(boolean accountStatus) {
        GlobalAccountStatus.accountStatus = accountStatus;
    }
}
