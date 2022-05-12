package fdrc.utils;

public class BackroundTask implements Runnable{

    @Override
    public void run() {
        printNumbers(50);
    }

    public void printNumbers(int upto) {
        for (int i = 0; i < upto; ++i) {
            System.out.println(i);
        }
    }

}

class ThreadClient{
    public static void main(String[] args) {
        new BackroundTask().run();
        printNumbers(50);
    }

    public static void printNumbers(int upto) {
        for (int i = 0; i < upto; i++) {
            System.out.println(i);
        }
    }
}