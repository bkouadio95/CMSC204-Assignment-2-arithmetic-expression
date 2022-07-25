/**
 * 
 * @author Betty Kouadio
 * CMSC 204
 * Assignment#2
 *
 */
public class Notation {

	
	public static NotationStack<Character> stack = new NotationStack<Character>();
	public static NotationQueue<Character> queue = new NotationQueue<Character>();
	/**
	 * Converts an infix expression into a postfix expression
	 * @param e the infix expression in string format
	 * @return the postfix expression in string format
	 * @throws QueueOverflowException 
	 * @throws StackOverflowException
	 * @throws StackUnderflowException
	 * @throws QueueUnderflowException
	 * @throws InvalidNotationFormatException
	 */
	public static String convertInfixToPostfix(String e) throws QueueOverflowException, StackOverflowException, StackUnderflowException, QueueUnderflowException, InvalidNotationFormatException
	{
		String result = "";
		queue = new NotationQueue<Character>();
		for(int i=0; i<e.length(); i++)
		{
			char c = e.charAt(i);
			if(Character.isDigit(c))
			{
				queue.enqueue(c);
			}
			else if(c=='(')
			{
				stack.push(c);
			}
			else if(c=='+'||c=='-'||c=='/'||c=='*')
			{
				while (!stack.isEmpty()&&(precedenceCheck(c))<=precedenceCheck(stack.top()))
				{
					queue.enqueue(stack.pop());			        	
				}
				stack.push(c);
			}
			else if(c==')')
			{
				while (!stack.isEmpty()&&stack.top()!='('&&(stack.top()=='-'||stack.top()=='+'||stack.top()=='*'||stack.top()=='/'))
				{
					queue.enqueue(stack.pop());
				}
				if(!stack.isEmpty()&&stack.top()=='(')
				{
					stack.pop();
				}
				else 
					throw new InvalidNotationFormatException("The notation is invalid");
			}
		}
		while(!stack.isEmpty())
		{
			queue.enqueue(stack.pop());
		}
		while(!queue.isEmpty())
			result+=queue.dequeue();
			
		return result;
	}
	
	public static String convertPostfixToInfix(String e) throws StackOverflowException, StackUnderflowException, InvalidNotationFormatException
	{
		String result = "";
		NotationStack<String> s = new NotationStack<String>();
		for(int i=0; i<e.length(); i++)
		{
			char c = e.charAt(i);
			if(c==' ')
				continue;
			else if(Character.isDigit(c))
				s.push(""+c);
			else if(c=='+'||c=='*'||c=='/'||c=='-')
			{
				if(s.size()<2)
					throw new InvalidNotationFormatException("The notation format is invalid");
				else
				{
					String leftOperator;
					String rightOperator;
					String operant ="";
					leftOperator = s.pop();
					rightOperator = s.pop();
					operant = "("+rightOperator+c+leftOperator+")";
					s.push(operant);
				}
			}		
		}
		result += s.pop();
		
		return result;
	}
	/**
	 * Evaluates a postfix expression from a string to a double 
	 * @param e the postfix expression in String format
	 * @return the evaluation of the postfix as a double
	 * @throws StackOverflowException
	 * @throws StackUnderflowException
	 * @throws InvalidNotationFormatException if the postfix expression format is invalid
	 */
	public static double evaluatePostfixExpression(String e) throws StackOverflowException, StackUnderflowException, InvalidNotationFormatException
	{
		double result=0.0;
		NotationStack<String> s = new NotationStack<String>();
		for(int i=0; i<e.length(); i++)
		{
			char c = e.charAt(i);
			if(c==' ')
				continue;
			else if(Character.isDigit(c))
				s.push(""+c);
			else if(c=='+'||c=='*'||c=='/'||c=='-')
			{
				if(s.size()<2)
					throw new InvalidNotationFormatException("The notation format is invalid");
				else
				{
					String leftside;
					String rightside;
					leftside = s.pop();
					double operant1 = Double.parseDouble(leftside);
					rightside = s.pop();
					double operant2 = Double.parseDouble(rightside);
					
					switch(c)
					{
						case '+':
							result = operant1 + operant2;
							break;
						case '-':
							result = operant2 - operant1;
							break;
						case '*':
							result = operant1 * operant2;
							break;
						case '/':
							result = operant2 / operant1;
							break;
					}
					s.push(""+result);
				}
			}
		}
		if(stack.size()>1)
			throw new InvalidNotationFormatException("The notation is invalid");
		else
			return result;
	}
	
	/**
	 * Precedence class
	 * @param op operator
	 * @return the operator
	 */
	public static int precedenceCheck(char op) {
		
			switch (op) 
			{
				case '+':
				case '-':
					return 1;
				case '*':
				case '/':
					return 2;
				default:
					return 0;
			}
		}
	}
