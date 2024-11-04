function solution(arr) {
    // 인덱스 초기화
    let idx = 0;
    var stk = [];
    
    // 인덱스가 마지막 원소를 다 돌 때까지
    while(idx !== arr.length) {
        // stk가 비어있으면 현재 원소 추가 후 idx 값 1 더하기
        if(stk.length === 0 || stk[stk.length - 1] < arr[idx]) {
            stk[stk.length] = arr[idx++];
            continue;
        }
        
        stk.pop();
    }
    
    return stk;
}