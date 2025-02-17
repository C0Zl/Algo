const fs = require("fs");
const [[n], ...meeting] = fs
  .readFileSync(0, "utf-8")
  .trim()
  .split("\n")
  .map((line) => line.split(" ").map(Number));

function solution() {
  meeting.sort((a, b) => a[1] - b[1] || a[0] - b[0]);

  let maxMeetingCount = 0;
  let currentTime = 0;

  for ([startTime, endTime] of meeting) {
    if (startTime >= currentTime) {
      currentTime = endTime;
      maxMeetingCount++;
    }
  }

  return maxMeetingCount;
}

console.log(solution());
