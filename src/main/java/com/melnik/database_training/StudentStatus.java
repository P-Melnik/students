package com.melnik.database_training;


public enum StudentStatus {
    UNKNOWN(0),
    GRADUATED(1),
    CURRENT(2),
    EXPELLED(3),
    VACATION(4);

    private final int num;

    StudentStatus(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

}
