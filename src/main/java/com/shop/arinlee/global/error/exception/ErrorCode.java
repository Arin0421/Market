package com.shop.arinlee.global.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {


    ALREADY_REGISTERED_MEMBER(400,"이미 존재하는 회원입니다."),
    MISMATCHED_PASSWORD(400,"비밀번호가 일치하지 않습니다."),
    NO_MATCHING_MEMBER(400,"없는 회원입니다.");
    MISMATCHED_PASSWORD(401, "패스워드가 일치하지 않습니다.");


    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private int status;
    private String message;

}