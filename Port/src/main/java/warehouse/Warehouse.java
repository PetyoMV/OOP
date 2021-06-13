package warehouse;

import port.Package;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Warehouse {

    private ConcurrentLinkedQueue<Package> packages;
    private Distributor distributor;
    private int id;
   public Warehouse(int id){
       this.id = id;
       packages = new ConcurrentLinkedQueue<>();
       distributor = new Distributor(this,"Distributor "+id);
       new Thread(distributor).start();
    }

    public void addPackage(Package p){
        packages.offer(p);
        synchronized (this) {
            notifyAll();
        }
    }

    public Package removePackage(){
        System.out.println(distributor.getName()+"- Removing package");
        return packages.poll();
    }

    public boolean isEmpty(){
        return packages.isEmpty();
    }

    public synchronized void deliver() {
        while (isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("interrupted deliverer -" + e.getMessage());
            }
        }
        removePackage();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("thread sleep interrupted -" + e.getMessage());
        }
    }
}
