package com.project.defne.resource.message_status;

/**
 * Created by Ceren A. @ 5/19/2019
 * While listening ${SPOT}
 */
public enum MESSAGE_STATUS {
    CANCELLED("CANCELLED"),
    NOT_SEND("NOT SEND"),
    ERROR_1("ERROR_1"),
    ERROR_2("ERROR_2"),
    ERROR_3("ERROR_3"),
    SEND("SEND");

    private final String value;

    MESSAGE_STATUS(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
