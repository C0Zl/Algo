const fs = require("fs");
// TODO: 제출 시 경로 변환 필수 ("/dev/stdin")
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

// 풀이
function solution(input) {
  const [N, K] = input[0].split(" ").map((str) => Number(str));
  const visited = new Array(200000).fill(false);
  const queue = [{ current: N, time: 0 }];

  while (queue.length > 0) {
    const { current, time } = queue.shift();

    if (current === K) return time;
    else if (current < 0 || current > 200000) continue;

      if (!visited[current - 1]) {
      queue.push({ current: current - 1, time: time + 1 });
      visited[current - 1] = true;
    }
    if (!visited[current * 2]) {
      queue.push({ current: current * 2, time: time });
      visited[current * 2] = true;
    }
    if (!visited[current + 1]) {
      queue.push({ current: current + 1, time: time + 1 });
      visited[current + 1] = true;
    }
    
  }
}

console.log(solution(input));
