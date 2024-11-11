function solution(progresses, speeds) {
    // idx : 배포 되어야 하는 기능의 인덱스
    let idx = 0;
    
    // n : 총 작업 개수
    const n = progresses.length;
    
    // answer : 정답 배열    
    var answer = [];
    
    // idx가 작업 배열의 길이와 같아질 때까지 반복
    while (idx !== n) {
        // idx번째 작업부터 마지막 작업까지 speed만큼 작업 진도 더해줌
        for (let i = idx; i < n; i++) {
            // 100보다 작은 경우에만
            if (progresses[i] < 100) {
                progresses[i] += speeds[i];    
            }
        }
        
        let works = 0;
        
        // 배포해야 할 작업의 진도가 100을 넘어선 경우
        while (idx < n && progresses[idx] >= 100) {
            works++;
            idx++;
        }

        if (works > 0) {
            answer.push(works);
        }
    }

    return answer;
}