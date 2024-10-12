function solution(N, road, K) {
    // 최소 시간을 담을 minCost와 도로 정보를 담을 lines 변수
    const minTime = Array(N + 1).fill(Number.MAX_SAFE_INTEGER);
    const lines = Array(N + 1).fill().map(() => Array(0));
    
    road.forEach(([a, b, c]) => {
        lines[a].push({ to : b, cost : c });
        lines[b].push({ to : a, cost : c });
    })
    
    let queue = [{ to : 1, cost : 0 }];
    minTime[1] = 0;
    
    // queue에 요소가 없을 때까지 반복
   while (queue.length) {
        let { to, cost } = queue.shift();

        // 현재 위치에서 갈 수 있는 곳 확인
        lines[to].forEach(next => {
            const newCost = cost + next.cost; // 현재 경로까지의 비용 + 다음 경로 비용
            if (minTime[next.to] > newCost) {
                // 기존 시간보다 짧으면 갱신
                minTime[next.to] = newCost;
                queue.push({ to: next.to, cost: newCost });
            }
        });
    }
    
    return minTime.filter(time => time <= K).length;
}