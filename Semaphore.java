package com.company;

public class Semaphore {
     int value;

    public Semaphore(int value) {
        this.value = value;
    }

    public synchronized void wait(Device device) throws InterruptedException {
        value--;
        if (value < 0) {
            System.out.println(device.name + " (" + device.type + ")" + " arrived and waiting");
            wait();

        }
        else{
            System.out.println( device.name +" (" + device.type + ")" +" arrived");
        }

        device.router.connect(device);
    }

    public synchronized void signal() {
        value++;
        if (value <= 0) {
            notify();
        }
    }
}
