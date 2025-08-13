function solution(genres, plays) {
    let answer = [];
    
    const n = genres.length;
    const map = new Map();
    
    for (let i = 0; i < n; i++) {
        const genreCnt = map.get(genres[i]);
        
        map.set(genres[i], genreCnt ? genreCnt + plays[i] : plays[i]);
    }

    let genreArr = [...map].sort((a, b) => b[1] - a[1]);
    
    // 장르, index, play를 담은 객체 배열 생성
    let totalInfo = genres.map((g,i)=>({
        genre : g,
        index : i,
        playCnt : plays[i]
    }));
    
    // totalInfo 정렬
    totalInfo.sort((a, b) => {
        // 재생 횟수가 같은 경우
        if (a.playCnt === b.playCnt) {
            return a.index - b.index;
        }
        
        return b.playCnt - a.playCnt;
    })
    
    for (let genre of genreArr) {
        let cnt = 0;
        totalInfo.forEach((info) => {
            if (info.genre == genre[0] && cnt < 2) {
                cnt++;
                answer.push(info.index);
            }
        })
    }
    
    return answer;
}
