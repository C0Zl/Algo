import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// br, st, sb 선언 및 초기화
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int w, h, testCase, minDist = 0;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 블록의 가로 세로 길이 초기화
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		// 테스트 케이스 개수 입력 받기
		testCase = Integer.parseInt(br.readLine());

		// 위치와 길이 배열 선언
		int[] d = new int[testCase + 1];
		int[] l = new int[testCase + 1];

		// 테스트케이스 개수 + 1(동근)만큼 반복하며 위치와 길이입력 받기
		for (int i = 0; i <= testCase; i++) {
			st = new StringTokenizer(br.readLine());
			d[i] = Integer.parseInt(st.nextToken());
			l[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(calculate(d, l));
	}

	public static int calculate(int[] d, int[] l) {
		// 테스트케이스만큼 반복
		for (int i = 0; i < testCase; i++) {

			// 1. 동근과 상점이 같은 위치에 있는 경우
			if (d[i] == d[testCase]) {
				minDist += Math.abs(l[testCase] - l[i]);
			
			// 2. 동근과 상점이 마주보는 경우
			} else if (d[i] == 1 && d[testCase] == 2 || d[i] == 2 && d[testCase] == 1) {
				// 상, 하로 마주보는 경우
				minDist += (l[i] + l[testCase] <= 2 * w - (l[i] + l[testCase])) ? 
						l[i] + l[testCase] + h : 2 * w - (l[i] + l[testCase]) + h;
				
			} else if (d[i] == 3 && d[testCase] == 4 || d[i] == 4 && d[testCase] == 3) {
				// 좌, 우로 마주보는 경우
				minDist += (l[i] + l[testCase] <= 2 * h - (l[i] + l[testCase])) ? 
						l[i] + l[testCase] + w : 2 * h - (l[i] + l[testCase]) + w;
				
			// 3. 동근과 상점이 옆에 위치한 경우
			} else {
				if (d[i] % 2 == 1)
					minDist += l[testCase];
				else
					minDist += w - l[testCase];
				if (d[testCase] % 2 == 1)
					minDist += l[i];
				else
					minDist += h - l[i];
			}

		}
		return minDist;
	}
}
