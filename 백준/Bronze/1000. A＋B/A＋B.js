const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

// 풀이
function solution(input) {
  const [A, B] = input[0].split(" ").map((str) => Number(str));
  return A + B;
}

console.log(solution(input));
