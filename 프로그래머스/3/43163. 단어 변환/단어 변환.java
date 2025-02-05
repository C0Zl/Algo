import java.util.*;

class Solution {
    class Node {
        String word;
        int count;
        
        Node (String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        queue.offer(new Node(begin, 0));
        
        // 큐가 비어있지 않으면 반복
        while(!queue.isEmpty()) {
            Node curWord = queue.poll();
            
            // 현재 단어와 target이 같아지면 바로 count return하기
            if (curWord.word.equals(target)) return curWord.count;
            
            // 바꿀 수 있는 단어 찾기
            wordLoop : for(int s = 0; s < words.length; s++) {
                // 방문한 적 있는 단어는 건너뛰기
                if (visited[s]) continue;
                
                String word = words[s];
                
                // 다른 문자수 세기
                int diffCnt = 0;
                for(int c = 0; c < word.length(); c++) {
                    if (curWord.word.charAt(c) != word.charAt(c)) {
                        diffCnt++;
                        
                        // 다른 문자 개수가 1개보다 많으면 다음 단어로 건너뛰기
                        if(diffCnt > 1) continue wordLoop;
                     }
                }
                
                // 다른 문자 개수가 1개면 queue에 추가하고 방문 배열에 넣기
                queue.offer(new Node(word, curWord.count + 1));
                visited[s] = true;
            }
        }
        
        return 0;
    }
}