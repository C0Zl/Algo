function solution(friends, gifts) {
    // 전체 사람 수
    let n = friends.length;
    
    // 각 사람 별 인덱스 번호 저장
    let map = new Map();
    
    for(let i = 0; i < n; i++) {
        map.set(friends[i], i);
    }
    
    // 선물 주고 받은 내역 2차원 배열로 저장
    let history = new Array(n).fill().map(() => new Array(n).fill(0));
    
    for (gift of gifts) {
        // 주는 사람의 인덱스 sIdx, 받는 사람의 인덱스 rIdx
        const sIdx = map.get(gift.split(' ')[0]);
        const rIdx = map.get(gift.split(' ')[1]);
        
        // 선물 주고 받은 2차원 배열
        history[sIdx][rIdx]++;
    }
    
    console.log(history);
    
    // 총 선물을 받을 개수를 담을 배열
    const answer = new Array(n).fill(0);
    
    // 선물 주고 받은 기록 비교
    for (let j = 0; j < n; j++) {
        for (let k = j + 1; k < n; k++) {
            // 1. 주고받은 선물 개수가 같은 경우
            if (history[j][k] === history[k][j]) {
                // 선물 지수 계산
                let a = 0; let b = 0;
                
                let rowSum = 0; let colSum = 0;
                
                // 열, 행의 합 계산
                for (let num = 0; num < n; num++) {
                    colSum += history[num][j]; // 받은 선물 개수
                    rowSum += history[j][num]; // 준 선물 개수
                }
                
                a = rowSum - colSum;
                
                rowSum = 0; colSum = 0;
                
                for (let num = 0; num < n; num++) {
                    colSum += history[num][k]; // 받은 선물 개수
                    rowSum += history[k][num]; // 준 선물 개수
                }
                
                b = rowSum - colSum;
                
                if (a > b) {
                    answer[j]++;
                } else if (b > a) {
                    answer[k]++;
                }
            }
            
            // 2. 한명이 더 많이 선물은 준 경우
            else if (history[j][k] > history[k][j]) {
                answer[j]++;
            } else {
                answer[k]++;
            }
        }
    }
    
    let maxValue = 0;
    
    for(value of answer) {
        maxValue = maxValue < value ? value : maxValue;
    }
    
    return maxValue;
}