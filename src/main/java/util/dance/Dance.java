package util.dance;

public class Dance {

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.start();
        dog = new Dog();
        dog.start();
    }
}

class Dog extends Thread {
     static String propertiesName2 = "ok2";
    //private String propertiesName3 = "ok3";

    @Override
    public void run() {
        String td = Thread.currentThread().toString();
//        System.err.printf(td+"%d|start\n" ,System.nanoTime());
        System.err.printf(td+"%d|name:%s\n" ,System.nanoTime(),getPName());
        System.err.printf(td+"%d|end\n\n" ,System.nanoTime());
    }

    public  String  getPName(){
        synchronized (propertiesName2){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return propertiesName2;
        }
    }
}

