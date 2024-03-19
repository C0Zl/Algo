import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static int N, M;
	public static int[] arr;
	public static boolean[] visited;
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = br.readLine().split(" ");
		
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		
		arr = new int[M];
		visited = new boolean[N];
		
		comb(0);
		System.out.println(sb);
	}
	
	private static void comb(int depth) {
		if (depth == M) {
			for (int i : arr) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			if (visited[i - 1]) continue;
			
			visited[i - 1] = true;
			arr[depth] = i;
			comb(depth + 1);
			
			visited[i - 1] = false;
		}
	}
}
