package ru.ibesh.service.check;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneChecker {
    private String phonePatternString = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    Pattern phonePattern = Pattern.compile(phonePatternString);

    public boolean isValidPhoneNumber(String phoneNumber){
        Matcher matcher = phonePattern.matcher(phoneNumber);
        return matcher.find();
    }

    public boolean isValidPhoneNumber(String phoneNumber, Predicate<String> predicate){
        Matcher matcher = phonePattern.matcher(phoneNumber);
        return matcher.find() && predicate.test(phoneNumber);
    }
}
