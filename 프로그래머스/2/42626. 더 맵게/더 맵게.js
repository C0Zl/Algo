class MinHeap {
    constructor() {
        this.heap = [];
    }

    // 최소 힙의 루트값 (가장 작은 값)을 반환
    peek() {
        return this.heap[0];
    }

    // 최소 힙에 값 삽입
    push(val) {
        this.heap.push(val);
        this._bubbleUp();
    }

    // 최소값 제거 및 반환
    pop() {
        if (this.heap.length === 1) return this.heap.pop();
        const min = this.heap[0];
        this.heap[0] = this.heap.pop();
        this._bubbleDown();
        return min;
    }

    // 힙 크기 반환
    size() {
        return this.heap.length;
    }

    // 내부 메서드: 삽입 후 힙 정렬 유지
    _bubbleUp() {
        let index = this.heap.length - 1;
        while (index > 0) {
            const parentIndex = Math.floor((index - 1) / 2);
            if (this.heap[index] >= this.heap[parentIndex]) break;
            [this.heap[index], this.heap[parentIndex]] = [this.heap[parentIndex], this.heap[index]];
            index = parentIndex;
        }
    }

    // 내부 메서드: 삭제 후 힙 정렬 유지
    _bubbleDown() {
        let index = 0;
        const length = this.heap.length;
        const element = this.heap[0];

        while (true) {
            let leftChildIndex = 2 * index + 1;
            let rightChildIndex = 2 * index + 2;
            let swapIndex = null;

            if (leftChildIndex < length && this.heap[leftChildIndex] < element) {
                swapIndex = leftChildIndex;
            }
            if (
                rightChildIndex < length &&
                this.heap[rightChildIndex] < (swapIndex === null ? element : this.heap[leftChildIndex])
            ) {
                swapIndex = rightChildIndex;
            }
            if (swapIndex === null) break;
            [this.heap[index], this.heap[swapIndex]] = [this.heap[swapIndex], this.heap[index]];
            index = swapIndex;
        }
    }
}

// 스코빌 문제 해결 함수
function solution(s, k) {
    const heap = new MinHeap();
    let mixCount = 0;

    // 배열을 최소 힙에 삽입
    s.forEach(num => heap.push(num));

    // 모든 음식의 스코빌 지수가 k 이상이 될 때까지 반복
    while (heap.size() > 1 && heap.peek() < k) {
        const first = heap.pop();
        const second = heap.pop();
        const newScoville = first + second * 2;
        heap.push(newScoville);
        mixCount++;
    }

    // 조건을 만족하지 못하면 -1 반환
    return heap.peek() >= k ? mixCount : -1;
}
