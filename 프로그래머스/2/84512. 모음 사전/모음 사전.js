function solution(word) {
    const alphabet = ["A", "E", "I", "O", "U"];
    let cnt = 0; // 단어 생성 순서 카운트
    let found = false; // 찾았는지 여부 확인
    
    // 단어를 만드는 함수
    function makeWord(current) {
        // 기저 조건: 찾고자 하는 단어를 생성했을 때
        if (current === word) {
            found = true;
            return;
        }

        // 문자열 길이가 5 이상이면 중단
        if (current.length >= 5) {
            return;
        }

        // 가능한 모든 경우 탐색
        for (let i = 0; i < alphabet.length; i++) {
            if (found) return; // 단어를 찾은 경우 즉시 중단
            cnt++; // 단어 생성 순서 증가
            makeWord(current + alphabet[i]); // 다음 문자 추가
        }
    }

    makeWord(""); // 빈 문자열부터 시작
    return cnt; // 찾은 단어의 생성 순서 반환
}
