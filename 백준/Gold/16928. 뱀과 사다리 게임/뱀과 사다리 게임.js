const fs = require("fs");
// TODO: 제출 시 경로 변환 필수 ("/dev/stdin")
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

function solution(input) {
  const [N, M] = input[0].split(" ").map(Number);
  const ladderSnake = new Array(101).fill(0);

  // 사다리와 뱀 정보 입력
  for (let i = 1; i <= N; i++) {
    const [x, y] = input[i].split(" ").map(Number);
    ladderSnake[x] = y;
  }
  for (let i = N + 1; i < input.length; i++) {
    const [u, v] = input[i].split(" ").map(Number);
    ladderSnake[u] = v;
  }

  const visited = new Array(101).fill(false);
  const queue = [{ pos: 1, cnt: 0 }];
  visited[1] = true;

  while (queue.length > 0) {
    const { pos, cnt } = queue.shift();

    if (pos === 100) return cnt;

    for (let dice = 1; dice <= 6; dice++) {
      let next = pos + dice;

      if (next > 100) continue;
      if (visited[next]) continue;

      visited[next] = true;

      if (ladderSnake[next] !== 0) {
        next = ladderSnake[next];
      }

      queue.push({ pos: next, cnt: cnt + 1 });
    }
  }

  return -1;
}

console.log(solution(input));
