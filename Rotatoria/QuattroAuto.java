public class QuattroAuto {
    public static void main(String[] s){
        Rotatoria r=new Rotatoria();
        Auto[] a = new Auto[4];
        for(int i=0; i<4; ++i){
            a[i]=new Auto(10,r,i);
            a[i].start();
        }
        for(int i =0;i<4;i++) {
            try {
                a[i].join();
            }catch(InterruptedException e){}
        }
        System.out.println("FINITO");
    }
}
