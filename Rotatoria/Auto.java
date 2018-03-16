public class Auto extends Thread {
    private int distanza;
    private Rotatoria r;
    private int direzione;

    Auto(int d, Rotatoria rr, int dd){
        distanza=d; r = rr; direzione=dd;
    }
    public void run(){
        try{
            while(distanza>0){
                distanza--;
                sleep((int)(Math.random()*50));
            }
            r.entraRotatoria(direzione);
            sleep((int)(Math.random()*200));
            r.esciRotatoria(direzione);

        }catch(InterruptedException e){}
    }
}
