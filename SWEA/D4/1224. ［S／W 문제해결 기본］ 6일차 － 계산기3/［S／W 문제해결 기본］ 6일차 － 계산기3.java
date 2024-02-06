import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) throws IOException {
		Map<Character, Integer> priority = new HashMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// +, *, ( 의 우선순위 부여
		priority.put('+', 1);
		priority.put('*', 2);
		priority.put('(', 0);

		// 테스트케이스만큼 반복
		for (int testCase = 1; testCase <= 10; testCase++) {
			int N = Integer.parseInt(br.readLine());
			
			// 연산자를 넣을 stack, 피연산자를 넣을 num을 Stack 변수로 선언
			Stack<Character> stack = new Stack<>();
			Stack<Integer> num = new Stack<>();

			// 한줄씩 읽어들이기 위한 expr 변수 선언 및 초기화
			String expr = br.readLine();
			
			// 후위 표기법 문자열을 담을 fixExpr 선언 및 초기화
			String fixExpr = "";
			
			// 각 문자를 받기 위한 char형 변수 c를 미리 선언
			char c;
			
			// 초기 표현식의 길이만큼 반복
			for (int i = 0; i < N; i++) {
				// 문자를 입력 받고, 각 경우에 따라 처리
				c = expr.charAt(i);
				
				if(c == '(') {
					stack.push(c);
				} else if (c == ')') {
					while(stack.peek() != '(') {
						fixExpr += stack.pop();
					}
					stack.pop();
				} else if ('0' <= c && c <= '9') {
					fixExpr += c;
				} else {
					// +, -, *, /의 경우 
					if (stack.isEmpty()) {
						stack.push(c);
					} else {
						// 우선순위가 낮은 연산자가 마지막에 남을 때까지 pop
						while (!stack.isEmpty() && priority.get(c) <= priority.get(stack.peek())) {
							fixExpr += stack.pop();
						}
						stack.push(c);
					}
				}
			}
			
			// 후위 표기법 연산
			for (int i = 0; i < fixExpr.length(); i++) {
				c = fixExpr.charAt(i);
				
				if('0' <= c && c <= '9') {
					num.push(c - 48);
				} else {
					// +, *의 경우 
					if (c == '*') {
						num.push(num.pop() * num.pop());
					} else {
						num.push(num.pop() + num.pop());
					}
				}
			}
			sb.append("#").append(testCase).append(" ").append(num.pop()).append("\n");
		}
		System.out.println(sb);
	}
}
