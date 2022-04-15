package com.msku.drugdosemonitoringsystem.services;

import com.msku.drugdosemonitoringsystem.controllers.response.DefaultResponse;
import com.msku.drugdosemonitoringsystem.entities.User;

public interface IPasswordService {

    DefaultResponse<User> forgotPassword(String username, String password);
    DefaultResponse<Boolean> resetPassword(String usernameid, String password);
    Boolean checkVerificationCode(String email, String verificationCode);

}
