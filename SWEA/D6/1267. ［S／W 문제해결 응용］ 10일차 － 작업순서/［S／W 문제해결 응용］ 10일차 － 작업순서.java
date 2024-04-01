import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	public static String[] cook = {"", "재료구매", "양념장만들기", "고기재우기", "고기손질", "제육볶음만들기", "식사", "뒷정리", "채소손질", "밥하기"};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			sb.append("#" + tc + " ");
			st = new StringTokenizer(br.readLine());
			
			int V = Integer.parseInt(st.nextToken()); // 정점 수
			int E = Integer.parseInt(st.nextToken()); // 간선 수 (방향 O)
			
			st = new StringTokenizer(br.readLine());
			
			int[][] adj = new int[V+1][V+1]; // 정점 번호가 1번부터 시작
			int[] degree = new int[V+1]; // 진입차수 저장
			
			for (int e = 0; e < E; e++) {
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				adj[A][B] = 1; // 가중치가 따로 없기 때문에 1로 표기, 유향이니 반대 처리 x
				degree[B]++;
			}
			
			Queue<Integer> queue = new LinkedList<>();
			
			// 위상정렬 구현 1단계
			// 진입차수 0인 정점 넣어
			for (int i = 1; i < V+1; i++) {
				if (degree[i] == 0)
					queue.offer(i);
			}
			
			// 2단계
			// 큐가 공백상태가 될 때까지 돌린다.
			while(!queue.isEmpty()) {
				// 2-1. 하나 꺼내
				int n = queue.poll();
				sb.append(n + " ");
				// 2-2. 연결된 간선 제거
				for (int i = 0; i < V+1; i++) {
					if(adj[n][i] == 1) {
						degree[i]--; // 진입차수 하나 깎음
						
						// 2-3. 진입차수가 새롭게 0이 되면 큐에 넣어
						if (degree[i] == 0) {
							queue.offer(i);
						}
					}
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	
}
