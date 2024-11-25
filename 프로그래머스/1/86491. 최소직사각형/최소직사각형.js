function solution(sizes) {
    // 정답을 담을 answer 배열
    var answer = [0, 0];
    
    // sizes 배열의 크기
    const n = sizes.length;
    
    // sizes를 순회하며 w, h를 갱신할지 비교
    for (let i = 0; i < n; i++) {
        // 현재 명함과 정답 배열 정렬
        sizes[i].sort((a, b) => a - b);
        console.log(sizes[i])
        
        // answer의 w, h로 현재 명합을 담을 수 없는 경우
        // - answer의 w, h 중 더 긴 길이와 현재 명함의 w, h 중 더 긴 길이를 비교
        // - answer보다 현재 명함이 더 길면 갱신
        if (answer[1] < sizes[i][1]) {
            answer[1] = sizes[i][1];
        }
        if (answer[0] < sizes[i][0]) {
            answer[0] = sizes[i][0];
        }
    }
    
    return answer[0] * answer[1];
}