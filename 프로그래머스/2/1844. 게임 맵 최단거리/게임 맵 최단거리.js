function solution(maps) {
    const n = maps.length;
    const m = maps[0].length;
    
    // 사방 탐색을 위한 방향 설정 (상, 우, 하, 좌)
    const dr = [-1, 0, 1, 0];
    const dc = [0, 1, 0, -1];
    
    // 방문 여부를 체크하는 배열
    const visited = new Array(n).fill().map(() => Array(m).fill(false));
    
    // BFS 구현을 위한 큐 초기화
    const queue = [[0, 0, 1]]; // [r, c, 이동 거리]
    visited[0][0] = true; // 시작점 방문 처리
    
    while (queue.length) {
        const [r, c, dist] = queue.shift();
        
        // 목적지에 도착하면 바로 반환 (최소 거리)
        if (r === n - 1 && c === m - 1) {
            return dist;
        }
        
        // 사방 탐색
        for (let d = 0; d < 4; d++) {
            const nr = r + dr[d];
            const nc = c + dc[d];
            
            // 맵의 범위를 벗어나지 않고, 아직 방문하지 않았으며, 이동 가능한 칸인 경우
            if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && maps[nr][nc] === 1) {
                visited[nr][nc] = true; // 방문 처리
                queue.push([nr, nc, dist + 1]); // 다음 좌표와 거리 정보를 큐에 삽입
            }
        }
    }
    
    // 목적지에 도착할 수 없는 경우 -1 반환
    return -1;
}
