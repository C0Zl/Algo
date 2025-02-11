const fs = require("fs");
// TODO: 제출 시 경로 변환 필수 ("/dev/stdin")
const input = fs.readFileSync("/dev/stdin", "utf8").toString().trim().split("\n");

function solution(input) {
  const [worldHeight, worldWidth] = input[0].split(" ").map(Number);
  const blockHeight = input[1].split(" ").map(Number);
  let areaSum = 0;
  let [left, right] = [0, worldWidth - 1];
  let [maxLeftHeight, maxRightHeight] = [0, 0];

  // 왼쪽 인덱스보다 오른쪽 인덱스가 더 클 때 반복
  while (left < right) {
    // 왼쪽 포인터의 높이보다 오른쪽 포인터 높이가 더 큰 경우
    if (blockHeight[left] < blockHeight[right]) {
      if (maxLeftHeight < blockHeight[left]) {
        maxLeftHeight = blockHeight[left];
      } else {
        areaSum += maxLeftHeight - blockHeight[left];
      }
      left++;
    } else {
      if (maxRightHeight < blockHeight[right]) {
        maxRightHeight = blockHeight[right];
      } else {
        areaSum += maxRightHeight - blockHeight[right];
      }
      right--;
    }
  }
  return areaSum;
}

console.log(solution(input));
