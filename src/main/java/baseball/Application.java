package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static List<Integer> computer; //컴퓨터의 숫자
    public static List<Integer> player; //플레이어의 숫자
    public static boolean CONTINUE_GAME = true; //재시작, 종료 구분하는 변수
    public static boolean SUCCESS; //게임 종료 여부 확인하는 변수

    public static void main(String[] args) {

        gameStart(); //게임 시작 문구 출력

        while (CONTINUE_GAME) {
            game();
        }
    }

    private static void game() {

        SUCCESS = false;
        setComputerNum(); //컴퓨터의 숫자 세팅

        //숫자 3개를 다 맞힐 때까지 반복
        while (!SUCCESS) {
            setPlayerNum(); //플레이어의 숫자 세팅

            int ball = 0; //"볼" 개수
            int strike = 0; //"스트라이크" 개수
            for (int i = 0; i < 3; i++) {
                if (player.contains(computer.get(i))) { //같은 숫자가 있고
                    if (player.get(i) == computer.get(i)) { //같은 위치에 있다면
                        strike++;
                    } else { //다른 위치에 있다면
                        ball++;
                    }
                }
            }

            printResult(ball, strike); //결과 출력

        }
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료"); //숫자 3개 다 맞혔다면 게임 종료
        checkRestart(); //재시작, 종료 여부 확인
    }

    private static void checkRestart() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String answer = Console.readLine();

        if (answer.equals("1")) {
            CONTINUE_GAME = true;
        }
        if (answer.equals("2")) {
            CONTINUE_GAME = false;
        }
        if (!answer.equals("1") && !answer.equals("2")) { //플레이어가 잘못된 입력을 한 경우
            throw new IllegalArgumentException();
        }
    }

    private static void printResult(int ball, int strike) {
        if (strike == 3) {
            System.out.println("3스트라이크");
            SUCCESS = true;
        }
        if (ball == 0 && strike == 0) {
            System.out.println("낫싱");
        }
        if (ball != 0 && strike == 0) {
            System.out.println(ball + "볼");
        }
        if (ball == 0 && strike != 0 && strike != 3) {
            System.out.println(strike + "스트라이크");
        }
        if (ball != 0 && strike != 0) {
            System.out.println(ball + "볼 " + strike + "스트라이크");
        }
    }

    //플레이어의 숫자 세팅하는 함수
    private static void setPlayerNum() throws IllegalArgumentException {
        player = new ArrayList<>();
        System.out.print("숫자를 입력해주세요 : ");
        char[] player_chArr = Console.readLine().toCharArray();
        for (char c : player_chArr) {
            int num = c - '0';
            //서로 다른 수가 아닌 경우
            if (player.contains(num)) {
                throw new IllegalArgumentException();
            } else {
                player.add(num);
            }
        }
        //숫자 3개가 아닌 경우
        if (player.size() != 3) throw new IllegalArgumentException();
        //숫자가 아닌 경우
        for (int i = 0; i < 3; i++) {
            if (!(1 <= player.get(i) && player.get(i) <= 9)) {
                throw new IllegalArgumentException();
            }
        }
    }

    //컴퓨터의 숫자 세팅하는 함수
    private static void setComputerNum() {
        computer = new ArrayList<>();
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
    }

    private static void gameStart() {
        System.out.println("숫자 야구 게임을 시작합니다.");
    }
}

