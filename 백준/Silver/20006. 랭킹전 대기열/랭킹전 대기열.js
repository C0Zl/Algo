const input = require("fs")
  .readFileSync(0, "utf8")
  .trim()
  .toString()
  .split("\n");

const [playerCount, maxCount] = input[0].split(" ").map(Number);
const system = [];

// 플레이어 수만큼 정보 순회
for (let i = 1; i <= playerCount; i++) {
  const [levelStr, nickname] = input[i].split(" ");
  const level = +levelStr;

  let entered = false;

  // 들어갈 방이 있는지 검증
  for (let room of system) {
    // 1) 들어갈 수 있는 경우
    if (room.players.length < maxCount && Math.abs(room.level - level) <= 10) {
      room.players.push({
        nickname,
        level,
      });
      entered = true;
      break;
    }
  }

  // 2) 못 들어간 경우
  if (!entered)
    system.push({
      level: level,
      players: [{ nickname, level }],
    });
}

for (let room of system) {
  const status = room.players.length === maxCount ? "Started!" : "Waiting!";
  console.log(status);

  room.players
    .sort((a, b) => a.nickname.localeCompare(b.nickname))
    .forEach((p) => console.log(`${p.level} ${p.nickname}`));
}
