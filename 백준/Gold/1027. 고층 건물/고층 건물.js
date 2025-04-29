const filepath = process.platform === "linux" ? 0 : "test.txt";
const input = require("fs")
  .readFileSync(filepath, "utf8")
  .toString()
  .trim()
  .split(/\r?\n/);

const N = Number(input[0]);
const building = input[1].split(" ").map(Number);

// 정답을 담을 변수
let maxBuildingCount = 0;

// 기준이 될 빌딩 순회
for (let i = 0; i < N; i++) {
  // 현재 빌딩에서 보이는 빌딩 개수
  let buildingCount = 0;

  // 현재 기준 빌딩보다 왼쪽 빌딩 확인
  checkBuilding: for (let l = 0; l < i; l++) {
    // 기준 빌딩과 보이는지 확인할 빌딩 사이에 다른 고층 빌딩이 있는 지 확인
    for (let j = l + 1; j < i; j++) {
      if (
        building[j] >=
        building[l] + ((building[i] - building[l]) * (j - l)) / (i - l)
      )
        continue checkBuilding;
    }
    // console.log("l : ", l);

    // 고층 빌딩이 없으면 count
    buildingCount++;
  }

  // 현재 기준 빌딩보다 오른쪽 빌딩 확인
  checkBuilding: for (let r = i + 1; r < N; r++) {
    // 기준 빌딩과 보이는지 확인할 빌딩 사이에 다른 고층 빌딩이 있는 지 확인
    for (let j = i + 1; j < r; j++) {
      if (
        building[j] >=
        building[i] + ((building[r] - building[i]) * (j - i)) / (r - i)
      )
        continue checkBuilding;
    }
    // console.log("r : ", r);

    // 고층 빌딩이 없으면 count
    buildingCount++;
  }

  // console.log("i : ", i, "count : ", buildingCount);
  maxBuildingCount = Math.max(buildingCount, maxBuildingCount);
}

console.log(maxBuildingCount);
