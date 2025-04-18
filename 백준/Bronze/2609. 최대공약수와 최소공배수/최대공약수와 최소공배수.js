const fs = require("fs");
const input = fs.readFileSync(0, "utf-8").split("\n");

function solution(input) {
  // n, m 입력
  const [n, m] = input[0].split(" ").map(Number);

  // 공약수를 담을 배열
  let commonDivisor = [];
  // 정답 배열
  let answer = [1, 1];

  // 공약수 구하기
  let tempN = n;
  let tempM = m;
  for (let i = 2; i <= n; i++) {
    // 공약수인 경우
    while (tempN % i === 0 && tempM % i === 0) {
      commonDivisor.push(i);
      tempN /= i;
      tempM /= i;
    }
  }

  // 최대 공약수
  for (divisor of commonDivisor) {
    answer[0] *= divisor;
  }
  // 최소 공배수
  answer[1] = (n * m) / answer[0];

  return answer[0] + "\n" + answer[1];
}

console.log(solution(input));
