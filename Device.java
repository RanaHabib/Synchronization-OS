package com.company;

public class Device extends Thread {
   public String name, type;
   public int connectionID;
   public  Router router;

   public Device(String name, String type, Router router) {
        this.name = name;
        this.type = type;
        this.router = router;
        connectionID = 1;
    }

    @Override
    public void run() {
        try {
            router.arrived(this);
            router.semaphore.wait(this);
            connectionID = router.connect(this);
            System.out.println("Connection " + connectionID + ": " + name + " Occupied");
            activity();
            router.disconnect(this);
            router.semaphore.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void activity() throws InterruptedException {
        System.out.println("Connection " + connectionID + ": " + name + " Performs online activity");
        sleep(2000);
    }
}
