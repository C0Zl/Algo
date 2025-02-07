function countSubarraySums(target, A, B) {
    // 모든 부 배열의 합을 구하는 함수
    function getSubarraySums(arr) {
        let subSums = [];
        let n = arr.length;
        
        for (let i = 0; i < n; i++) {
            let sum = 0;
            for (let j = i; j < n; j++) {
                sum += arr[j];
                subSums.push(sum);
            }
        }
        return subSums;
    }

    // Step 1: A와 B의 모든 부 배열 합 구하기
    let A_sub_sums = getSubarraySums(A);
    let B_sub_sums = getSubarraySums(B);

    // Step 2: B의 부 배열 합을 해시맵에 저장
    let B_sum_count = new Map();
    
    for (let bSum of B_sub_sums) {
        B_sum_count.set(bSum, (B_sum_count.get(bSum) || 0) + 1);
    }

    // Step 3: A의 부 배열 합을 순회하며 (T - A_sum)이 해시맵에 있는지 확인
    let count = 0;
    
    for (let aSum of A_sub_sums) {
        let required = target - aSum;
        if (B_sum_count.has(required)) {
            count += B_sum_count.get(required); // 해당 값을 만들 수 있는 경우의 수 추가
        }
    }

    return count;
}

// 입력 받기
const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const T = parseInt(input[0]);
const n = parseInt(input[1]);
const A = input[2].split(" ").map(Number);
const m = parseInt(input[3]);
const B = input[4].split(" ").map(Number);

console.log(countSubarraySums(T, A, B));
