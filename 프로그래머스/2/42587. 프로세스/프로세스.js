function solution(priorities, location) {
    let count = 1;
    let sorted = [...priorities].sort((a, b) => b - a);
    
    while (true) {
        if (sorted[0] === priorities[0]) {
            if (location === 0) return count;
            priorities.shift();
            sorted.shift();
            location--;
            count++;
        } else {
            // 맨 앞의 값을 맨 뒤로 넣는다
            priorities.push(priorities.shift());
            if (location === 0) location = priorities.length - 1;
            else location--;
        }
    }
}