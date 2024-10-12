function solution(n, edges) {
    // 1번 노드까지 가장 짧은 거리를 담을 배열 minDist, 간선 정보를 담을 lines
    minDist = Array(n + 1).fill(Number.MAX_SAFE_INTEGER);
    lines = Array(n + 1).fill().map(() => Array(0));
    
    edges.forEach(edge => {
        let [a, b] = edge;
        lines[a].push(b);
        lines[b].push(a);
    })
    
    // 1번 노드와의 거리 정보를 queue에 추가
    let queue = [{to : 1, dist : 0}];
    minDist[1] = 0;
    
    while(queue.length) {
        let {to, dist} = queue.shift();
        
        lines[to].forEach(next => {
            newDist = dist + 1;
            
            // 거리가 더 작으면 갱신
            if(minDist[next] > newDist) {
                minDist[next] = newDist;
                queue.push({to : next, dist : newDist});
            }

        })
    }
    
    minDist[0] = 0;
    console.log(minDist)
    const maxDist = Math.max(...minDist);
    return minDist.filter((dist) => dist === maxDist).length;
}