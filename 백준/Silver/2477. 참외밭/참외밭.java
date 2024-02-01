import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()); // 1제곱미터 당 참외 개수
		
		int[] d = new int[6]; // 방향 배열
		int[] l = new int[6]; // 길이 배열
		
		int smallArea = 0; // 작은 사각형
		
		int[] countClock = {2, 3, 1, 4}; // 반시계 방향
		
		
		for(int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			d[i] = Integer.parseInt(st.nextToken());
			l[i] = Integer.parseInt(st.nextToken());
		}
		
		int comp;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 4; j++) {
				if(j ==3) comp = 2; // 반시계 배열의 마지막 값이면, 비교값을 2로 설정 (모두 탐색)
				else comp = countClock[j+1]; // 마지막 값이 아니면, 다음값으로 비교
				if(d[i] == countClock[j] && d[i+1] !=comp) { // 반시계로 돌고 있지 않다면 ?
					smallArea = l[i] * l[i+1];
				}
			}
		}
		// 모두 탐색해도 작은 사각형의 면적이 구해지지 않으면 배열의 첫 값과 끝값으로 구하기
		if (smallArea == 0) {
			smallArea = l[0] * l[5];
		} 
		
		// 큰 사각형의 면적은 방향 배열에서 한번씩 나온 값을 곱해준다.
		int bigArea = 1;
		int count[] = new int[6];
		for(int i =0; i < 6; i++) {
			count[d[i]]++;
		}
		for(int i =0; i < 6; i++) {
			if(count[d[i]] == 1) bigArea *= l[i];
		}
	
		System.out.println((bigArea - smallArea)*N);
	}
}
