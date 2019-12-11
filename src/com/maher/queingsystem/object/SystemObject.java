package com.maher.queingsystem.object;

public abstract class SystemObject {

    protected int servedClientsCount = 0;
    protected int rejectedClientsCount = 0;
    protected SystemObject next = null;
    protected SystemObject previous = null;

    public abstract boolean tryPushClient();
    public abstract void tact(int number);

    public SystemObject getNext() {
        return next;
    }

    public void setNext(SystemObject object) {
        this.next = object;
        if (object != null && object.getPrevious() != this) {
            object.setPrevious(this);
        }
    }

    public SystemObject getPrevious() {
        return previous;
    }

    public void setPrevious(SystemObject object) {
        this.previous = object;
        if (object != null && object.getNext() != this) {
            object.setNext(this);
        }
    }

    public int getServedClientsCount() {
        return servedClientsCount;
    }

    public int getRejectedClientsCount() {
        return rejectedClientsCount;
    }
}
