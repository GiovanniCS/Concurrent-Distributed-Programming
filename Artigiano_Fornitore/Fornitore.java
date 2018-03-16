import java.util.concurrent.Callable;

public class Fornitore implements Callable<Risorsa> {
    public Risorsa call(){
        try {
            Thread.sleep((int)(Math.random()*300));
        }catch(InterruptedException e){}
        System.out.println("Risorsa computata :)" + Thread.currentThread().getName());
        return new Risorsa();
    }
}
