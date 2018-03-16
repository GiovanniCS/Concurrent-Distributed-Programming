class Acq{}
class Vin{}
class Aran{}
class Integ{ //non è possibile usare Integer nei blocchi synch perche solleva un' IllegalMonitorException quando si invoca notifyAll()
    int i=0;
}
public class Distributore {
    Integ parallelo= new Integ();
    Acq a = new Acq(); Vin v =new Vin(); Aran ar = new Aran();
    public void riempiBicchiere(int n,String s){
        try {
            //Ogni thread cerca di acquisire il lock sul tipo di bevanda scelta e una volta preso verifica se due altri clienti si stanno servendo al distributore
            //In questo modo serializzo la distribuzione di un tipo di bevanda e permetto al massimo a due clienti (con bevande differenti) di servirsi contemporaneamente
            if(s == "acqua"){
                synchronized (a) {
                    synchronized (parallelo){
                        while(parallelo.i == 2)
                            parallelo.wait();
                        (parallelo.i)++;
                    }
                    System.out.println("riempio il bicchiere del cliente " + n);
                    Thread.sleep((int) (Math.random() * 30));
                    synchronized (parallelo){
                        (parallelo.i)--;
                        parallelo.notifyAll();
                    }
                }
            }else if(s== "vino"){
                synchronized (v) {
                    synchronized (parallelo){
                        while(parallelo.i >= 2)
                            parallelo.wait();
                        (parallelo.i)++;
                    }
                    System.out.println("riempio il bicchiere del cliente " + n);
                    Thread.sleep((int) (Math.random() * 30));
                    synchronized (parallelo){
                        (parallelo.i)--;
                        parallelo.notifyAll();
                    }
                }
            }else{
                synchronized (ar) {
                    synchronized (parallelo){
                        while(parallelo.i >= 2)
                            parallelo.wait();
                        (parallelo.i)++;
                    }
                    System.out.println("riempio il bicchiere del cliente " + n);
                    Thread.sleep((int) (Math.random() * 30));
                    synchronized (parallelo){
                        (parallelo.i)--;
                        parallelo.notifyAll();
                    }
                }
            }
        }catch(InterruptedException e){}
    }
    class Cliente extends Thread{
        private int numero;
        private String s;
        Cliente(int i, String ss){numero=i; s = ss;}
        public void run(){
            System.out.println("Sono il cliente numero: " + numero + " e voglio bere " + s);
            Distributore.this.riempiBicchiere(numero,s);
            System.out.println("Sono il cliente numero: " + numero + " e ho bevuto " + s);
        }
    }
    public static void main(String[] s){
        Distributore d = new Distributore();
        int acqua=0, vino=0, aranciata=0;
        for(int i=0; i<100; ++i){
            int caso = (((int)((Math.random()*543*i)))%3);
            if(caso == 0){ acqua++; d.new Cliente(i,"acqua").start(); }
            else if(caso ==1){ vino++; d.new Cliente(i,"vino").start(); }
            else{ aranciata++; d.new Cliente(i,"aranciata").start(); }
        }
        System.out.println("Cosa hanno chiesto i clienti?");
        System.out.println("acqua: "+acqua+"\tvino: "+vino+"\taranciata: "+aranciata);
    }
}
