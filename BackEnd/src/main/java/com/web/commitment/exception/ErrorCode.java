package com.web.commitment.exception;

public enum ErrorCode {

    UNEXPECTED(0, "서버 내부 에러."),
    UNEXPECTED_USER(1000, "존재하지 않는 회원입니다."),
    MEMBER_DUPLICATED_EMAIL(1100, "이미 가입된 이메일입니다."),
    MEMBER_DUPLICATED_NICKNAME(1200, "이미 존재하는 닉네임입니다."),
    WRONG_EMAIL_CHECK_AUTH(1300, "인증되지 않는 유저입니다."),
    WRONG_PASSWORD(1400, "잘못된 비밀번호입니다."),
    MEMBER_WRONG_PLATFORM_TYPE(1500, "소셜 회원이 아닙니다. 코북 로그인을 이용해주세요."),
    MEMBER_NOT_SOCIAL_PLATFORM_TYPE(1600, "소셜 회원입니다. 소셜 로그인을 이용해주세요."),
    UNEXPECTED_CLUB(2000, "존재하지 않는 클럽입니다."),
    EXIST_CLUB_NAME(2100, "중복된 클럽 이름입니다."),
    ALREADY_APPLY_USER(2200, "이미 가입된 클럽입니다"),
    ILLEGAL_ACCESS_CLUB(2300, "권한이 없습니다"),
    ALREADY_PROCESS(2400, "이미 처리 되었거나 잘못된 정보입니다"),
    UNEXPECTED_POST(3000, "존재하지 않는 게시물입니다."),
    ILLEGAL_ACCESS_POST(3100, "잘못된 접근입니다"),
    UNEXPECTED_EVENT(4000, "존재하지 않는 이벤트입니다"),
    INVALID_GENRE(5000, "존재하지 않는 장르입니다"),
    UNEXPECTED_BOOK(6000, "존재하지 않는 책 정보입니다"),
    UNEXPECTED_READING(7000, "존재하지 않는 이벤트 정보입니다"),
    ILLEGAL_ACCESS_READING(7100, "잘못된 접근입니다"),
    UNEXPECTED_COMMENTS(8000, "존재하지 않는 댓글입니다"),
    ILLEGAL_ACCESS_COMMENT(8100, "댓글에 권한이 없습니다"),
    ILLEGAL_ACCESS_NOTIFICATION(9000, "알람에 대한 권한이 없습니다"),
    ALREADY_READ(9100, "이미 처리된 알람입니다");

    private final Integer code;
    private final String message;

    ErrorCode(final Integer code, final String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

