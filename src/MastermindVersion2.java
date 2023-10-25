import java.util.*;

public class MastermindVersion2 extends GuessingGame{
    public static Map mapAll = new HashMap();
    public String s;
    public int exact;
    public int partial;
    public Set setFinal = new HashSet();
    public int count;

    public  char[] generateSecret(){
        mapAll.put(0, 'R');
        mapAll.put(1, 'G');//R, G, B, Y, O, P
        mapAll.put(2, 'B');
        mapAll.put(3, 'Y');
        mapAll.put(4, 'O');
        mapAll.put(5, 'P');
        Random rand = new Random();
        char[] secret = new char[4];
        Set set = new HashSet();
        for (int i = 0; i < 4; i++) {
            int r = random(6); // gets 0, 1, 2, 3, 4 ,5
            char z = (char) mapAll.get(r);
            secret[i] = z;
            set.add(z);
        }
        return secret;
    }
    public String getGuess() {
        continueLoop:
        while(true) {
            System.out.println("please enter your guess");
            s = input();
        char[] guess = s.toCharArray();
            if (guess == null || guess.length != 4) {
                System.out.println("invalid input");
                continue;
            }
            for (int i = 0; i < 4; i++) {
                if (!mapAll.containsValue(guess[i])) {
                    System.out.println("invalid input");
                    continue continueLoop;
                }
            }
            break;
        }
        return s;
    }
    public int computeExacts(char[] secret, char[] guess) {
        for (int i = 0; i < 4; i++) {
            if (guess[i] == secret[i]) {
                exact++;
                guess[i] = ' ';
            } else {
                setFinal.add(secret[i]);
            }
        }
        System.out.println("exact" + exact);
        return exact;
    }
    public int computePartials(char [] secret, char [] guess) {
        for (int i = 0; i < 4; i++) {
            if (setFinal.contains(guess[i])) {
                partial++;
                setFinal.remove(guess[i]);
            }
        }
        System.out.println("partial" + partial);
        return partial;
    }
    @Override
    public GameRecord play(){
        inputID();
        char[] secretArray =  generateSecret();
        while (true) {
            String guess = getGuess();
            char[] guessArray = guess.toCharArray();
            exact = computeExacts(secretArray, guessArray);
            partial = computePartials(secretArray, guessArray);
            System.out.println(secretArray);
            if(exact == 4) {
                System.out.println("congratulations");
                gameRecord.score = 10 - count;
                break;
            }
            count++;
            System.out.println("It is your " +count +" attempt");
            if(count== 10){
                gameRecord.score = 0;
                break;
            }
            reset();
        }
        reset();
        count = 0;
        return deepCopy();
    }
    private void reset(){
        exact = 0;
        partial = 0;
        setFinal.clear();
    }
@Override
    public boolean playNext() {
        System.out.println("A new player want to join or you want to play a new round?");
        String s = input();
        if (s.equals("yes")) {
            return true;
        }
        else {
            return false;
        }
    }
    public static void main(String[] args)  {
        MastermindVersion2 m = new MastermindVersion2();
        AllGamesRecord record = m.playAll();
        System.out.println(record);
        System.out.println("The average is "+ record.average());
        System.out.println("highGameList "+ record.highGameList(2));// or call specific functions of record
    }
}
