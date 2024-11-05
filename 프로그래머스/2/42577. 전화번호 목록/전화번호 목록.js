function solution(phone_book) {
    // 전화 번호 리스트 정렬
    phone_book.sort();
    
    for(let i = 0; i < phone_book.length - 1; i++) {
        // 현재 전화번호와 뒤 전화 번호의 시작이 같으면 접두어가 같은 경우
        if (phone_book[i] === phone_book[i+1].slice(0, phone_book[i].length)) {
            return false;
        }
    }
    return true;
}