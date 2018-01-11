package com.example.lunchvoting;

import com.example.lunchvoting.security.AuthorizedPerson;
import com.example.lunchvoting.util.PasswordUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

/**
 *
 */
public class SecurityTest {

    @Test
    public void testEncoding() {
        String testPassword = "54321";
        System.out.println(PasswordUtil.encode(testPassword));
        Assert.assertTrue(PasswordUtil.isMatch(testPassword, PasswordUtil.encode(testPassword)));
    }

//    @Test
//    public void testAuthenticationPrincipal() {
//        ;
//        System.out.println(authorizedPerson.getUsername());
//    }
}
