package com.driver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.SortedMap;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000.0);
        if(balance < 5000.0){
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        boolean isValid = true;
        int lengthOfId = tradeLicenseId.length();
        for (int i=0; i < lengthOfId-1; i++){
            if(tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i+1)){
                isValid = false;
                break;
            }
        }
        if(isValid)return;
        HashMap<Character,Integer> freq = new HashMap<>();
        for (int i=0; i<lengthOfId; i++){
            char ch = tradeLicenseId.charAt(i);
            freq.put(ch,freq.getOrDefault(ch,0)+1);
        }
        int maxCount = 0;
        char maxChar = ' ';
        for (char ch : freq.keySet()) {
            if(freq.get(ch) > maxCount){
                maxChar = ch;
                maxCount = Math.max(maxCount,freq.get(ch));
            }
        }
        if(maxCount > (lengthOfId + 1)/2){
            //it is valid
            isValid = false;
        }else{
            isValid = true;
        }
        if(isValid == false)throw new ValidLicenseException("Valid License can not be generated");

        char licenseId[] = new char[lengthOfId];
        int idx=0;
        while (maxCount-- > 0){
            licenseId[idx] = maxChar;
            idx += 2;
        }
        freq.remove(maxChar);
        for(char ch : freq.keySet()){
            int charFreq = freq.get(ch);
            while(charFreq-- > 0){
                if(idx >= lengthOfId)idx = 1;
                licenseId[idx] = ch;
                idx += 2;
            }
        }
        String newLicenceId = "";
        for (char ch : licenseId) {
            newLicenceId += ch;
        }
        setTradeLicenseId(newLicenceId);
    }
}
