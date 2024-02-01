import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] arr = new char[100][100];
		StringBuilder sb = new StringBuilder();
		int[] answer = new int[10];

		for (int i = 1; i <= 10; i++) {
			br.readLine();
			for (int j = 0; j < arr.length; j++) {
				arr[j] = br.readLine().toCharArray();
			}
			sb.append("#" + i + " "+ (oddCheck(arr) > evenCheck(arr) ? oddCheck(arr) : evenCheck(arr)) + "\n");
		}	
		System.out.println(sb);
	}
	
	// 회문 길이가 홀수인 경우
	public static int oddCheck(char[][] arr) {
		int max = 1;
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr.length; c++) {
								
				int cnt = 1;
				int dr = 1;
				int dc = 1;
				
				// 범위 내에 있으면서, 세로로 대칭이면 계cnt값을 더해줌
				while(r - dr >= 0 && r + dr < 100) {
					if (arr[r + dr][c] == arr[r - dr][c]) {
						cnt += 2;
						dr++;
					} else {
						break;
					}
				}
				max = (cnt > max) ? cnt : max;
				cnt = 1;

				// 범위 내에 있으면서, 가로로 대칭이면 cnt값을 더해줌
				while(c - dc >= 0 && c + dc < 100) {
					if (arr[r][c + dc] == arr[r][c - dc]) {
						cnt += 2;
						dc++;
					} else {
						break;
					}
				}
				max = (cnt > max) ? cnt : max;
			}
		}
		return max;
	}
	
	// 회문 길이가 짝수인 경우
	public static int evenCheck(char[][] arr) {
		int max = 1;
		for (int r = 0; r < arr.length; r++) {
			for (int c = 0; c < arr.length; c++) {
								
				int cnt = 0;
				int dr = 1;
				int dc = 1;
				
				// 범위 내에 있으면서, 세로로 대칭이면 계cnt값을 더해줌
				while(r - dr >= 0 && r + (dr - 1) < 100) {
					if (arr[r + (dr - 1)][c] == arr[r - dr][c]) {
						cnt += 2;
						dr++;
					} else {
						break;
					}
				}
				max = (cnt > max) ? cnt : max;
				cnt = 0;
				
				// 범위 내에 있으면서, 가로로 대칭이면 cnt값을 더해줌
				while(c - dc >= 0 && c + dc - 1 < 100) {
					if (arr[r][c + dc - 1] == arr[r][c - dc]) {
						cnt += 2;
						dc++;
					} else {
						break;
					}
				}
				max = (cnt > max) ? cnt : max;
			}
		}
		return max;
	}
}
