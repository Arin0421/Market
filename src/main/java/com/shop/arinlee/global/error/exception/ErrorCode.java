package com.shop.arinlee.global.error.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {


    ALREADY_REGISTERED_MEMBER(400,"이미 존재하는 회원입니다."),
    MISMATCHED_PASSWORD(400,"비밀번호가 일치하지 않습니다."),
    LOGIN_ERROR(400,"로그인 오류"),
    NO_REP_IMAGE(400,"대표 이미지를 등록해주세요."),
    ADD_ITEM_ERROR(400,"아이템 등록 중 오류가 발생했습니다."),
    NO_MATCHING_MEMBER(400,"없는 회원입니다.");



    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private int status;
    private String message;

}