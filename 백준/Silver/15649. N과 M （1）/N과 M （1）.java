import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static int N, M;
	public static StringBuilder sb = new StringBuilder();
	public static boolean[] visited;
	public static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		visited = new boolean[N];
		arr = new int[M];
		
		dfs(0);
		System.out.println(sb);
	}
	
	public static void dfs(int d) {
		if (d == M) {
			for (int num : arr) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		
		for (int next = 0; next < N; next++) {
			if (visited[next]) continue;
			
			visited[next] = true;
			arr[d] = next + 1;
			dfs(d + 1);
			visited[next] = false;
		}
	}
}
