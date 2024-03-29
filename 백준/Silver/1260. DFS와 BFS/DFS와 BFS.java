import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static int N, M, V;
	public static int[][] adjMatrix;
	public static int[] visited;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		adjMatrix = new int[N+1][N+1];
		visited = new int[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjMatrix[a][b] = 1;
			adjMatrix[b][a] = 1;
		}
		
		visited[V] = 1;
		dfs(V);
		
		visited = new int[N+1];
		
		System.out.println();
		bfs();
	}

	public static void dfs(int node) {
		System.out.print(node + " ");
		
		for (int next = 1; next <=  N; next++) {
			// 방문 불가능 하다면
			if (visited[next] == 1 || adjMatrix[node][next] == 0) continue;
			
			// 방문 가능 하다면 ?
			visited[next] = 1;
			
			dfs(next);
		}
	}
	
	public static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		
		visited[V] = 1;
		q.offer(V);
		
		while(!q.isEmpty()) {
			int node = q.poll();
			System.out.print(node + " ");
			
			for (int next = 1; next <= N; next++) {
				if(visited[next] == 1 || adjMatrix[node][next] == 0) {
					continue;
				}
				
				visited[next] = 1;
				q.offer(next);
			}
		}
	}

	
}
