const filepath = process.platform === "linux" ? 0 : "test.txt";
const input = require("fs")
  .readFileSync(filepath, "utf8")
  .toString()
  .trim()
  .split(/\r?\n/);

const N = Number(input[0]);
const arr = input.slice(1).map((line) => line.split("").map(Number));

let answer = quadTree(0, 0, N);

function quadTree(sr, sc, n) {
  let mixed = false;
  let current = "";

  let initial = arr[sr][sc];

  quad: for (let r = sr; r < sr + n; r++) {
    for (let c = sc; c < sc + n; c++) {
      if (arr[r][c] !== initial) {
        mixed = true;
        current += "(" + quadTree(sr, sc, n / 2);
        current += quadTree(sr, sc + n / 2, n / 2);
        current += quadTree(sr + n / 2, sc, n / 2);
        current += quadTree(sr + n / 2, sc + n / 2, n / 2) + ")";
        break quad;
      }
    }
  }
  if (!mixed) current += initial;

  return current;
}

console.log(answer);
