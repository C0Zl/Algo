const fs = require("fs");
// TODO: 제출 시 경로 변환 필수 ("/dev/stdin")
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

// 풀이
function solution(input) {
  const cleanedInput = input.map((line) => line.replace(/\r/g, ""));
  const [S, T] = [cleanedInput[0], cleanedInput[1]];

  const queue = [T];

  while (queue.length > 0) {
    const word = queue.shift();
    if (word === S) return 1;
    else if (word.length === 0) continue;

    // 마지막 문자가 A인 경우
    if (word[word.length - 1] === "A") {
      queue.push(word.slice(0, -1));
    }

    // 첫문자가 B인 경우
    if (word[0] === "B") {
      let reverse = "";
      for (let i = word.length - 1; i >= 0; i--) {
        reverse += word[i];
      }
      queue.push(reverse.slice(0, -1));
    }
  }

  return 0;
}

console.log(solution(input));
