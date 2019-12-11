package com.maher.queingsystem.object;

public class Queue extends SystemObject {

    private double averageClientsCount = 0;
    private int capacity;
    private int clientsCount = 0;

    public Queue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be positive number");
        }
        this.capacity = capacity;
    }

    public double getAverageClientsCount() {
        return averageClientsCount;
    }

    @Override
    public boolean tryPushClient() {
        if (clientsCount < capacity) {
            if (0 == clientsCount) {
                if (next != null && !next.tryPushClient()) {
                    clientsCount++;
                }
            } else {
                clientsCount++;
            }
            return true;
        }
        return false;
    }
    @Override

    public void tact(int number) {
        averageClientsCount = averageClientsCount * ((double)number / (number + 1)) + (double)clientsCount / (number + 1);
        if (clientsCount > 0 && next != null && next.tryPushClient()) {
            clientsCount--;
        }
    }
}
