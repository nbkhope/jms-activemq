package nbk;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello World");

        Sender sender = new Sender();

        Boolean finished = false;
        while (!finished) {
            System.out.println("Send message? ");
            Integer input = scanner.nextInt();
            System.out.println("You chose: " + input);
            if (input == 1) {
                sender.sendMessage();
            }
            else {
                finished = true;
            }
        }

    }
}