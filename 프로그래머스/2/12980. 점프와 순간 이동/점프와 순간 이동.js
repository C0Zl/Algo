function solution(n) {
    // 최댓값으로 초기화
//     let dp = Array(n + 1).fill(Number.MAX_SAFE_INTEGER);
    
//     dp[0] = 0;
//     dp[1] = 1;
    
    let num = n;
    let answer = 1;
    
    while (num !== 1) {
        if (num % 2 === 0) {
            // 짝수인 경우
            num /= 2;
        } else {
            // 홀수인 경우
            num -= 1;
            answer++;
        }
    }
    // // n까지의 dp 배열 구하기
    // for(let i = 2; i <= n; i++) {
    //     if (i % 2 === 0) {
    //         // 짝수인 경우
    //         // i/2와 i-1 값을 비교
    //         if (dp[i / 2] > dp[i - 1] + 1) {
    //             dp[i] = dp[i - 1] + 1;
    //         } else {
    //             dp[i] = dp[i / 2];
    //         }
    //     } else {
    //         // 홀수인경우
    //         dp[i] = dp[i - 1] + 1;
    //     }
    // }

    return answer;
}