class MinHeap {
  constructor() {
    this.heap = [];
  }

  insert(edge, cost) {
    const node = { edge, cost };
    this.heap.push(node);
    this.upHeap();
  }

  upHeap() {
    const heap = this.heap;
    let idx = heap.length - 1;
    const node = heap[idx];

    while (idx > 0) {
      let parentIdx = Math.floor((idx - 1) / 2);

      if (heap[parentIdx].cost <= heap[idx].cost) break;

      heap[idx] = heap[parentIdx];
      idx = parentIdx;
    }

    heap[idx] = node;
  }

  remove() {
    const heap = this.heap;

    if (heap.length === 0) return undefined;
    if (heap.length === 1) return heap.pop();

    const min = heap[0];
    heap[0] = heap.pop();
    this.downHeap();
    return min;
  }

  downHeap() {
    const heap = this.heap;
    const length = heap.length;
    let idx = 0;
    const node = heap[idx];

    while (idx * 2 + 1 < length) {
      let leftIdx = idx * 2 + 1;
      let rightIdx = leftIdx + 1;
      let smallerChildIdx = leftIdx;

      if (rightIdx < length && heap[rightIdx].cost < heap[leftIdx].cost)
        smallerChildIdx = rightIdx;

      heap[idx] = heap[smallerChildIdx];
      idx = smallerChildIdx;
    }

    heap[idx] = node;
  }

  isEmpty() {
    return this.heap.length === 0;
  }
}

// 입력 변수 처리
const fs = require("fs");
const [[n], [m], ...rest] = fs
  .readFileSync(0, "utf-8")
  .trim()
  .split("\n")
  .map((line) => line.split(" ").map(Number));

const edgeInfo = rest.slice(0, m);
const [[startIdx, endIdx]] = rest.slice(m);
const adjList = Array.from({ length: n }, () => []);
const minCost = Array.from({ length: n }, () => Infinity);

for ([start, end, cost] of edgeInfo) {
  adjList[start - 1].push({ edge: end - 1, cost });
}

console.log(dijkstra(startIdx - 1, endIdx - 1));

function dijkstra(start, end) {
  const minHeap = new MinHeap();
  minHeap.insert(start, 0);
  minCost[start] = 0;

  while (!minHeap.isEmpty()) {
    const node = minHeap.remove();

    // 이미 더 작은 값이 저장되어 있으면 넘어가기
    if (node.cost > minCost[node.edge]) continue;

    for (let { edge, cost } of adjList[node.edge]) {
      let newCost = cost + node.cost;

      if (newCost < minCost[edge]) {
        minCost[edge] = newCost;
        minHeap.insert(edge, newCost);
      }
    }
  }

  return minCost[end];
}
