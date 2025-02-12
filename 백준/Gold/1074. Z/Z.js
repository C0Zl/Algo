let fs = require("fs");
let [n, r, c] = fs
  .readFileSync(0, "utf8")
  .trim()
  .split(" ")
  .map(Number);

function solution(n, r, c) {
  let visitedCount = 0;

  while (n > 1) {
    // 현재 n을 기준으로 4사분면을 그려 큰 경우
    if (r >= 2 ** (n - 1)) {
      visitedCount += 2 ** (2 * n - 1);
      r %= 2 ** (n - 1);
    }
    if (c >= 2 ** (n - 1)) {
      visitedCount += 2 ** (2 * n - 2);
      c %= 2 ** (n - 1);
    }

    n--;
  }

  visitedCount += 2 * r;
  visitedCount += c;

  return visitedCount;
}

console.log(solution(n, r, c));
