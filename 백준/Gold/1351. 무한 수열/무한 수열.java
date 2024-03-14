import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	private static long N, P, Q;
	
	private static Map<Long, Long> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		map.put(0l, 1l);
		
		N = Long.parseLong(st.nextToken());
		P = Long.parseLong(st.nextToken());
		Q = Long.parseLong(st.nextToken());
		
		System.out.println(dp(N));
	}
	
	public static long dp(long i) {
		if (map.containsKey(i)) return map.get(i);
		long ans = dp(i / P) + dp(i / Q);
		map.put(i, ans);
		return ans;
	}
}
