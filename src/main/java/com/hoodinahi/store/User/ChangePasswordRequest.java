package com.hoodinahi.store.User;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    String currentPassword;
    String newPassword;
}
