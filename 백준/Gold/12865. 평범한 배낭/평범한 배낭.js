const fs = require("fs");
const input = fs
  .readFileSync(0, "utf-8")
  .split("\n")
  .map((line) => line.trim().split(" "));

function solution(input) {
  // N, M 입력
  const [itemCount, maxWeight] = input[0].map(Number);

  // 무게, 가치 배열
  const weight = input.slice(1).map((item) => Number(item[0]));
  const value = input.slice(1).map((item) => Number(item[1]));

  // dp 배열 생성
  const dp = Array.from({ length: itemCount }).map(() =>
    Array.from({ length: maxWeight + 1 }, () => 0)
  );

  for (let j = weight[0]; j < dp[0].length; j++) {
    dp[0][j] = value[0];
  }

  for (let i = 1; i < dp.length; i++) {
    for (let j = 1; j < dp[0].length; j++) {
      // 현재 물건의 무게, 가치
      const currentWeight = weight[i];
      const currentValue = value[i];

      // 현재 물건의 가치보다 작은 경우 넘어가기
      if (j < currentWeight) {
        dp[i][j] = dp[i - 1][j];
        continue;
      }

      // 더 큰 값으로 채우기
      dp[i][j] = Math.max(
        dp[i - 1][j - currentWeight] + currentValue,
        dp[i - 1][j]
      );
    }
  }

  return dp[itemCount - 1][maxWeight];
}

console.log(solution(input));
