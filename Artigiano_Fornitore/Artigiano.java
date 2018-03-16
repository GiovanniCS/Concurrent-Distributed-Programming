import java.util.concurrent.FutureTask;

public class Artigiano extends Thread{
    public static Fornitore f = new Fornitore();
    public void run() {
        try {
            FutureTask<Risorsa> ft = new FutureTask<Risorsa>(f);
            Thread t = new Thread(ft); t.start();
            for(int i=0; i<100; ++i)
                System.out.println("procedendo con il lavoro.. " + getName());
            Risorsa r = ft.get();
            System.out.println("completo il lavoro iniziato " + getName());
        } catch (Exception e) {}
    }
    public static void main(String[] s){
        Artigiano a1= new Artigiano(), a2 = new Artigiano(), a3 = new Artigiano();
        a1.start(); a2.start(); a3.start();
    }
}
//useless comment
//another comment