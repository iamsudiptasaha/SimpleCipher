/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleCipher.Expressions;

import java.util.Stack;
import java.util.StringTokenizer;


/**
 * Contains format conversion functions.
 * @author Sudipta Saha
 * @since 21st JUNE 2017
 * @version 1.0
 */
public class Conversion {
    
    /**
     * This function checks if an expression is arithmetically valid or not.
     * 
     * @param eq <pre>infix arithmetic expression.
     * <b>The expression needs to be enclosed with opening and closing brackets.</b>
     * Operations that are allowed are : 
     * | : Square root. (√)
     * ^ : Power.
     * * and / : multiplication and division.
     * + and - : addition and subtraction. 
     * Incase of using negative numbers, for example (-3), enter it as "(,0,-,3,)".  
     * </pre>
     * 
     * @return True if valid expression, else false.
     * 
     * @throws ExpressionFormatException Throws exception if infix arithmetic expression is invalid .
     */
    private static boolean validExpression(String eq) throws ExpressionFormatException{
        Stack bracket=new Stack();
        StringTokenizer st=new StringTokenizer(eq, ",");
        String temp,sym;
        boolean flag=true;
         if(eq.charAt(0)!='(' || eq.charAt(eq.length()-1)!=')'){
             throw new ExpressionFormatException("Missing opening or closing brackets.");
         }
        while(st.hasMoreTokens()){
            temp=st.nextToken();
            switch(temp.trim()){
                case "(":bracket.push(temp);
                         break;
                case ")":if(!bracket.isEmpty()){
                            
                            while(!bracket.peek().toString().equals("(")){
                                
                                bracket.pop();
                    
                            }
                            bracket.pop();
                            if(!bracket.isEmpty())
                                bracket.push("5");
                         
                        }
                        else{
                            throw new ExpressionFormatException("Missing opening or closing braces.");
                        }
                         break;
                case "|":bracket.push(temp);
                         break;   
                case "^":if(!bracket.isEmpty()){
                            sym=bracket.peek().toString();
                             if(getSymbolPriority(bracket.peek().toString())!=0){
                                  throw new ExpressionFormatException("Missing opening or closing braces.");
                             }
                             else{
                                 bracket.push(temp);
                             }
                          }
                         else{
                            throw new ExpressionFormatException("Missing opening or closing braces.");
                        }
                         break;    
                case "*":if(!bracket.isEmpty()){
                            sym=bracket.peek().toString();
                             if(getSymbolPriority(bracket.peek().toString())!=0){
                                  throw new ExpressionFormatException("Missing opening or closing braces.");
                             }
                             else{
                                 bracket.push(temp);
                             }
                          }
                         else{
                            throw new ExpressionFormatException("Missing opening or closing braces.");
                        }
                         break;    
                case "/":if(!bracket.isEmpty()){
                            sym=bracket.peek().toString();
                             if(getSymbolPriority(bracket.peek().toString())!=0){
                                  throw new ExpressionFormatException("Missing opening or closing braces.");
                             }
                             else{
                                 bracket.push(temp);
                             }
                          }
                         else{
                            throw new ExpressionFormatException("Missing opening or closing braces.");
                        }
                         break;           
                case "+":if(!bracket.isEmpty()){
                            sym=bracket.peek().toString();
                             if(getSymbolPriority(bracket.peek().toString())!=0){
                                  throw new ExpressionFormatException("Missing opening or closing braces.");
                             }
                             else{
                                 bracket.push(temp);
                             }
                          }
                         else{
                            throw new ExpressionFormatException("Missing opening or closing braces.");
                        }
                         break;    
                case "-":if(!bracket.isEmpty()){
                            sym=bracket.peek().toString();
                             if(getSymbolPriority(bracket.peek().toString())!=0){
                                  throw new ExpressionFormatException("Missing opening or closing braces.");
                             }
                             else{
                                 bracket.push(temp);
                             }
                          }
                         else{
                            throw new ExpressionFormatException("Missing opening or closing braces.");
                        }
                         break;    
                case "": throw new ExpressionFormatException("Cannot accept blank characters.");
                         
                default: 
                       try {
                       Double.parseDouble(temp);
                         bracket.push(temp);
                 
                        } catch (NumberFormatException e) {
                            throw new ExpressionFormatException("Unknown numerical character. at '"+temp+"'");
                        }
                       break;
            }
            
           
        }
         if(bracket.isEmpty()){
                return true;
            }
       //  System.out.print("\n"+bracket.toString());
         return false;
    }
    
