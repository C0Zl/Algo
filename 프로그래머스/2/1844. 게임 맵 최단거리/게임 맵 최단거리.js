function solution(maps) {
    const n = maps.length;
    const m = maps[0].length;
    
    // 사방 탐색을 위한 방향 설정 (상, 우, 하, 좌)
    const dr = [-1, 0, 1, 0];
    const dc = [0, 1, 0, -1];
    
    // 방문 여부를 체크하는 배열
    const visited = new Array(n).fill().map(() => Array(m).fill(false));
    
    // BFS 구현을 위한 큐 초기화
    const queue = [{
        r : 0,
        c : 0,
        dist: 1
    }]; // r, c, 이동거리
    visited[0][0] = true;
    
    // queue가 빌 때까지 반복
    while(queue.length) {
        const {r, c, dist} = queue.shift();
        
        // n,m에 도착 시 최단 경로
        if (r === n - 1 && c === m - 1) return dist;
        
        // 사방 탐색
        for(let d = 0; d < 4; d++) {
            const nr = r + dr[d];
            const nc = c + dc[d];
            
            // 방문할 수 없는 경우 처리
            if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc] || !maps[nr][nc]) continue;
            
            // 벽이 없으면 queue에 추가
            queue.push({r: nr, c: nc, dist: dist+1});
            visited[nr][nc] = true;
        }
    }
    
    return -1;
}
