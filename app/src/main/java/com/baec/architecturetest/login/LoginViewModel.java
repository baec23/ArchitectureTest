package com.baec.architecturetest.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    private final MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>(new LoginFormState(null, null, false));
    private String usernameText = "";
    private String passwordText = "";

    public void onUsernameTextChanged(String changedUsername)
    {
        usernameText = changedUsername;
        if(!isUsernameValid(usernameText))
            loginFormState.setValue(new LoginFormState("Username must be >5 characters", loginFormState.getValue().getPasswordErrorMessage(), false));
        else if(isPasswordValid(passwordText))
            loginFormState.setValue(new LoginFormState(null, null, true));
        else if(loginFormState.getValue().getUsernameErrorMessage() != null)
            loginFormState.setValue(new LoginFormState(null, loginFormState.getValue().getPasswordErrorMessage(), false));
    }

    public void onPasswordTextChanged(String changedPassword)
    {
        passwordText = changedPassword;
        if(!isPasswordValid(passwordText))
            loginFormState.setValue(new LoginFormState(loginFormState.getValue().getUsernameErrorMessage(),"Password must be >5 characters", false));
        else if(isUsernameValid(usernameText))
            loginFormState.setValue(new LoginFormState(null, null, true));
        else if(loginFormState.getValue().getPasswordErrorMessage() != null)
            loginFormState.setValue(new LoginFormState(loginFormState.getValue().getUsernameErrorMessage(), null, false));
    }

    private boolean isUsernameValid(String usernameText) {
        if (usernameText.length() > 5)
            return true;
        return false;
    }

    private boolean isPasswordValid(String passwordText) {
        return passwordText.length() > 5;
    }

    public LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }
}