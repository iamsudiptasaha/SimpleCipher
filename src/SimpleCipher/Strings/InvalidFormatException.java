/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleCipher.Strings;

/**
 * Exception class for invalid expression format.
 * @author Sudipta Saha
 * @since 21st June 2017
 * @version 1.0
 */
public class InvalidFormatException extends Exception{
    InvalidFormatException(String s){
        super(s);
        System.out.print(s);
    } 
}
