import java.io.*;
import java.util.*;

public class Main {
    static final int[] dr = {0, 1, 0, -1}, dc= {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 보드의 크기와 사과 갯수
        int boardSize = Integer.parseInt(br.readLine());
        int appleCnt = Integer.parseInt(br.readLine());

        // 전체 보드
        int[][] board = new int[boardSize][boardSize];
        board[0][0] = 1;
        Deque<int[]> snake = new LinkedList<>();
        snake.add(new int[] {0, 0});

        // 사과 위치 입력
        for (int i = 0; i < appleCnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 사과 : 2
            board[r - 1][c - 1] = 2;
        }

        int dirCnt = Integer.parseInt(br.readLine());

        Queue<int[]> dirInfo = new LinkedList<>();

        // 방향 정보 입력
        for (int i = 0; i < dirCnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int sec = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);

            dirInfo.add(new int[] {sec, dir - '0'});
        }

        int currentTime = 0, d = 0;
        int[] nextDir = dirInfo.poll();

        while(true) {
            currentTime++;

            // 1. 이동
            int[] head = snake.getLast();
            int nr = head[0] + dr[d];
            int nc = head[1] + dc[d];

            // 이동 방향 검증
            if (nr < 0 || nr >= boardSize || nc < 0 || nc >= boardSize || board[nr][nc] == 1) {
                System.out.println(currentTime);
                return;
            }

            // snake에 현재 값 추가
            snake.add(new int[] {nr, nc});

            // 2. 이동할 위치에 사과 있는지 확인
            if (board[nr][nc] != 2) {
                int[] point = snake.poll();
                board[point[0]][point[1]] = 0;
            }
            board[nr][nc] = 1;

            if (nextDir != null && nextDir[0] == currentTime) {
                if (nextDir[1] == 'L' - '0') d = d == 0 ? 3 : d - 1;
                else d = (d + 1) % 4;

                nextDir = dirInfo.poll();
            }
        }
    }

}

