import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		if(N == 1) {
			System.out.println(1);
			return;
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 1;
		int max = 1;
		
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] >= arr[i - 1]) {
				cnt++;
				max = Math.max(max, cnt);
			} else {
				cnt = 1;
			}
		}
		
		cnt = 1;
		
		for (int i = arr.length - 1; i > 0; i--) {
			if (arr[i] <= arr[i - 1]) {
				cnt++;
				max = Math.max(max, cnt);
			} else {
				cnt = 1;
			}
		}
		
		System.out.println(max);
	}
}