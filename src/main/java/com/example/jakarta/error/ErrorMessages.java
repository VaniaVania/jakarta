package com.example.jakarta.error;

public enum ErrorMessages {
    FILE_NOT_FOUND("Файл не знайдено"),
    MEMBER_NOT_FOUND("Сторінка учасника не знайдена");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
