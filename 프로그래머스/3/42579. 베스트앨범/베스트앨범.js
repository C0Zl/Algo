function solution(genres, plays) {
    const genrePlayCount = new Map(); // 장르별 총 재생 수
    const genreSongs = new Map();    // 장르별 노래 리스트

    // 1. 데이터를 Map에 정리
    for (let i = 0; i < genres.length; i++) {
        const genre = genres[i];
        const play = plays[i];

        // 장르별 총 재생 수 저장
        genrePlayCount.set(genre, (genrePlayCount.get(genre) || 0) + play);

        // 장르별 노래 리스트 저장
        if (!genreSongs.has(genre)) {
            genreSongs.set(genre, []);
        }
        genreSongs.get(genre).push({ index: i, play });
    }

    // 2. 장르를 총 재생 수 기준으로 내림차순 정렬
    const sortedGenres = [...genrePlayCount.entries()]
        .sort((a, b) => b[1] - a[1]) // 재생 수 기준 내림차순
        .map(([genre]) => genre);    // 장르 이름만 추출

    const answer = [];

    // 3. 각 장르에서 최대 2곡 선택
    for (const genre of sortedGenres) {
        // 장르 내 노래를 재생 수 기준 내림차순, 고유 번호 오름차순 정렬
        const songs = genreSongs.get(genre)
            .sort((a, b) => b.play - a.play || a.index - b.index);

        // 최대 2곡 추가
        for (let i = 0; i < Math.min(2, songs.length); i++) {
            answer.push(songs[i].index);
        }
    }

    return answer;
}
