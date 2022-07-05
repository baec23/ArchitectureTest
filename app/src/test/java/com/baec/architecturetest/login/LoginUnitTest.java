package com.baec.architecturetest.login;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LoginUnitTest {
    LoginViewModel testViewModel;

    @Before
    public void init() {
        testViewModel = new LoginViewModel();
    }

    @Test //usernameErrorMessage=null, passwordErrorMessage=null, isFieldValid=false
    public void initialLoginFormState() {
        LoginFormState loginFormState = testViewModel.getLoginFormState().getValue();
        assertFalse(loginFormState.isFieldsValid());
        assertNull(loginFormState.getUsernameErrorMessage());
        assertNull(loginFormState.getPasswordErrorMessage());
    }

    @Test
    public void onUsernameTextChanged_loginFormStateValid() {
        testViewModel.onUsernameTextChanged("abc");
        LoginFormState loginFormState = testViewModel.getLoginFormState().getValue();
        assertNotNull(loginFormState.getUsernameErrorMessage());

        testViewModel.onUsernameTextChanged("abcdef");
        loginFormState = testViewModel.getLoginFormState().getValue();
        assertNull(loginFormState.getUsernameErrorMessage());

    }

    @Test
    public void onPasswordTextChanged_loginFormStateValid() {
        testViewModel.onPasswordTextChanged("abc");
        LoginFormState loginFormState = testViewModel.getLoginFormState().getValue();
        assertNotNull(loginFormState.getPasswordErrorMessage());

        testViewModel.onPasswordTextChanged("abcdef");
        loginFormState = testViewModel.getLoginFormState().getValue();
        assertNull(loginFormState.getPasswordErrorMessage());
    }

    @Test
    public void onUsernameAndPasswordValid_isFieldsValid_true() {
        testViewModel.onUsernameTextChanged("abcdef");
        testViewModel.onPasswordTextChanged("abcdef");
        LoginFormState loginFormState = testViewModel.getLoginFormState().getValue();
        assertTrue(loginFormState.isFieldsValid());
    }
}
