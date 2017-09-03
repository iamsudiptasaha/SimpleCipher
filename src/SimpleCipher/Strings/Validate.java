/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleCipher.Strings;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains functions related to regular expression validations.
 * @author Sudipta Saha
 * @since 21st JUNE 2017
 * @version 1.8
 */
public class Validate {
    
    
    /**
     * This function validates a string if it is not a first or last word in a sentence.
     * @param word String that needs to be validated.
     * 
     * @return True if the String is a valid word, else false.
     */
    public static boolean isMiddleWord(String word){
        Pattern pattern=Pattern.compile("^([A-Z]{0,1}[a-z]+(-[a-z]){0,1}[a-z]*[']{0,1}[a-z]*)$");
        Matcher matcher=pattern.matcher(word);
        return matcher.matches();
    }
    
    /**
     * This function validates a string if it is a starting word in a sentence.
     * @param word String that needs to be validated.
     * 
     * @return True if the String is a valid word, else false.
     */
    public static boolean isFirstWord(String word){
       Pattern pattern=Pattern.compile("^([A-Z]{1}[a-z]+(-[a-z]){0,1}[a-z]*[']{0,1}[a-z]*)$");
       Matcher matcher=pattern.matcher(word);
       return matcher.matches();
    }
    
    /**
     * This function validates a string if it is a terminating word in a sentence.
     * @param word String that needs to be validated.
     * 
     * @return True if the String is a valid word, else false.
     */
    public static boolean isDefaultLastWord(String word){
       Pattern pattern=Pattern.compile("^([A-Z]{0,1}[a-z]+(-[a-z]){0,1}[a-z]*[']{0,1}[a-z]*)([?!.]{1})$");
       Matcher matcher=pattern.matcher(word);
       return matcher.matches();
    }
    
    /**
     * This function validates a string if it is a terminating word in a sentence. The terminating parameters are user defined.
     * @param word String that needs to be validated.
     * 
     * @param delimeters The ending parameters for the word to validate word termination. The parameters 
     * should be placed in sequence without any gaps. For backslash, you need to put '\\\\' because the 
     * string parser will remove two of them when "de-escaping" it for the string, and then the regex needs 
     * two for an escaped regex backslash.
     * <pre>For example : isCustomLastWord(yourString,"/}\\\\") will check for /, }, \ as word termination parameters.</pre>
     * 
     * @return True if the String is a valid word, else false.
     */
    public static boolean isCustomLastWord(String word,String delimeters){
       Pattern pattern=Pattern.compile("^([A-Z]{0,1}[a-z]+(-[a-z]){0,1}[a-z]*[']{0,1}[a-z]*)(["+delimeters+"]{1})$");
       Matcher matcher=pattern.matcher(word);
       return matcher.matches();
    }
   
    /**
     * This function validates if a string is valid a sentence.
     * @param sentence String that needs to be validated.
     * 
     * @return True if the String is a valid word, else false.
     */
    public static boolean isSentence(String sentence){
       // Pattern firstWord=Pattern.compile("^([\"]{0,1}[A-Z]{0,1}(([a-z]+[-]{0,1}[']{0,1}[a-z]*)+[']{0,1})(\\?\"){0,1}[\"]{0,1}[,;]{0,1})*$");
         Pattern lastWord=Pattern.compile("^([\"]{0,1}[A-Z]{0,1}(([a-z]+(-[a-z]){0,1}[a-z]*[']{0,1}[a-z]*))((\\?\")|(!\")){0,1}[\"]{0,1}[,;]{0,1})[.!?]{1}$");
        Pattern firstWord=Pattern.compile("^([\"]{0,1}[A-Z]{1}(([a-z]*(-[a-z]){0,1}[a-z]*[']{0,1}[a-z]*))((\\?\")|(!\")){0,1}[\"]{0,1}[,;]{0,1})$");
       
        Pattern midWord=Pattern.compile("^([\"]{0,1}[A-Z]{0,1}(([a-z]+[-]{0,1}[a-z]*[']{0,1}[a-z]*))((\\?\")|(!\")){0,1}[\"]{0,1}[,;]{0,1})$");
        int quoteCount=0;
        Matcher matcher=null;
        String[] cool=sentence.split("[\\s\\xA0]+");
        for(int i=0;i<cool.length;i++){
           // System.out.print("\n at "+i+" word : "+cool[i]);
            if(cool[i].contains("\""))
                quoteCount++;
            if(i==cool.length-1 && lastWord.matcher(cool[i]).matches()==false){
               return false;
            }
            else if(i==0 && firstWord.matcher(cool[i]).matches()==false){
               
                return false;
                
            }
            else if(i>0 && i<cool.length-1){
                
                if(midWord.matcher(cool[i]).matches()==false && !cool[i].equals("I")){
                    return false;
                }
                
                      
            
            }
           
          
          // System.out.print("\n"+i+" = "+cool[i]+" matched : "+matcher.matches());
           
          
        }
        if(quoteCount%2!=0)
            return false;
        return true;
    }
    
