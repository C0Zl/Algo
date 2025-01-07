function solution(begin, target, words) {
    // bfs로 target과 같은지 확인
    function bfs(word) {
        // queue
        let queue = [{currentWord : word, cnt : 0}];
        
        // 방문 기록을 위한 배열 visited
        const visited = Array(words.length).fill(false);
        
        while (queue.length > 0) {
            let {currentWord, cnt} = queue.shift();
            console.log(currentWord, cnt);
            
            if (currentWord === target) return cnt;
            
            for(let i = 0; i < words.length; i++) {
                if (!visited[i] && verifyChange(currentWord, words[i])) {
                    visited[i] = true;
                    queue.push({ currentWord: words[i], cnt : cnt + 1});
                }
            }
        }
        
        // 실패 시 0 return
        return 0;
    }
    
    // bfs 실행
    return bfs(begin);    
    
    // 변환 가능 여부 판단 함수
    function verifyChange(current, goal) {
        let difNum = 0;
        
        for (let j = 0; j < current.length; j++) {
            if (current[j] !== goal[j]) {
                difNum++;
                if (difNum > 1) return false;
            }
        }
        
        // 다른 문자가 1개 이하면 변환 가능
        return true;
    }
}