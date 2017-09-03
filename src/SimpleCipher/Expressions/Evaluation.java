/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleCipher.Expressions;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Contains functions related to arithmetic expression evaluation.
 * @author Sudipta Saha
 * @since 21st JUNE 2017
 * @version 1.0
 */
public class Evaluation {
    /**
     * <pre>This function solves an infix arithmetic expression. Each arithmetical term needs to be separated by comma. 
     * <b>The expression needs to be enclosed with opening and closing brackets.</b>
     * Operations that are allowed are : 
     * | : Square root. (√)
     * ^ : Power.
     * * and / : multiplication and division.
     * + and - : addition and subtraction. 
     * Incase of using negative numbers, for example (-3), enter it as "(,0,-,3,)".  
     * </pre>
     * <pre>
     * For example, "((10.2,+,3)*5)" is valid expression.
     * </pre>
     * 
     * @param equation Infix arithmetic expression that needs to be evaluated.
     * 
     * @return Value of the arithmetic expression, if the arithmetic expression is valid.
     * 
     * @throws ExpressionFormatException Throws exception if the entered infix expression is invalid.
     */
    public static double solveEq(String equation) throws ExpressionFormatException{
        String postfixExpr=Conversion.infix_to_Postfix(equation);
        Stack eval=new Stack();
        Double result;
        String temp;
        StringTokenizer st=new StringTokenizer(postfixExpr,",");
        while(st.hasMoreTokens()){
            temp=st.nextToken();
            switch(temp){
                case "|":calculate(eval, temp);
                         
                         
                         break;   
                case "^":calculate(eval, temp);
                         
                        
                         break;    
                case "*":calculate(eval, temp);
                         
                       
                         break;   
                case "/":calculate(eval, temp);
                         
                         
                         break;        
                case "+":calculate(eval, temp);
                         
                        
                         break;
                case "-":calculate(eval, temp);
                         
                        
                         break;             
                default: 
                       try {
                      // System.out.print("\n"+Double.parseDouble(temp));
                       eval.push(temp);
                 
                        } catch (NumberFormatException e) {
                            throw new ExpressionFormatException("Unknown numerical character.");
                        }
                       
                break;
            }
        }
      //  System.out.print(" Stack : "+eval.toString());
        return Double.parseDouble(eval.pop().toString());
    }
    
    /**
     * This function calculates binary arithmetic operands.
     * @param eval Stack containing arithmetic digits.
     * @param symbol Equivalent symbol of arithmetic operation. 
     */
    private static void calculate(Stack eval, String symbol){
        double a,b;
        switch(symbol){
           case "|"://System.out.print("\n"+temp);
                         eval.push(Math.sqrt(Double.parseDouble(eval.pop().toString())));
                         break;   
                case "^"://System.out.print("\n"+temp);
                        b=Double.parseDouble(eval.pop().toString());
                        a=Double.parseDouble(eval.pop().toString());
                        eval.push(Math.pow(a, b));
                         break;    
                case "*"://System.out.print("\n"+temp);
                        b=Double.parseDouble(eval.pop().toString());
                        a=Double.parseDouble(eval.pop().toString());
                        eval.push(a*b);
                         break;   
                case "/"://System.out.print("\n"+temp);
                         b=Double.parseDouble(eval.pop().toString());
                         a=Double.parseDouble(eval.pop().toString());
                         eval.push(a/b);
                         break;        
                case "+"://System.out.print("\n"+temp);
                         b=Double.parseDouble(eval.pop().toString());
                         a=Double.parseDouble(eval.pop().toString());
                         eval.push(a+b);
                         break;
                case "-"://System.out.print("\n"+temp);
                         b=Double.parseDouble(eval.pop().toString());
                         a=Double.parseDouble(eval.pop().toString());
                         eval.push(a-b);
                         break; 
        }
    }
}
