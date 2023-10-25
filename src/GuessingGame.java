import java.util.Random;
import java.util.Scanner;

public abstract class GuessingGame {
    Random rand = new Random();
    GameRecord gameRecord = new GameRecord();
    GameRecord gameRecordNew = new GameRecord();

    public AllGamesRecord playAll() {
        AllGamesRecord allGameRecord = new AllGamesRecord();
        boolean flag = true;
        while (flag == true){
            GameRecord gameRecord = play();
            allGameRecord.add(gameRecord);
            flag = playNext();
        }
        return allGameRecord;
    }
    //- GameRecord play()-- plays a game and returns a GameRecord
    //- boolean playNext() -- asks if the next game should be played (this will be called even to check if the first game should be played). The function should return a boolean.
    public abstract GameRecord play();
    public abstract boolean playNext();

    public String input(){
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        return s;
    }
    public int random(int num){
        int r = rand.nextInt(num);
        return r;
    }
    public void inputID() {
        System.out.println("please enter your name");
        String s = input();
        gameRecord.id = s;
        gameRecord.score = 0;
    }
    public GameRecord deepCopy(){
        gameRecordNew = new GameRecord();
        gameRecordNew.id = gameRecord.id;
        gameRecordNew.score = gameRecord.score;
        return gameRecordNew;
    }
}
