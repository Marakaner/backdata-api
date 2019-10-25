package net.alexander.backdata;

public class BackData {

    private static BackData instance;

    public BackData() {
        instance = this;
    }

    public static BackData getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        new BackData();
    }

}
