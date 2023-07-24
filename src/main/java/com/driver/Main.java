package com.driver;

public class Main {
    public static void main(String[] args) {
        try{
            CurrentAccount b = new CurrentAccount("Param",5000,"PAMMMMMMTW");
            try{
                b.validateLicenseId();
                System.out.println(b.getTradeLicenseId());
            }catch(Exception exception){
                System.out.println(exception.getMessage());
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}