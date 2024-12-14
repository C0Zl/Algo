function solution(k, dungeons) {
    // 최대 던전수를 담을 변수 maxDun;
    let maxDun = 0;
    
    // 던전들의 총 개수
    const dunNum = dungeons.length;
    
    // 순열을 구하기 위한 던전 index 배열
    let idx = 0;
    const idxArr = Array(dunNum).fill().map((v) => v = idx++);
    
    // 던전을 탐험할 순서를 담은 순열 조합
    let permArr = [];
    permutation([], idxArr, permArr);
    
    // 던전 탐험 경우의 수
    permArr.forEach((v) => {
        let cnt = 0;
        let current = k;
        
        v.forEach((index) => {
            // 탐험할 수 있는 경우
            if (current >= dungeons[index][0]) {
                current -= dungeons[index][1];
                cnt++;
            }
        })
        
        // 현재 던전 탐험 수보다 많이 탐험 했다면 갱신
        if (cnt > maxDun) maxDun = cnt;
    })
    
    return maxDun;
}

// 순열 구하는 함수 permutation
function permutation (perm, rests, output) {
    // 남은 배열의 길이가 0이 되면 output에 현재의 순열 push
    if (rests.length === 0) {
        output.push(perm);
        return
    }
    // 재귀적으로 순열 구하기
    rests.forEach((v, idx) => {
        const rest = [...rests.slice(0, idx), ...rests.slice(idx + 1)];
        permutation([...perm, v], rest, output);
    })
}