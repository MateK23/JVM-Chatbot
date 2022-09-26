import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Talk to Chatbot ");
        System.out.println("Say something: ");
        Scanner scanner = new Scanner(System.in);
        String msg = scanner.next();

        Chatbot currentChatBot = new Chatbot();
        currentChatBot.Chat(msg);
    }
}