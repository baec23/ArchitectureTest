package com.baec.architecturetest.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.baec.architecturetest.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;

    private EditText et_username;
    private EditText et_password;
    private Button bt_login;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        et_username = binding.loginEtUsername;
        et_password = binding.loginEtPassword;
        bt_login = binding.loginBtLogin;

        TextWatcher usernameTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                loginViewModel.onUsernameTextChanged(editable.toString());
            }
        };
        et_username.addTextChangedListener(usernameTextWatcher);

        TextWatcher passwordTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                loginViewModel.onPasswordTextChanged(editable.toString());
            }
        };
        et_password.addTextChangedListener(passwordTextWatcher);

        loginViewModel.getLoginFormState().observe(getViewLifecycleOwner(), new Observer<LoginFormState>() {
            @Override
            public void onChanged(LoginFormState loginFormState) {
                if (loginFormState.getUsernameErrorMessage() != null)
                    et_username.setError(loginFormState.getUsernameErrorMessage());
                if (loginFormState.getPasswordErrorMessage() != null)
                    et_password.setError(loginFormState.getPasswordErrorMessage());
                bt_login.setEnabled(loginFormState.isFieldsValid());
            }
        });

        return binding.getRoot();
    }
}