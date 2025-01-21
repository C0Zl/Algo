function solution(skill, skillTrees) {
    // 정답을 담을 변수
    let answer = 0;
    
    // 스킬 트리를 순회하면서 가능한지 여부 확인
    treeloop : for (let ti = 0; ti < skillTrees.length; ti++) {
        const skillTree = skillTrees[ti];
        // 스킬 배웠는지 여부를 저장할 배열
        let learned = new Array(skill.length).fill(false);
        
        // 각 스킬 순회
        for (let i = 0; i < skillTree.length; i++) {
            const currentSkill = skillTree[i];
            const skillIdx = skill.indexOf(currentSkill);
            // skill에 현재 skill을 포함하지 않고 있으면 pass
            if (skillIdx === -1) {
                continue;
            } else {
                for (let si = 0; si < skillIdx; si++) {
                    if (!learned[si]) continue treeloop;
                }
                learned[skillIdx] = true;
            }
        }
        answer++;
    }
    
    return answer;
}