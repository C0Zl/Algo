function solution(n, wires) {
    // 정답 변수 초기화
    let answer = Number.MAX_SAFE_INTEGER;
    
    // tree 초기화
    let tree = new Array(n+1).fill().map(() => [])
    
    // 인접리스트를 사용해 트리 정보 저장
    wires.map((w) => {
        let [w1, w2] = w;
        
        tree[w1].push(w2);
        tree[w2].push(w1);
    })
    // console.log('tree : ', tree)
    
    wires.forEach((w, idx) => {
        let c1 = countWire(tree, w[0], w[1]);
        let c2 = countWire(tree, w[1], w[0]);
        
        // console.log('c1 : ', c1, 'c2 : ', c2);
        
        if (Math.abs(c1 - c2) < answer) answer = Math.abs(c1 - c2);
    })
    
    return answer;
}

function countWire(tree, root, except) {
    let cnt = 1;
    let visit = [except, root];
    let queue = [root];
    
    while (queue.length !== 0) {
        const currentNode = queue.pop();
        // console.log('current', currentNode)
        
        tree[currentNode].forEach((v) => {
            // 아직 방문한 적 없는 노드인 경우
            if (!visit.includes(v)) {
                queue.push(v);
                visit.push(v);
                cnt++;
                // console.log('cnt:', cnt)
            }
        })
    }
    return cnt;
    
}