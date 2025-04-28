const filepath = process.platform === "linux" ? 0 : "test.txt";
const input = require("fs")
  .readFileSync(filepath, "utf8")
  .toString()
  .trim()
  .split(/\r?\n/);

const N = Number(input[0]);
const arr = input[1].split(" ").map(Number);
const oper = input[2].split(" ").map(Number);

// 최댓값, 최소값을 담을 변수
let maxValue = Number.MIN_SAFE_INTEGER;
let minValue = Number.MAX_SAFE_INTEGER;

cal(1, arr[0], oper);

function cal(i, cur, oper) {
  if (i === N) {
    if (cur > maxValue) maxValue = cur;
    if (minValue > cur) minValue = cur;

    return;
  }

  // 덧셈
  if (oper[0] > 0) {
    oper[0]--;
    cal(i + 1, cur + arr[i], oper);
    oper[0]++;
  }

  // 뺄셈
  if (oper[1] > 0) {
    oper[1]--;
    cal(i + 1, cur - arr[i], oper);
    oper[1]++;
  }

  // 곱셈
  if (oper[2] > 0) {
    oper[2]--;
    cal(i + 1, cur * arr[i], oper);
    oper[2]++;
  }

  //나눗셈
  if (oper[3] > 0) {
    oper[3]--;
    cal(i + 1, parseInt(cur / arr[i]), oper);
    oper[3]++;
  }
}

console.log(maxValue + "\n" + minValue);
