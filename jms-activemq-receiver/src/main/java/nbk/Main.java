package nbk;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Boolean finished = false;
        while (!finished) {
            System.out.println("Receive message? ");
            Integer input = in.nextInt();

            if (input == 1) {
                Receiver receiver = new Receiver();
                System.out.println("Receiving message");
                receiver.receiveMessage();
            }
            else {
                finished = true;
            }
        }
    }
}