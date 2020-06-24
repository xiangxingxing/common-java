import com.levi.basic.CollectionMaker;
import com.levi.basic.thread2.Bank;

public class BasicTest {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 1000;


    public static void main(String[] args) {
        //EqualTest();


        //StaticConstructor test = new StaticConstructor();
        //CollectionMaker.ConvertArrayList2List();

        bankTest();
    }

    public static void bankTest(){
        var bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++){
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true){
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int)(DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            var t = new Thread(r);
            t.start();
        }
    }


}
