package com.maher.queingsystem.object;

public class Operator extends SystemObject {

    private double rejectProbability;
    private boolean isFree = true;

    public Operator(double rejectProbability) {
        this.rejectProbability = rejectProbability;
    }

    @Override
    public boolean tryPushClient() {
        if (isFree) {
            isFree = false;
            return true;
        }
        return false;
    }

    @Override
    public void tact(int number) {
        if (!isFree && Math.random() > rejectProbability) {
            isFree = true;
            servedClientsCount++;
            if (next != null && !next.tryPushClient()) {
                rejectedClientsCount++;
            }
        }
    }
}
