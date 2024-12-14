function solution(brown, yellow) {
    // x * y로 yellow가 나오는 배열 찾기
    for (let i = 1; i <= yellow; i++) {
        // yellow가 i의 배수인지 판단
        if (yellow % i === 0) {
            // 2 * (x + y) + 4가 brown과 같은지 판단
            const j = yellow / i;
            if ((2 * (i + j) + 4) === brown) {
                return i > j ? [i+2, j+2] : [j+2, i+2];
            }
        }
    }
}