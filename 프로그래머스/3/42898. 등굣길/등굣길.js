function solution(m, n, puddles) {
    const map = new Array(n+1).fill().map(() => new Array(m+1).fill(0));

    // 물에 잠긴 지역은 접근할 수 없기 때문에 -1으로 초기화
    for ([x, y] of puddles) {
        map[y][x] = -1;
    }
        
    // 시작점을 1로 초기화 하기
    map[1][1] = 1;
    
    // map 순회
    for (let i = 1; i <= m; i++) {
        for (let j = 1; j <= n; j++) {
            // 물에 잠긴 지역은 넘어가기
            if (i === 1 && j === 1 || map[j][i] === -1) continue;
            
            const up = map[j-1][i];
            const left = map[j][i-1];
            
            map[j][i] = ((up <= 0 ? 0 : up) + (left <= 0 ? 0 : left)) % 1000000007;
        }
    }
    
    return map[n][m] ;
}