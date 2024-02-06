import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Character, Integer> priority = new HashMap<>();
		
		priority.put('+', 1);
		priority.put('-', 1);
		priority.put('*', 2);
		priority.put('/', 2);
		priority.put('(', 0);
		
		String expression = br.readLine();
		
		// 중위표기식 -> 후위 표기식
		String postfix = "";
		Stack<Character> op = new Stack<>();
		
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			
			if(c == '(') {
				op.push(c);
			} else if (c == ')') {
				while(op.peek() != '(') {
					postfix += op.pop();
				}
				op.pop();
			} else if ('A' <= c && c <= 'Z') {
				postfix += c;
			} else {
				// +, -, *, /의 경우 
				if (op.isEmpty()) {
					op.push(c);
				} else {
					// 우선순위가 낮은 연산자가 마지막에 남을 때까지 pop
					while (!op.isEmpty() && priority.get(c) <= priority.get(op.peek())) {
						postfix += op.pop();
					}
					op.push(c);
				}
			}
		}
		while(!op.isEmpty()) {
			postfix += op.pop();
		}
		System.out.println(postfix);
	}
}
