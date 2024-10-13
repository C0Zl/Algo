function solution(n, s, a, b, fares) {
    const minFare = Array(n+1).fill().map(() => Array(n+1).fill(Number.MAX_SAFE_INTEGER));
    
    // 자기 자신과의 거리는 항상 0
    for (let i = 1; i < n+1; i++) {
        minFare[i][i] = 0;    
    }
    
    // 연결리스트에 값 넣기
    fares.forEach((fare) => {
        minFare[fare[0]][fare[1]] = fare[2];
        minFare[fare[1]][fare[0]] = fare[2];
    })
    
    for (let k = 1; k <= n; k++) {
        for(let i = 1; i <= n; i++) {
            for (let j = 1; j <= n; j++) {
                // 우회하는 경우의 비용이 더 싼 경우
                if (minFare[i][j] > minFare[i][k] + minFare[k][j]) {
                    minFare[i][j] = minFare[i][k] + minFare[k][j]
                }
            }
        }
    }

    
    // 각자 가는 경우
    const initFare = minFare[s][a] + minFare[s][b];
    
    // 합승하는 경우
    let together = Number.MAX_SAFE_INTEGER;
    
    for (let i = 1; i <= n; i++) {
        currentFare = minFare[s][i] + minFare[i][a] + minFare[i][b];

        if (currentFare < together) {
            together = currentFare;
        }
    }
    
    return Math.min(together, initFare);
}