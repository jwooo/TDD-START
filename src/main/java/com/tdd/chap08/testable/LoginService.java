package com.tdd.chap08.testable;

import com.tdd.chap08.auth.Customer;
import com.tdd.chap08.auth.CustomerRepository;
import com.tdd.chap08.auth.LoginResult;

public class LoginService {
    private AuthService authService = new AuthService();
    private CustomerRepository customerRepository;

    public LoginService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public LoginResult login(String id, String pw) {
        int resp = authService.authenticate(id, pw);
        if (resp == -1) return LoginResult.badAuthKey();

        if (resp == 1) {
            Customer c = customerRepository.findOne(id);
            return LoginResult.authenticated(c);
        } else {
            return LoginResult.fail(resp);
        }
    }
}