    /**
     * This function validates if a string is valid a phone number for an user defined format.
     * @param phoneno String that needs to be validated.
     * 
     * @param format The custom format of the phone number. 
     * <pre>
     * For example XXX-XXX where X has to be in uppercase and X represents a possible digit.
     * </pre>
     * @return True if the String is a valid word, else false.
     * 
     * @throws InvalidFormatException Throws exception on format mismatch.
     */
    public static boolean isPhoneno(String phoneno, String format) throws InvalidFormatException{
        Pattern pattern=Pattern.compile("^X+([-]{1}X+)*$");
        Matcher matcher=pattern.matcher(format);
        if(matcher.matches()){
            StringBuffer sb=new StringBuffer("^");
            String regex;
            StringTokenizer st=new StringTokenizer(format, "-");
            while(st.hasMoreTokens()){
               
           
                sb.append("([0-9]{"+st.nextToken().length()+"})-");
              
               
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("$");
          
      
            regex=sb.toString();
            pattern=Pattern.compile(regex);
            matcher=pattern.matcher(phoneno);
            return matcher.matches();
        }
        else{
            throw new InvalidFormatException("Not a valid phone number format.");
        }
       
    }
    
    /**
     * This function validates if a string is valid a valid name. The honorific is allowed if user defined.
     * @param name String that needs to be validated.
     * 
     * @param checkHonorific True if name includes a honorific else false.
     * 
     * @param isAllUpperCase True is all the letters are in uppercase, else false. Then it checks if the first letter of every starting word is in uppercase.
     * 
     * @return True if the String is a valid word, else false.
     */
    public static boolean isName(String name, boolean checkHonorific,boolean isAllUpperCase){
        Pattern pattern;
        String range;
        if(isAllUpperCase)
            range="[A-Z]";
        else
            range="[a-z]";
        
        if(!checkHonorific)
            pattern=Pattern.compile("^(([A-Z]{1}"+range+"+))(\\s([A-Z]{1}"+range+"+))+$");
        else
            pattern=Pattern.compile("^([A-z]{1}"+range+"{0,4}[.]{1}) (([A-Z]{1}"+range+"+))(\\s([A-Z]{1}"+range+"+))+$");
         
        Matcher matcher=pattern.matcher(name);
        return matcher.matches();
    }
    
    /**
     * <pre>
     * This function checks if a String satisfies predetermined password parameters. The defaultSettings here are predetermined. 
     * defaultSettings values are : 
     * 1 if check for any 8 characters including alphabets, digits and symbols.
     * 2 if check for minimum 8 characters including minimum one uppercase and one digit.
     * 3 if check for minimum 8 characters including minimum one uppercase, digit, symbol and 8 characters.
     * </pre>
     * @param password String that needs to be validated.
     * 
     * @param defaultSettings Can be 1, 2 or 3. Each are entitled to different parameters.
     * <pre>
     * 1 if check for any 8 characters including alphabets, digits and symbols.
     * 2 if check for minimum 8 characters including minimum one uppercase and one digit.
     * 3 if check for minimum 8 characters including minimum one uppercase, digit, symbol and 8 characters.
     * </pre>
     * @return True if the String is a valid word, else false.
     */
    public static boolean isDefaultPassword(String password, int defaultSettings){
         Pattern pattern=null;
        switch(defaultSettings){
            case 1:  //8 characters any 
                    pattern=Pattern.compile("^[A-z0-9$@$!%*#?&]{8,}$");
                   
                     break;
            case 2: //minimum one uppercase and one digit, minimum 8 characters
                    pattern=Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])[A-z0-9$@$!%*#?&]{8,}$");     
                     break;
            case 3: //minimum one uppercase, digit, symbol and 8 characters
                     pattern=Pattern.compile("^(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-z0-9$@$!%*#?&]{8,}$");     
                     break;
            default: return false;
        }
        Matcher matcher=pattern.matcher(password);
        
        return matcher.matches();
    }
    
