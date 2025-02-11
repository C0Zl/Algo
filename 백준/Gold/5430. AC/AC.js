let fs = require("fs");
let input = fs.readFileSync(0, "utf8").trim().split("\n");

function solution(input) {
  const t = Number(input[0]);
  const rest = input.slice(1);
  const answer = [];

  tcLoop: for (let testcase = 0; testcase < t; testcase++) {
    let forward = true; // 정방향 여부
    let func = rest[testcase * 3].trim();
    let n = Number(rest[testcase * 3 + 1]);
    let arr = JSON.parse(rest[testcase * 3 + 2].trim());

    let start = 0, end = arr.length - 1;
    let isError = false;

    for (let char of func) {
      if (char === "R") {
        forward = !forward;
      } else {
        if (start > end) {
          answer.push("error");
          continue tcLoop;
        }
        if (forward) start++; // 앞에서 제거
        else end--; // 뒤에서 제거
      }
    }

    let resultArr = arr.slice(start, end + 1);
    if (!forward) resultArr.reverse(); // 뒤집어야 하면 뒤집기

    answer.push(JSON.stringify(resultArr));
  }

  return answer.join("\n");
}

console.log(solution(input));
