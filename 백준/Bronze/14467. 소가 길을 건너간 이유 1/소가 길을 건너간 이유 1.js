const input = require("fs")
  .readFileSync(0, "utf8")
  .trim()
  .toString()
  .split("\n");

const N = Number(input[0]);
const map = new Map();
let answer = 0;

// N개의 위치 정보 순회
for (i = 1; i <= N; i++) {
  const [cow, position] = input[i].split(" ").map(Number);

  if (!map.has(cow)) {
    map.set(cow, position);
  } else if (map.get(cow) !== position) {
    answer++;
    map.set(cow, position);
  }
}

console.log(answer);
