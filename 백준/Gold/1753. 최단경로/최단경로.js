class MinHeap {
  constructor() {
    this.heap = [];
  }

  insert(edge, value) {
    const node = { edge, value };
    this.heap.push(node);
    this.upHeap();
  }

  upHeap() {
    let heap = this.heap;
    let idx = heap.length - 1;
    const node = heap[idx];

    while (idx > 0) {
      let parentIdx = Math.floor((idx - 1) / 2);

      if (heap[parentIdx].value <= node.value) break;

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
    this.downHeap(0);

    return min;
  }

  downHeap(idx) {
    let heap = this.heap;
    let length = heap.length;
    const node = heap[idx];

    while (idx * 2 + 1 < length) {
      let leftIdx = idx * 2 + 1;
      let rightIdx = leftIdx + 1;
      let smallerChildIdx = leftIdx;

      if (rightIdx < length && heap[rightIdx].value < heap[leftIdx].value)
        smallerChildIdx = rightIdx;

      if (heap[smallerChildIdx].value >= node.value) break;

      heap[idx] = heap[smallerChildIdx];
      idx = smallerChildIdx;
    }

    heap[idx] = node;
  }

  isEmpty() {
    return this.heap.length === 0;
  }
}

let fs = require("fs");
let [[V, E], [K], ...edges] = fs
  .readFileSync("/dev/stdin", "utf8")
  .trim()
  .split("\n")
  .map((line) => line.trim().split(" ").map(Number));

const INF = Infinity;
const adjList = Array.from({ length: V }, () => new Map());
const minValue = Array(V).fill(INF);

// **🚀 중복 간선 처리 (더 작은 가중치만 저장)**
for (let [u, v, w] of edges) {
  if (!adjList[u - 1].has(v - 1) || adjList[u - 1].get(v - 1) > w) {
    adjList[u - 1].set(v - 1, w);
  }
}

dijkstra(K - 1);

function dijkstra(start) {
  const minHeap = new MinHeap();
  minHeap.insert(start, 0);
  minValue[start] = 0;

  while (!minHeap.isEmpty()) {
    let { edge: node, value: curDistance } = minHeap.remove();

    // 🚀 **힙에서 꺼낸 거리가 현재 최소 거리보다 크면 무시 (중복 경로 방지)**
    if (curDistance > minValue[node]) continue;

    for (const [nextNode, weight] of adjList[node]) {
      let newDistance = curDistance + weight;

      if (newDistance < minValue[nextNode]) {
        minValue[nextNode] = newDistance;
        minHeap.insert(nextNode, newDistance);
      }
    }
  }
}

// 결과 출력
console.log(minValue.map((v) => (v === INF ? "INF" : v)).join("\n"));
