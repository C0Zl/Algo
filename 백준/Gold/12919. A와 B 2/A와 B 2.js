const filepath = process.platform === "linux" ? 0 : "test.txt";
const [start, target] = require("fs")
  .readFileSync(filepath, "utf8")
  .toString()
  .trim()
  .split(/\r?\n/);

function check(start, target) {
  // base case
  if (start.length === target.length) return start === target ? 1 : 0;

  let result = 0;

  // 1) 뒤가 A인 경우: A를 제거하고 다시 시도
  if (target[target.length - 1] === "A") {
    result = result || check(start, target.slice(0, -1));
  }

  // 2) 앞이 B인 경우: B 제거 후 뒤집기
  if (target[0] === "B") {
    const reversed = target.slice(1).split("").reverse().join("");
    result = result || check(start, reversed);
  }

  return result;
}

console.log(check(start, target));
