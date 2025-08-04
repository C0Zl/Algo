import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        char[] chars;
        char[][] board;

        while (true) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();

            // end가 입력되면 반복 종료
            if (line.equals("end")) {
                System.out.println(sb);
                return;
            }

            chars = line.toCharArray();
            board = new char[3][3];

            // board에 게임 내용 입력
            for (int i = 0; i < chars.length; i++) {
//                System.out.println(chars[i]);
                board[i / 3][i % 3] = chars[i];
            }

            // 이긴 사람 확인
            String winner = winCheck(board);

            // 불가능한 경우에는 invalid 출력
            if (winner == "invalid" || !isValid(board, winner)) {
                sb.append("invalid").append("\n");
                continue;
            }

            // 끝났는지 확인
            if (isFull(board) || winner != null) sb.append("valid").append("\n");
            else sb.append("invalid").append("\n");
        }
    }

    // 가능한 경우인지 검증하는 함수
    public static boolean isValid(char[][] board, String winner) {
        int xCnt = 0, oCnt = 0;

        // x, o 개수 count
        for (char[] cArr : board) {
            for (char c : cArr) {
                if (c == 'X') xCnt++;
                else if (c == 'O') oCnt++;
            }
        }

        // 1) x의 개수가 o 개수보다 2개 이상 많거나, o의 개수가 더 많은 경우 불가능
        if (xCnt > oCnt + 1 || oCnt > xCnt) return false;

        // 2) o가 이겼는데, x가 더 많은 경우 불가능
        if (winner == "O" && oCnt < xCnt) return false;

        // 3) x가 이겼는데, o와 개수가 같거나 작은 경우 불가능
        if (winner == "X" && oCnt >= xCnt) return false;

        // 그 외에는 가능
        return true;
    }

    // 승리 검증 함수
    public static String winCheck(char[][] board) {
        boolean xWin = false;
        boolean oWin = false;

        // 1. 가로 체크
        for (int r = 0; r <= 2; r++) {
            // 가로가 같은 경우
            if (board[r][0] != '.' && board[r][0] == board[r][1] && board[r][1] == board[r][2]) {
                if (board[r][0] == 'X') xWin = true;
                else oWin = true;
            }
        }

        // 2. 세로 체크
        for (int c = 0; c <= 2; c++) {
            if (board[0][c] != '.' && board[0][c] == board[1][c] && board[1][c] == board[2][c]) {
                // 세로가 모두 같은 경우
                if (board[0][c] == 'X') xWin = true;
                else oWin = true;
            }
        }

        // 3. 대각선 체크
        // 대각선이 모두 같은 경우
        if (board[0][0] != '.' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'X') xWin = true;
            else oWin = true;
        } else if (board[0][2] != '.' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 'X') xWin = true;
            else oWin = true;
        }

        if (xWin && oWin) {
            return "invalid";
        } else if (!xWin && !oWin) {
            return null;
        } else {
            return xWin ? "X" : "O";
        }
    }

    // 게임판이 모두 찼는지 확인하는 함수
    public static boolean isFull(char[][] board) {
        for (char[] cArr : board) {
            for (char c : cArr) {
                if (c == '.') return false;
            }
        }

        return true;
    }
}

