function solution(info, n, m) {
  const INF = 1e9;

  // dp[b] = "B 흔적이 b일 때 가능한 A 최소 흔적"
  // 중간 상태에서 b가 m 이상이 되면 이미 실패 상태라 굳이 보관할 필요 없음
  let dp = Array(m).fill(INF);
  dp[0] = 0;

  for (const [aTrace, bTrace] of info) {
    const next = Array(m).fill(INF);

    for (let b = 0; b < m; b++) {
      if (dp[b] === INF) continue;

      // 1) 이 물건을 A에게: B 흔적 유지, A 흔적 증가
      const aSum = dp[b] + aTrace;
      if (aSum < n) {              // A가 붙잡히지 않는 상태만 유지
        if (aSum < next[b]) next[b] = aSum;
      }

      // 2) 이 물건을 B에게: B 흔적 증가, A 유지
      const nb = b + bTrace;
      if (nb < m) {                // B가 붙잡히지 않는 상태만 유지
        if (dp[b] < next[nb]) next[nb] = dp[b];
      }
    }
    dp = next;
  }

  // 모든 물건을 배정한 뒤, B < m, A < n 중 A 최소
  let ans = Math.min(...dp);
  return ans === INF ? -1 : ans;
}
