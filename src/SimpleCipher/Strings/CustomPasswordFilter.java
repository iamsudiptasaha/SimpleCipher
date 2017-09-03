/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SimpleCipher.Strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create object of this class to enable custom password filters. 
 * @author Sudipta Saha
 * @since 21st June 2017
 * @version 1.2
 */
public class CustomPasswordFilter {
private String alphabetSet;
private String digitSet;
private String symbolSet;
private int minCharacter;
private int maxCharacter;
private int minAlphabets;
private int maxAlphabets;
private int minDigits;
private int maxDigits;
private int minSymbols;
private int maxSymbols;
private Pattern pattern;    
private Matcher matcher;

    
    /**
     * This function returns an instance of CustomPasswordFilter. It is required in case of user defined password parameters. 
     * 
     * @param alphabetSet String of alphabets that the password may contain. Separate alphabets for uppercase and lowercase. The alphabets need to be put sequentially without any space.
     * <pre>
     * For example, "ABCdef" will check for A,B,C,d,e,f and will not allow a,b,c,D,E,F.
     * </pre>
     * 
     * @param minAlphabets The minimum number of alphabet characters that the custom password may contain.
     * 
     * @param digitSet String of digits that the password may contain. The digits need to be put sequentially without any space.
     * <pre>For example, "0178" will check for 0,1,7,8.
     * </pre>
     * 
     * @param minDigits The minimum number of digit characters that the custom password may contain.
     * 
     * @param symbolSet String of symbols that the password may contain. The set of symbols permitted are $,@,!,%,*,#,?,&amp; The digits need to be put sequentially without any space.
     * 
     * @param minSymbols The minimum number of symbol characters that the custom password may contain.
     * 
     * @param minCharacter Total number of minimum characters the password might contain.
     * 
     * @param maxCharacter Total number of maximum characters the password might contain. 
     * <b>In case of unrestricted maximum characters, put maxCharacter as 0.</b>
     * 
     * @return instance of CustomPasswordFilter.
     * 
     * @throws InvalidFormatException Throws exception if any of the input strings of alphabets, digits or symbols are invalid.
     */
    public static CustomPasswordFilter getInstance(String alphabetSet, int minAlphabets, String digitSet,int minDigits, String symbolSet, int minSymbols, int minCharacter,int maxCharacter) throws InvalidFormatException{
        //CustomPasswordFilter customPasswordFilter=n
       
            return new CustomPasswordFilter(alphabetSet, minAlphabets, 0, digitSet, minDigits, 0, symbolSet, minSymbols, 0, minCharacter, maxCharacter);
        
       
    }
    
    
    private CustomPasswordFilter(String alphabetSet, int minAlphabets,int maxAlphabets, String digitSet,int minDigits, int maxDigits, String symbolSet, int minSymbols, int maxSymbols, int minCharacter,int maxCharacter) throws InvalidFormatException{
        setAlphabetSet(alphabetSet, minAlphabets, maxAlphabets);
        setDigitSet(digitSet, minDigits, maxDigits);
        setSymbolSet(symbolSet, minSymbols, maxSymbols);
        setMinCharacter(minCharacter);
        setMaxCharacter(maxCharacter);
        
        
            
    }
    
    /**
     * @return the alphabetSet
     */
    String getAlphabetSet() {
        return alphabetSet;
    }

    /**
     * @param alphabetSet the alphabetSet to set
     */
    private void setAlphabetSet(String alphabetSet, int minAlphabets, int maxAlphabets) throws InvalidFormatException {
        pattern=Pattern.compile("^(?:([A-Za-z])(?!.*\\\\1))*$");
        if(alphabetSet==null || alphabetSet.trim().length()==0)
            alphabetSet=null;
        else{
           matcher=pattern.matcher(alphabetSet);
           if(matcher.matches()){
               this.alphabetSet=alphabetSet;
               this.minAlphabets=minAlphabets;
               if(maxAlphabets==0)
                    this.maxAlphabets=this.getMinAlphabets();
               else
                    this.maxAlphabets = maxAlphabets;
           }
           else
               throw new InvalidFormatException("Alphabet characters mismatch.");
           
        }
    }

    /**
     * @return the digitSet
     */
    String getDigitSet() {
        return digitSet;
    }

    /**
     * @param digitSet the digitSet to set
     */
    private void setDigitSet(String digitSet, int minDigits, int maxDigits) throws InvalidFormatException{
         pattern=Pattern.compile("^(?:([0-9])(?!.*\\1))*$");
        if(digitSet==null || digitSet.trim().length()==0)
            digitSet=null;
        else{
           matcher=pattern.matcher(digitSet);
           if(matcher.matches()){
               this.digitSet=digitSet;
               this.minDigits=minDigits;
               if(maxDigits==0)
                    this.maxDigits=this.getMinDigits();
               else
                    this.maxDigits = maxDigits;
           }
           else
               throw new InvalidFormatException("Numeric characters mismatch.");
           
        }
    }

    /**
     * @return the symbolSet
     */
    String getSymbolSet() {
        return symbolSet;
    }

    /**
     * @param symbolSet the symbolSet to set
     */
    private void setSymbolSet(String symbolSet, int minSymbols, int maxSymbols) throws InvalidFormatException{
          pattern=Pattern.compile("^(?:([!@#$%^&?*])(?!.*\\1))*$");
        if(symbolSet==null || symbolSet.trim().length()==0)
            symbolSet=null;
        else{
           matcher=pattern.matcher(symbolSet);
           if(matcher.matches()){
               this.symbolSet=symbolSet;
               this.minSymbols=minSymbols;
               if(maxSymbols==0)
                    this.maxSymbols=this.getMinSymbols();
               else
                    this.maxSymbols = maxSymbols;
               }
           else
               throw new InvalidFormatException("Symbolic characters mismatch.");
           
        }
    }

    /**
     * @return the minCharacter
     */
    int getMinCharacter() {
        return minCharacter;
    }

    /**
     * @param minCharacter the minCharacter to set
     */
    private void setMinCharacter(int minCharacter) {
        this.minCharacter = minCharacter;
    }

    /**
     * @return the maxCharacter
     */
    int getMaxCharacter() {
        return maxCharacter;
    }

    /**
     * @param maxCharacter the maxCharacter to set
     */
    private void setMaxCharacter(int maxCharacter) {
        if(maxCharacter==0)
            this.maxCharacter=0;
        else
            this.maxCharacter = maxCharacter;
    }

    /**
     * @return the minAlphabets
     */
    int getMinAlphabets() {
        return minAlphabets;
    }

    /**
     * @return the minDigits
     */
    int getMinDigits() {
        return minDigits;
    }

    /**
     * @return the minSymbols
     */
    int getMinSymbols() {
        return minSymbols;
    }
    
    
}
