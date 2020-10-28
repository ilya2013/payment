package ru.ibesh.service.check;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class PhoneCheckerTest {

    @Test
    void whenRussianMobilePhoneWithCountryCodeThenTrue() {
        boolean expected = true;
        PhoneChecker phoneChecker = new PhoneChecker();
        assertEquals(expected,phoneChecker.isValidPhoneNumber("+7(911)1766050"));
    }

    @Test
    void whenRussianMobilePhoneWith8AtTheBeginningThenTrue() {
        boolean expected = true;
        PhoneChecker phoneChecker = new PhoneChecker();
        assertEquals(expected,phoneChecker.isValidPhoneNumber("8(911)1766050"));
    }


    @Test
    void whenRussianMobilePhoneWith8AtTheBeginningWithSpacesThenTrue() {
        boolean expected = true;
        PhoneChecker phoneChecker = new PhoneChecker();
        assertEquals(expected,phoneChecker.isValidPhoneNumber("8(911)176 60 50"));
    }

    @Test
    void whenRussianMobilePhoneWith8AtTheBeginningWithMinusesThenTrue() {
        boolean expected = true;
        PhoneChecker phoneChecker = new PhoneChecker();
        assertEquals(expected,phoneChecker.isValidPhoneNumber("8(911)176-60-50"));
    }
    @Test
    void whenRussianMobilePhoneWith8AtTheBeginningWithMinusesAndBackspaceThenTrue() {
        boolean expected = true;
        PhoneChecker phoneChecker = new PhoneChecker();
        assertEquals(expected,phoneChecker.isValidPhoneNumber("8(911)176 60-50"));
    }

    @Test
    void whenRussianMobilePhoneWith8AtTheBeginningWithOnlyOneSeparatorMinusesThenTrue() {
        boolean expected = true;
        PhoneChecker phoneChecker = new PhoneChecker();
        assertEquals(expected,phoneChecker.isValidPhoneNumber("8(911)17660-50"));
    }

    @Test
    void whenNotRussianMobilePhoneWithSpacesThenTrue() {
        boolean expected = false;
        PhoneChecker phoneChecker = new PhoneChecker();
        assertEquals(expected,phoneChecker.isValidPhoneNumber("+311(911)176 60 50"));
    }

    @Test
    void whenNotStrangeRussianMobilePhoneWith8DigitAtTheBeginningThenTrue() {
        boolean expected = false;
        PhoneChecker phoneChecker = new PhoneChecker();
        assertEquals(expected,phoneChecker.isValidPhoneNumber("8311(911)176 60 50"));
    }

    @Test
    void whenStrictRussianMobilePhoneNumberThenTrue() {
        boolean expected = true;
        PhoneChecker phoneChecker = new PhoneChecker();
        assertEquals(expected,phoneChecker.isValidPhoneNumber("+7(911)1766050", a -> a.length() == 14));
    }

    private static boolean is12LengthString(String string){
        return string.length() == 12;
    }
    @Test
    void whenStrictRussianMobilePhoneNumberThenTrue2() {
        boolean expected = true;
        PhoneChecker phoneChecker = new PhoneChecker();
        assertEquals(expected,phoneChecker.isValidPhoneNumber("+79111766050", PhoneCheckerTest::is12LengthString));
    }
}