import java.util.ArrayList;

public class ObjSharing {
    public static void main(String[] args) {
        int NThreads = 5;
        SynchronizedArrayList sync = new SynchronizedArrayList();

        for (int i = 0; i < NThreads; i++)
            new Worker(sync,i).start();

        for (int j = 0; j < 6; j++) {
            try {
                Thread.sleep(1000);
            } catch (Exception ex) { }
            for(int k = 0; k < sync.get().size(); k++)
                System.out.println(sync.get().get(k));
        }
    }
}
