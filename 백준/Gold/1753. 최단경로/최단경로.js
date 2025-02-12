const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

class PriorityQueue {
  constructor(comparator) {
    this.heap = [null]; // 1번 인덱스부터 사용
    this.size = 0;
    this.comparator = comparator;
  }

  push(item) {
    this.size++;
    this.heap[this.size] = item;
    this.percolateUp();
  }

  percolateUp() {
    let pos = this.size;
    const heap = this.heap;
    const item = heap[pos];
    const comparator = this.comparator;

    while (pos > 1) {
      const parentPos = Math.floor(pos / 2);
      if (comparator(item, heap[parentPos]) >= 0) break;
      heap[pos] = heap[parentPos]; // 부모를 아래로 이동
      pos = parentPos;
    }

    heap[pos] = item;
  }

  shift() {
    if (this.size === 0) return undefined; // 예외 처리

    const min = this.heap[1]; // 최소값 저장
    this.heap[1] = this.heap[this.size]; // 마지막 요소를 루트로 이동
    this.heap.pop(); // 마지막 요소 제거
    this.size--;

    this.percolateDown();
    return min;
  }

  percolateDown() {
    let pos = 1;
    const heap = this.heap;
    const item = heap[pos];
    const comparator = this.comparator;

    while (pos * 2 <= this.size) {
      let leftChild = pos * 2;
      let rightChild = pos * 2 + 1;
      let smallerChild = leftChild;

      // 오른쪽 자식이 있고, 왼쪽보다 작은 경우 오른쪽 선택
      if (rightChild <= this.size && comparator(heap[rightChild], heap[leftChild]) < 0) {
        smallerChild = rightChild;
      }

      if (comparator(item, heap[smallerChild]) <= 0) break; // 힙 조건 충족 시 종료
      heap[pos] = heap[smallerChild]; // 자식을 부모로 이동
      pos = smallerChild;
    }

    heap[pos] = item;
  }

  isEmpty() {
    return this.size === 0;
  }
}

function dijkstra(graph, start, V) {
  const INF = Infinity;
  const distances = new Array(V + 1).fill(INF); // 거리 배열 (무한대로 초기화)
  distances[start] = 0;

  const pq = new PriorityQueue((a, b) => a[0] - b[0]); // 최소 힙
  pq.push([0, start]); // [거리, 노드]

  while (!pq.isEmpty()) {
    const [currentDistance, node] = pq.shift(); // 가장 작은 거리 노드 꺼내기

    if (currentDistance > distances[node]) continue; // 최단 거리보다 크면 무시

    for (const [nextNode, weight] of graph[node]) {
      const newDistance = currentDistance + weight;
      if (newDistance < distances[nextNode]) {
        distances[nextNode] = newDistance;
        pq.push([newDistance, nextNode]); // 갱신된 거리 삽입
      }
    }
  }

  return distances;
}

// 입력 파싱
const [V, E] = input[0].split(" ").map(Number);
const start = Number(input[1]);

const graph = Array.from({ length: V + 1 }, () => []);

// 그래프 구성 (인접 리스트)
for (let i = 2; i < 2 + E; i++) {
  const [u, v, w] = input[i].split(" ").map(Number);
  graph[u].push([v, w]);
}

// 다익스트라 실행
const shortestPaths = dijkstra(graph, start, V);

// 결과 출력
for (let i = 1; i <= V; i++) {
  console.log(shortestPaths[i] === Infinity ? "INF" : shortestPaths[i]);
}
