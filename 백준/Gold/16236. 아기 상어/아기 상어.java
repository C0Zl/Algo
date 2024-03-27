import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;
	
	Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	// 아기상어가 들어 있는 전체 배열
	static int[][] arr;
	static int[] dr = {-1, 0, 0, 1}, dc = {0, -1, 1, 0}; 
	static int N, sec = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 공간의 크기 초기화
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		int r = 0, c = 0;
		
		// 배열 입력 받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				int k = Integer.parseInt(st.nextToken());
				
				// 초기 아기상어 위치 찾기
				if (k == 9) {
					r = i; c = j;
					arr[i][j] = 0;
					continue;
				}
				arr[i][j] = k;
			}
		}
		
		// bfs로 들어감
		hunt(r, c);
		
		System.out.println(sec);
	}
	
	private static void hunt(int r, int c) {
		// bfs로 찾아
		Queue<Node> que = new LinkedList<>();
		int[][] dist = new int[N][N];
		List<Node> list = new ArrayList<>();
		que.add(new Node(r, c));
		
		int count = 0, size = 2;
		int x, y, nr, nc;
		
		boolean[][] visited = new boolean[N][N];
		visited[r][c] = true;
		
		next : while(!que.isEmpty()) {
			Node node = que.poll();
			x = node.x;
			y = node.y;
			
			// 사방 탐색하면서
			for (int d = 0; d < dr.length; d++) {
				nr = x + dr[d];
				nc = y + dc[d];
				
				// 나보다 큰 물고기는 못지나감 (continue)
				if (nr < 0 || nr >= N || nc < 0 || nc >= N 
						|| arr[nr][nc] > size || visited[nr][nc]) continue;

				visited[nr][nc] = true;
				
				if (node == null) dist[nr][nc] = 0;
				else dist[nr][nc] += dist[x][y] + 1;
				
				// 나보다 작은 물고기는 먹고 이동 처리하기
				if (arr[nr][nc] > 0 && arr[nr][nc] < size) {
					list.add(new Node(nr, nc));
				}
				
				que.add(new Node(nr, nc));
			}
			
			if (que.isEmpty() && !list.isEmpty()) {
				int minDist = Integer.MAX_VALUE;
				int minR = Integer.MAX_VALUE;
				int minC = Integer.MAX_VALUE;
				
				for (Node n : list) {
					if (dist[n.x][n.y] < minDist) {
						minDist = dist[n.x][n.y];
						minR = n.x;
						minC = n.y;
					} else if (dist[n.x][n.y] == minDist) {
						if (n.x < minR) {
							minR = n.x;
							minC = n.y;
						} else if (n.x == minR) {
							if (n.y < minC) {
								minR = n.x;
								minC = n.y;
							}
						}
					}
				}
				
				// - 먹은 칸 빈칸 만들기
				arr[minR][minC] = 0;
				// - count++
				count++;
				
				// - count랑 아기상어 크기 비교해서 같아지면 크기++ 
				if (count >= size) {
					size++;
					count = 0;
				}
				
				// - que과 visited, dist 초기화
				sec += dist[minR][minC];
				dist = new int[N][N];
				
				visited = new boolean[N][N];
				visited[minR][minC] = true;
				
				que.clear();
				que.add(new Node(minR, minC));
				list.clear();
			}
			
		}
		// 더 이상 사냥 할 수 없으면 return
		return;
		
	}
}
