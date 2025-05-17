package com.example.User_Service.business;

import com.example.User_Service.domain.UpdateUserInfoRequest;
import com.example.User_Service.domain.UpdateUserInfoResponse;

public interface IUpdateUserInfo {
    UpdateUserInfoResponse updateUserInfo(UpdateUserInfoRequest request);
}
