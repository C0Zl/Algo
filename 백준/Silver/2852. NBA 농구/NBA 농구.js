const input = require("fs")
  .readFileSync(0, "utf8")
  .trim()
  .toString()
  .split("\n");

// 현재 점수
const currentScore = [0, 0];
// 현재 이기고 있는 팀
let currentTeam = 0;
// 각 팀이 이기고 있던 시간 (초 기준)
const winTime = [0, 0];
// 기준 시간
let standardTime = 0;

// 득점 정보 순회
for (let i = 1; i < input.length; i++) {
  let [team, time] = input[i].split(" ");

  // 이긴 팀에 득점 정보 기록
  currentScore[team - 1]++;

  // 비기고 있지 않았던 경우
  if (currentTeam !== 0) {
    winTime[currentTeam - 1] += conversion(time) - standardTime;
  }

  standardTime = conversion(time);
  currentTeam = winTeam();

  // 현재가 마지막 득점이고 승팀이 있는 경우
  if (i === input.length - 1 && currentTeam !== 0) {
    winTime[currentTeam - 1] += 48 * 60 - standardTime;
  }
}

let answer =
  String(parseInt(winTime[0] / 60)).padStart(2, "0") +
  ":" +
  String(parseInt(winTime[0] % 60)).padStart(2, "0") +
  "\n" +
  String(parseInt(winTime[1] / 60)).padStart(2, "0") +
  ":" +
  String(parseInt(winTime[1] % 60)).padStart(2, "0");
console.log(answer);

// 현재 이기고 있는 팀 조사
function winTeam() {
  if (currentScore[0] > currentScore[1]) {
    return 1;
  } else if (currentScore[0] < currentScore[1]) {
    return 2;
  } else {
    return 0;
  }
}

// 분 -> 초 변환
function conversion(time) {
  const [min, sec] = time.split(":");
  return +min * 60 + +sec;
}