    /**
     * This function checks for individual parameter validations, i.e it individually checks for alphabets, digits and symbols. 
     * <pre>
     * Returns a boolean array of length 5. Each index has it's own significance. Default value is false.
     * Index 0 - Alphabet validation along with minimum alphabet length check.
     * Index 1 - Digit validation along with minimum digit length check.
     * Index 2 - Symbol validation along with minimum symbol length check.
     * Index 3 - Total minimum length check.
     * Index 4 - Total maximum length check.
     * Under any circumstances if pattern has characters that are not in the argument string, then index 0, 1, 2 will return false.
     * For example, if alphabets are "ABCdef", digits are "012" and symbol set is "$%" with minimum characters 2,1,1 respectively.
     * Then for pattern "ABC",
     * index 0 : true, index 1 : false, index 2: false.
     * for pattern "AB0"
     * index 0 : true, index 1 : true, index 2: false.
     * but for pattern "AB04", even if alphabet satisfies, but pattern as a whole is invalid hence output will be 
     * index 0 : false, index 1 : false, index 2: false.
     * </pre>
     * @param password String that needs to be validated.
     * @param customPasswordFilter instance of CustomPasswordFilter. 
     * @return True if the String is a valid word, else false.
     */
    public static boolean[] isCustomPasswordParametric(String password, CustomPasswordFilter customPasswordFilter){
       StringBuffer minAlphabet=null;
       StringBuffer minDigit=null;
       StringBuffer minSymbol=null;
       StringBuffer totalBuffer=new StringBuffer("[");
       Pattern pattern= null;
        Matcher matcher=null;
       boolean flagList[]=new boolean[5];
       if(customPasswordFilter.getAlphabetSet()!=null){
            minAlphabet=new StringBuffer();
            
            minAlphabet.append("(?=.*["+customPasswordFilter.getAlphabetSet()+"]{"+customPasswordFilter.getMinAlphabets()+"})");
            totalBuffer.append(customPasswordFilter.getAlphabetSet());
            
       //     System.out.print("\nAlphabet Pattern : "+totalBuffer.toString());
         //   pattern=Pattern.compile(minBuffer.toString()+totalBuffer.toString());
        //    matcher=pattern.matcher(password);
        //    flagList[0]=matcher.matches();
       }
       
       if(customPasswordFilter.getDigitSet()!=null){
           minDigit=new StringBuffer();
          
            minDigit.append("(?=.*["+customPasswordFilter.getDigitSet()+"]{"+customPasswordFilter.getMinDigits()+"})");
            totalBuffer.append(customPasswordFilter.getDigitSet());
        //    System.out.print("\nDigit Pattern : "+totalBuffer.toString());
        //    pattern=Pattern.compile(totalBuffer.toString());
        //    matcher=pattern.matcher(password);
        //    flagList[1]=matcher.matches();
       }
        
       if(customPasswordFilter.getSymbolSet()!=null){
           minSymbol=new StringBuffer();
          
            minSymbol.append("(?=.*["+customPasswordFilter.getSymbolSet()+"]{"+customPasswordFilter.getMinSymbols()+"})");
            totalBuffer.append(customPasswordFilter.getSymbolSet());
           
        //    pattern=Pattern.compile(minBuffer.toString()+totalBuffer.toString());
        //    matcher=pattern.matcher(password);
        //    flagList[2]=matcher.matches();
       }
     //   System.out.print("\nTotal Pattern : "+totalBuffer.toString());
       
       if(customPasswordFilter.getMaxCharacter()!=0){
           
        totalBuffer.append("]{"+customPasswordFilter.getMinCharacter()+","+customPasswordFilter.getMaxCharacter()+"}");
         //  if(password.length()>=customPasswordFilter.getMinAlphabets() && password.length()<=customPasswordFilter.getMaxCharacter())
           if(password.length()<=customPasswordFilter.getMaxCharacter())    
               flagList[4]=true;
           else
               flagList[4]=false;
               }
       else{
           totalBuffer.append("]{"+customPasswordFilter.getMinCharacter()+",}");
        
       }
       
       if(password.length()>=customPasswordFilter.getMinCharacter())
           flagList[3]=true;
       else 
           flagList[3]=false;
      
       
       
       if(minAlphabet!=null){
           pattern=Pattern.compile("^"+minAlphabet.toString()+totalBuffer.toString()+"$");
            matcher=pattern.matcher(password);
            flagList[0]=matcher.matches();
           System.out.print("\nSymbol Pattern : "+pattern.toString());
       }
       if(minDigit!=null){
           pattern=Pattern.compile(minDigit.toString()+totalBuffer.toString());
            matcher=pattern.matcher(password);
            flagList[1]=matcher.matches();
       }
       if(minSymbol!=null){
           pattern=Pattern.compile(minSymbol.toString()+totalBuffer.toString());
            matcher=pattern.matcher(password);
            flagList[2]=matcher.matches();
       }
       
        // System.out.print("\nSymbol Pattern : "+totalBuffer.toString());
       return flagList;
    }
    /**
     * This function allows you to design custom password parameters.
     * @param password String that needs to be validated.
     * @param customPasswordFilter instance of CustomPasswordFilter. 
     * @return True if the String is a valid word, else false.
     */
    public static boolean isCustomPassword(String password,CustomPasswordFilter customPasswordFilter){
       StringBuffer minBuffer=new StringBuffer("^");
       StringBuffer totalBuffer=new StringBuffer("[");
       
       if(customPasswordFilter.getAlphabetSet()!=null){
            minBuffer.append("(?=.*["+customPasswordFilter.getAlphabetSet()+"]{"+customPasswordFilter.getMinAlphabets()+"})");
            totalBuffer.append(""+customPasswordFilter.getAlphabetSet()+"");
       }
       
       if(customPasswordFilter.getDigitSet()!=null){
            minBuffer.append("(?=.*["+customPasswordFilter.getDigitSet()+"]{"+customPasswordFilter.getMinDigits()+"})");
            totalBuffer.append(""+customPasswordFilter.getDigitSet()+"");
       }
        
       if(customPasswordFilter.getSymbolSet()!=null){
            minBuffer.append("(?=.*["+customPasswordFilter.getSymbolSet()+"]{"+customPasswordFilter.getMinSymbols()+"})");
            totalBuffer.append(""+customPasswordFilter.getSymbolSet()+"");
       }
       if(customPasswordFilter.getMaxCharacter()!=0)
            totalBuffer.append("]{"+customPasswordFilter.getMinCharacter()+","+customPasswordFilter.getMaxCharacter()+"}$");
       else
            totalBuffer.append("]{"+customPasswordFilter.getMinCharacter()+",}$");
       
      //  System.out.print("\n"+minBuffer.toString()+totalBuffer.toString());
        
        Pattern pattern= Pattern.compile(minBuffer.toString()+totalBuffer.toString());
        Matcher matcher=pattern.matcher(password);
        System.out.print("\n regex : "+minBuffer.toString()+totalBuffer.toString());
        return matcher.matches();
    }
    
    
    /**
     * This function checks if an input string has binary bits, i.e if they are made up of 1s and 0s.
     * @param binary String that needs to be validated.
     * @return True if the String is a valid binary input, else false.
     */
    public static boolean isBinary(String binary){
        Pattern pattern=Pattern.compile("^[01]+$");
         Matcher matcher=pattern.matcher(binary);
         return matcher.matches();
    }
    
    
    /**
     * This function checks if an input string has hexadecimal bits, i.e if they are made up of 0-9 and A-F.
     * @param hex String that needs to be validated.
     * @return True if the String is a valid hexadecimal input, else false.
     */
    public static boolean isHex(String hex){
        Pattern pattern=Pattern.compile("^[0123456789ABCDEF]{2,}$");
        Matcher matcher=pattern.matcher(hex);
         return (matcher.matches() && (hex.length()%2==0));
    }

    /**
     * <pre>This function checks if an input string has alphanumeric bits, i.e if they are made of 0-9 and A-Z.
     * <b>Alphabets have to be in Uppercase.</b></pre>
     * @param alphanumeric String that needs to be validated.
     * @return True if the String is a valid alphanumeric input, else false.
     */
    public static boolean isAlphanumeric(String alphanumeric){
         Pattern pattern=Pattern.compile("^[A-z0-9]+$");
        Matcher matcher=pattern.matcher(alphanumeric);
         return (matcher.matches());
    }
    
}
