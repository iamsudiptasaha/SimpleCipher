/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleCipher.Expressions;

/**
 * Exception class for invalid expression format.
 * * @author Sudipta Saha
 * @since 21st June 2017
 * @version 1.0
 */
public class ExpressionFormatException extends Exception{

    ExpressionFormatException(String s) {
        super(s);
    }
    
}