    /**
     * This function converts a given infix string to postfix.
     * 
     * @param eq The infix string.
     * 
     * @return The equivalent postfix string.
     * 
     * @throws ExpressionFormatException Throws exception if the entered infix expression is invalid.
     */
    public static String infix_to_Postfix(String eq) throws ExpressionFormatException{
        StringBuffer postEq=new StringBuffer("");
        StringTokenizer st=new StringTokenizer(eq,",");
        String temp;
        Stack symbolStack=new Stack();
        Stack postfixStack=new Stack();
      //  char[] postEq=new char[st.countTokens()];
        int i=0;
        if(validExpression(eq)){
        while(st.hasMoreTokens()){
            temp=st.nextToken();
            switch(temp){
                case "("://System.out.print("\n"+temp);
                         symbolStack.push(temp);
                         break;
                case ")"://System.out.print("\n"+temp);
                         while(!symbolStack.peek().toString().equals("(")){
                             postEq.append(symbolStack.pop()+",");
                         }
                         symbolStack.pop();
                         break;
                case "|"://System.out.print("\n"+temp);
                         if(getSymbolPriority(symbolStack.peek().toString())!=5){
                             if(getSymbolPriority(temp)>=getSymbolPriority(symbolStack.peek().toString())){
                                symbolStack.push(temp);
                             }
                             else{
                                 postEq.append(symbolStack.pop()+",");
                                 symbolStack.push(temp);
                             }   
                         }
                         else{
                             symbolStack.push(temp);
                         }
                         break;   
                case "^"://System.out.print("\n"+temp);
                        if(getSymbolPriority(symbolStack.peek().toString())!=5){
                             if(getSymbolPriority(temp)>=getSymbolPriority(symbolStack.peek().toString())){
                                symbolStack.push(temp);
                             }
                             else{
                                  postEq.append(symbolStack.pop()+",");
                                 symbolStack.push(temp);
                             }   
                         }
                         else{
                             symbolStack.push(temp);
                         }
                         break;    
                case "*"://System.out.print("\n"+temp);
                        if(getSymbolPriority(symbolStack.peek().toString())!=5){
                             if(getSymbolPriority(temp)>=getSymbolPriority(symbolStack.peek().toString())){
                                symbolStack.push(temp);
                             }
                             else{
                                 postEq.append(symbolStack.pop()+",");
                                 symbolStack.push(temp);
                             }   
                         }
                         else{
                             symbolStack.push(temp);
                         }
                         break;   
                case "/"://System.out.print("\n"+temp);
                         if(getSymbolPriority(symbolStack.peek().toString())!=5){
                             if(getSymbolPriority(temp)>=getSymbolPriority(symbolStack.peek().toString())){
                                symbolStack.push(temp);
                             }
                             else{
                                 postEq.append(symbolStack.pop()+",");
                                 symbolStack.push(temp);
                             }   
                         }
                         else{
                             symbolStack.push(temp);
                         }
                         break;        
                case "+"://System.out.print("\n"+temp);
                        if(getSymbolPriority(symbolStack.peek().toString())!=5){
                             if(getSymbolPriority(temp)>=getSymbolPriority(symbolStack.peek().toString())){
                                symbolStack.push(temp);
                             }
                             else{
                                 postEq.append(symbolStack.pop()+",");
                                 symbolStack.push(temp);
                             }   
                         }
                         else{
                             symbolStack.push(temp);
                         }
                         break;
                case "-"://System.out.print("\n"+temp);
                         if(getSymbolPriority(symbolStack.peek().toString())!=5){
                             if(getSymbolPriority(temp)>=getSymbolPriority(symbolStack.peek().toString())){
                                symbolStack.push(temp);
                             }
                             else{
                                 postEq.append(symbolStack.pop()+",");
                                 symbolStack.push(temp);
                             }   
                         }
                         else{
                             symbolStack.push(temp);
                         }
                         break;             
                default: 
                      // System.out.print("\n"+Double.parseDouble(temp));
                       postEq.append(temp+",");
                
                break;
            }
        }
        if(symbolStack.isEmpty()){
            return postEq.deleteCharAt(postEq.length()-1).toString();
        }
        else{
           throw new ExpressionFormatException("Not a valid expression.");
        }
        
        }
        else{
           throw new ExpressionFormatException("Not a valid expression.");
        }
        
    }
      
    
    
    /**
     * This function converts a given infix string to prefix.
     * 
     * @param eq The infix string.
     * 
     * @return The equivalent prefix string.
     * 
     * @throws ExpressionFormatException Throws exception if the entered infix expression is invalid.
     */
    public static String infix_to_Prefix(String eq) throws ExpressionFormatException{
         System.out.print("\n no rev : "+eq);
        StringBuffer prefixEq=new StringBuffer(eq);
        prefixEq.reverse();
        String temp=prefixEq.toString();
        System.out.print("\n Rev : "+temp);
        temp=temp.replace(')', '/');
        temp=temp.replace('(', ')');
        temp=temp.replace('/', '(');
        temp=infix_to_Postfix(temp);
        prefixEq=new StringBuffer(temp);
        prefixEq.reverse();
      //  System.out.print(" Final : "+prefixEq.toString());
        return prefixEq.toString();
    }
    
    /**
     * This function returns symbol priority.
     * @param symbol Either of symbols (,|,^,*,/,+,-
     * @return <pre>Following priority :
     * ( : 5 
     * | : 4
     * ^ : 3
     * * or / : 2
     * + or - : 1
     * </pre>
     */   
    private static int getSymbolPriority(String symbol){
        switch(symbol){
            case "(":return 5;   
            case "|":return 4;   
            case "^":return 3;    
            case "*":return 2;   
            case "/":return 2;        
            case "+":return 1;
            case "-":return 1;    
        }
        return 0;
    }
}
