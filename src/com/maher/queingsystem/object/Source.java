package com.maher.queingsystem.object;

public class Source extends SystemObject {

    private int period;

    public Source(int period) {
        this.period = period;
    }

    @Override
    public boolean tryPushClient() {
        return false;
    }

    @Override
    public void tact(int number) {
        if (0 == number % period) {
            servedClientsCount++;
            if  (next != null && !next.tryPushClient()) {
                rejectedClientsCount++;
            }
        }
    }
}
