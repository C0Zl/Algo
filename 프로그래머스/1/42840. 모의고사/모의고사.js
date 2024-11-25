function solution(answers) {
    // 정답 배열
    var answer = [];
    
    // 각 수포자들이 찍는 방식
    const p1 = [1, 2, 3, 4, 5];
    const p2 = [2, 1, 2, 3, 2, 4, 2, 5];
    const p3 = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5];
    
    // 수포자들이 맞은 문제 개수 배열 count
    var count = [0, 0, 0];
    
    // 가장 높은 점수 highest
    var highest = 0;
    
    // answers를 돌며, 각각 맞은 갯수 count
    for (let i = 0; i < answers.length; i++) {
        // 정답이면, count++
        if (p1[i % p1.length] === answers[i]) {
            count[0]++;
        }
        if (p2[i % p2.length] === answers[i]) {
            count[1]++;
        }
        if (p3[i % p3.length] === answers[i]) {
            count[2]++;
        }
    }
    
    // 가장 많이 맞힌 점수로 highest 갱신
    count.forEach((value) => {highest = highest < value ? value : highest});
    
    // 가장 높은 점수와 현재 수포자의 점수가 같으면 answer에 추가
    for (let j = 0; j < 3; j++) {
        if (highest === count[j]) {
            answer.push(j + 1);
        }
    }

    return answer;
}