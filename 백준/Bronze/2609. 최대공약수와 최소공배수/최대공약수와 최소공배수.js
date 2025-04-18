const [n, m] = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split(" ")
  .map(Number);
let i = n;
let j = m;

// 유클리드 호제법
while (i % j !== 0) {
  let rest = i % j;
  if (rest !== 0) {
    [i, j] = [j, rest];
  }
}

console.log(j + "\n" + (n * m) / j);
