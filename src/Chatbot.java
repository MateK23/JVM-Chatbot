import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Chatbot {
    private String name;
    private String surname;
    private boolean identified = false;
    private boolean firstTimeAsking = true;
    private boolean botsTurn = false;
    private boolean saidHello = false;


    public void Chat(String msg)
    {
        msg = msg.toLowerCase();

        if (botsTurn){
            int randomNum = ThreadLocalRandom.current().nextInt(0, 5);
            switch (randomNum) {
                case 1:
                    if (identified){
                        System.out.println("You are looking good today "+name+ ". Would you like to ask me something?");
                    }else {
                        Print("You are looking good today ");
                    }
                    botsTurn = false;
                    break;
                case 2:
                    Print("LOL");
                    break;
                default:
                    Print("I need to go, Good bye.");
            }
        }
        else {
            switch (msg) {
                case "hi":
                case "ola":
                case "gamarjoba":
                case "hello":
                    ChatAskName();
                    botsTurn = true;
                    break;
                case "bye":
                case "good bye":
                case "goodbye":
                case "byebye":
                case "good night":
                case "goodnight":
                case "gn":
                    Print("Good bye.");
                    break;
                default:
                    Print("Sorry, I can't respond to that, try something else");
                    Chat(ChatWaitForInput());
            }
        }
    }

    private void ChatAskName() {
        if (saidHello){
            System.out.println("Didn't we already greet? Bit weird but sure hello. ");

            System.out.println("So do you have anything else to say? ");
            Chat(ChatWaitForInput());
        }
        else {
            if (!firstTimeAsking){
                System.out.println("So again, what's your name? ");
                Scanner scanner = new Scanner(System.in);
                name = scanner.next();
            }
            else {
                System.out.println("Hello what's your name? ");
                Scanner scanner = new Scanner(System.in);
                name = scanner.next();
            }

            firstTimeAsking = false;


            System.out.println("And what's your surname? ");
            Scanner scanner2 = new Scanner(System.in);
            surname = scanner2.next();

            System.out.println("So your name is "+ name + " and your surname is "+ surname+ ", is that correct?");
            Scanner scanner3 = new Scanner(System.in);
            String answer = scanner3.next();
            ChatRespondToQuestion(answer);


        }
    }

    private String ChatWaitForInput(){
        Scanner scanner = new Scanner(System.in);
        String userResponse = scanner.next();
        return userResponse;
    }

    private void ChatRespondToQuestion(String answer){
        answer = answer.toLowerCase();
        if (answer.equals("yes")){
            System.out.println("Nice to meet you "+name+". I am Chatbot, homework for first lecture of JVM.");
            identified = true;
            saidHello = true;
            if(ChatAskQuestion("Would you like to ask a question?", "Sure, go ahead", "Ah ok then. Good bye.")){
                botsTurn = false;
                Chat(ChatWaitForInput());
            }
            else {

            }
        }
        else if (answer.equals("no")){
            ChatAskName();
        }
        else {
            System.out.println("Hm, don't know that word.. Say yes or no please. ");
            ChatRespondToQuestion(ChatWaitForInput());
        }
    }

    private void ChatRespondToQuestion(String answer, String yesResponse, String noResponse){
        answer = answer.toLowerCase();
        if (answer.equals("yes")){
            System.out.println(yesResponse);
        }
        else if (answer.equals("no")){
            System.out.println(noResponse);
        }
        else {
            System.out.println("Hm, don't know that word.. Say yes or no please. ");
            ChatRespondToQuestion(ChatWaitForInput());
        }
    }

    private boolean ChatAskQuestion(String question, String yesResponse, String noResponse){
        System.out.println(question);
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();

        answer = answer.toLowerCase();
        if (answer.equals("yes")){
            System.out.println(yesResponse);
            return true;
        }
        else if (answer.equals("no")){
            System.out.println(noResponse);
            return false;
        }
        else {
            System.out.println("Hm, I don't understand.. Say yes or no please. ");
            ChatAskQuestion("(yes/no): ", yesResponse, noResponse);
            return false;
        }
    }

    private void Print(String msg){
        System.out.println(msg);
    }
}
