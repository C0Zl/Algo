const fs = require("fs");
// TODO: 제출 시 경로 변환 필수 ("/dev/stdin")
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

// 풀이
function solution(input) {
  // 최소 차이 변수
  let minDiff = Number.MAX_SAFE_INTEGER;

  // 각각의 문자열을 분리
  const [A, B] = input[0].split(" ");
  const [ALen, BLen] = [A.length, B.length];

  // 문자열 차이만큼 반복
  for (let i = 0; i <= BLen - ALen; i++) {
    let diff = 0;

    // 앞, 뒤에 붙일 문자열
    const front = B.slice(0, i);
    const back = B.slice(ALen + i);

    // 최종 문자열
    const result = front + A + back;

    for (let j = 0; j < result.length; j++) {
      if (B[j] !== result[j]) diff++;
    }

    // 차이 최솟값이 바뀌었으면 갱신
    if (minDiff > diff) minDiff = diff;
  }

  return minDiff;
}

console.log(solution(input));