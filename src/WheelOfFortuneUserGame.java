import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class WheelOfFortuneUserGame extends GuessingGame{
    public int left = 3;
    boolean needNewGamePlayer = true;
    boolean needNewPhrase = true;
    List<String> phraseList = null;
    public String phrase;//String phrase
    public char[] hiddenArray;//para
    public char[] newArray;//para
    public char guess;
    public Map<Integer,Character> map = new HashMap<>();
    // returns the initial hidden phrase
    public void generateHiddenPhrase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) <= 'z' && phrase.charAt(i) >= 'a') {
                sb.append('*');
            } else if (phrase.charAt(i) <= 'Z' && phrase.charAt(i) >= 'A') {
                sb.append('*');
            } else {
                sb.append(phrase.charAt(i));
            }
        }
        String hiddenPhrase = sb.toString();
        System.out.println(hiddenPhrase);
        hiddenArray = hiddenPhrase.toCharArray();
    }
    public void processGuess() {
        char[] originalArray = phrase.toCharArray();
        newArray = Arrays.copyOf(hiddenArray, hiddenArray.length);
        for (int i = 0; i < phrase.length(); i++) {
            if (guess == phrase.charAt(i)) {
                newArray[i] = guess;
                /*                flag = true;*/
            }

            else {
            }
        }
        System.out.println(newArray);
        System.out.println(originalArray);
    }
    public boolean randomPhrase() {
        if(needNewPhrase == true){
            left = 3;
            needNewPhrase = false;
            // Get the phrase from a file of phrases
            try {
                phraseList = Files.readAllLines(Paths.get("phrases.txt"));
            } catch (IOException e) {
                System.out.println(e);
            }
            // Get a random phrased from the list
        }
        if(left != 0){
            int r = random(left);// gets 0, 1, or 2
            phrase = phraseList.get(r).toLowerCase();
            phraseList.remove(r);
            left--;
        }
        else{
            System.out.println("No more phrases");
            return false;
        }
        return true;
        //replaced with asterisks
    }
    @Override
    public GameRecord play(){
        if(needNewGamePlayer == true){
            needNewGamePlayer = false;
            inputID();
        }
            int count = 0;
            boolean a = randomPhrase();
//        if(!a){
//            return gameRecord;
//        }
            generateHiddenPhrase();
            Set set = new HashSet();
            while (true) {
                char[] ori = phrase.toCharArray();
                if (Arrays.equals(ori, hiddenArray)) {
                    System.out.println("congratulations");
                    gameRecord.score = 10 - count;
                    break;
                }
                if (count == 10) {
                    System.out.println("Sorry you lose");
                    break;
                }
                getGuess();

                if (set.contains(guess)) {
                    System.out.println("already used");
                    continue;
                }
                processGuess();
                set.add(guess);
                if (Arrays.equals(newArray, hiddenArray)) {
                    count++;
                    System.out.println("used " + count + " times");
                } else {
                    hiddenArray = newArray;
                }
            }
            // Deep copy GameRecord
            return deepCopy();
        }
    @Override
    public boolean playNext() {
        if (left == 0) {
            System.out.println("No more phrases");
            System.out.println("A new player want to join or you want to play a new round?");
            String s = input();
            if (s.equals("yes")) {
                needNewGamePlayer = true;
                needNewPhrase = true;
                return true;
            }
            else {
                return false;
        }
    }
        System.out.println("Do you want to play again?");
        String s = input();
        if(s.equals("no")) {
            System.out.println("A new player want to join or you want to play a new round?");
            String s1 = input();
            if (s1.equals("yes")) {
                needNewGamePlayer = true;
                needNewPhrase = true;
                return true;
            }
            else {
                return false;
            }
        }
        else if(s.equals("yes")) {
            return true;
        }
        return false;
    }
    public char getGuess(){
        while (true) {
            String s = input();
            String z = s.toLowerCase();
            guess = z.charAt(0);
            if (guess > 'z' || guess < 'a') {
                System.out.println("invalid input");
                continue;
            }

            break;
        }
        return guess;
    }
    public static void main(String[] args)  {
        WheelOfFortuneUserGame wf = new WheelOfFortuneUserGame();
        AllGamesRecord record = wf.playAll();
        System.out.println(record);
        System.out.println("The average is "+ record.average());
        System.out.println("highGameList "+ record.highGameList(2));// or call specific functions of record
    }
}
