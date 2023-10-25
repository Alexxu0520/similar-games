import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
// a general class for all GuessingGame
public abstract class GuessingGame {
    private Random rand = new Random();
    protected GameRecord gameRecord = new GameRecord();
    protected GameRecord gameRecordNew = new GameRecord();
    // It can play a series of games, and return all game record
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
    public abstract GameRecord play();
    //- boolean playNext() -- asks if the next game should be played (this will be called even to check if the first game should be played). The function should return a boolean.
    public abstract boolean playNext();
    //get the input
    public String input(){
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        return s;
    }
    // get random
    public int random(int num){
        int r = rand.nextInt(num);
        return r;
    }
    //user inputID
    public void inputID() {
        System.out.println("please enter your name");
        String s = input();
        gameRecord.id = s;
        gameRecord.score = 0;
    }
    //deep copy to modify data member gameRecord
    public GameRecord deepCopy(){
        gameRecordNew = new GameRecord();
        gameRecordNew.id = gameRecord.id;
        gameRecordNew.score = gameRecord.score;
        return gameRecordNew;
    }
    // provide for sort
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuessingGame that = (GuessingGame) o;
        return Objects.equals(rand, that.rand) && Objects.equals(gameRecord, that.gameRecord) && Objects.equals(gameRecordNew, that.gameRecordNew);
    }
    // cast to string
    @Override
    public String toString() {
        return "GuessingGame{" +
                "rand=" + rand +
                ", gameRecord=" + gameRecord +
                ", gameRecordNew=" + gameRecordNew +
                '}';
    }
}
