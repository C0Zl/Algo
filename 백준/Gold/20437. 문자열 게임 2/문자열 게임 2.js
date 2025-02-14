const fs = require("fs");
const input = fs.readFileSync(0, "utf-8").trim().split("\n");

function solution() {
  const t = Number(input[0]);
  const rest = input.slice(1);
  const strArr = rest.filter((_, idx) => idx % 2 === 0).map((v) => v.trim());
  const k = rest.filter((_, idx) => idx % 2 === 1).map(Number);

  for (let i = 0; i < strArr.length; i++) {
    let strObj = {};
    for (let j = 0; j < strArr[i].length; j++) {
      let char = strArr[i][j];
      if (!strObj[char]) {
        strObj[char] = [];
      }
      strObj[char].push(j);
    }

    let minLen = Number.MAX_SAFE_INTEGER;
    let maxLen = 0;

    for (let char in strObj) {
      if (strObj[char].length < k[i]) continue;
      for (let d = 0; d < strObj[char].length - k[i] + 1; d++) {
        let diff = strObj[char][d + k[i] - 1] - strObj[char][d] + 1;

        if (minLen > diff) minLen = diff;
        if (maxLen < diff) maxLen = diff;
      }
    }

    console.log(maxLen === 0 ? "-1" : minLen + " " + maxLen + "");
  }
}

solution();
