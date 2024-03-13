import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[] mArr = new int[M + 1];
		int ans = 1;
		
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0; i < M; i++) {
			mArr[i] = Integer.parseInt(br.readLine());
		}
		
		// 마지막 고정석 길이를 구하기 위해 더해줌
		mArr[M] = N + 1;
		
		// 자유석 개수만큼 자르기 위한 인덱스와 자유석 최대 길이를 구하기 위한 maxlen
		int idx = 0, maxLen = -1;
		
		int arrLen;
		for (int i = 0; i < mArr.length; i++) {
			arrLen = mArr[i] - (idx + 1);
			idx = mArr[i];
			
			//기존 최대 길이보다 긴 배열이면 최댓값 갱신
			if (maxLen < arrLen) maxLen = arrLen;
			
			stack.add(arrLen);
		}
		
		// 최대 길이가 0이거나 (고정석만 있는 경우)
		// 최대 길이가 1인경우 (고정석 사이 자유석 1개) 1 출력 후 종료
		if (maxLen <= 1) {
			System.out.println(1);
			return;
		}
		
		int[] dp = new int[maxLen];
		
		// dp의 1과 2값을 지정해준 후 점화식 이용
		dp[0] = 1; dp[1] = 2;
		
		for (int i = 2; i < maxLen; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		while(!stack.isEmpty()) {
			int tmp = stack.pop();
			if (tmp == 0) {
				continue;
			}
			ans *= dp[tmp - 1];
		}
		
		System.out.println(ans);
	}
}
