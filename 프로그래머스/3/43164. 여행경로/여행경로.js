function solution(tickets) {
    // 결과를 담을 배열
    const answer = [];
    
    // 티켓을 출발지 기준, 그리고 동일 출발지 내에서 도착지 기준으로 정렬
    tickets.sort(([a1, b1], [a2, b2]) => {
        if (a1 === a2) return b1.localeCompare(b2);
        return a1.localeCompare(a2);
    });
    
    // DFS 탐색 함수
    const dfs = (current, path, used) => {
        // 모든 티켓을 사용한 경우
        if (path.length === tickets.length + 1) {
            answer.push([...path]);
            return true; // 정답을 찾으면 탐색 중단
        }
        
        for (let i = 0; i < tickets.length; i++) {
            const [start, end] = tickets[i];
            
            // 현재 위치가 출발지와 같고, 티켓이 사용되지 않은 경우
            if (start === current && !used[i]) {
                used[i] = true; // 티켓 사용 처리
                path.push(end); // 경로에 도착지 추가
                
                // 재귀 호출
                if (dfs(end, path, used)) return true;
                
                // 백트래킹
                used[i] = false; 
                path.pop();
            }
        }
        return false;
    };
    
    // 시작점 "ICN"에서 탐색 시작
    dfs("ICN", ["ICN"], new Array(tickets.length).fill(false));
    
    return answer[0]; // 사전순 경로 중 첫 번째 경로 반환
}
