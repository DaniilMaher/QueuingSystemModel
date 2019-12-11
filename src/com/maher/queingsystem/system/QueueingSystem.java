package com.maher.queingsystem.system;

import com.maher.queingsystem.object.Queue;
import com.maher.queingsystem.object.SystemObject;

public class QueueingSystem {

    private int tactsCount;
    private SystemObject lastObject;

    public QueueingSystem(int tactsCount, SystemObject lastObject) {
        this.tactsCount = tactsCount;
        this.lastObject = lastObject;
    }

    public void tact(int number) {
        SystemObject object = lastObject;
        while (object != null) {
            object.tact(number);
            object = object.getPrevious();
        }
    }

    public void doModeling() {
        for (int i = 0; i < tactsCount; i++) {
            tact(i);
        }
    }

    public double calculateRejectProbability() {
        int rejectedClients = 0;
        SystemObject object = lastObject;
        while (null != object.getPrevious()) {
            rejectedClients += object.getRejectedClientsCount();
            object = object.getPrevious();
        }
        rejectedClients += object.getRejectedClientsCount();
        return (double)rejectedClients / object.getServedClientsCount();
    }

    public double calculateAbsoluteBandwidth() {
        return (double)lastObject.getServedClientsCount() / tactsCount;
    }

    public double calculateAverageQueue() {
        SystemObject object = lastObject;
        while (null != object && object.getClass() != Queue.class) {
            object = object.getPrevious();
        }
        if (object != null) {
            return ((Queue)object).getAverageClientsCount();
        }
        return 0;
    }
}
