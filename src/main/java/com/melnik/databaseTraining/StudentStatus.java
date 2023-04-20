package com.melnik.databaseTraining;


public enum StudentStatus {
    GRADUATED(0),
    CURRENT(1),
    EXPELLED(2),
    VACATION(3),
    UNKNOWN(4);

    private final int num;

    StudentStatus(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

}
