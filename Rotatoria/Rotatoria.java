public class Rotatoria {
    private boolean[] occupata = {false,false,false,false};

    void entraRotatoria(int dir){
        synchronized (occupata){
            try {
                while (occupata[(dir + 1) % 4] == true)
                    occupata.wait();
                occupata[dir] = true;
                System.out.println(dir + " occupa la rotatoria");
            }catch(InterruptedException e){}
        }
    }
    void esciRotatoria(int dir){
        synchronized (occupata){
            occupata[dir] = false;
            occupata.notifyAll();
            System.out.println(dir + " libera la rotatoria");
        }
    }
}
