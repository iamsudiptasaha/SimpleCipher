# SimpleCipherJava
This package hosts an array of frequently used regex validations and regex expression evaluation functionalities. In general, String check encompasses last word check, middle word check, first word check, sentence validation, phone number validation, name validation with or without honorific, password with both default parameter settings and custom parameter settings, hexadecimal string validation,alphanumeric string validation and binary string validations. Expression resolving encompasses conversions like infix to postfix and infix to prefix, and evaluation compasses java equation solving and resultant equation output.

The package makes use of intense regex expressions for peak validation under default settings. SimpleCipher is highly useful for developers who are in need for rapid and accurate FORM VALIDATIONS
that are available over both the Web, Android and Javascript.

Name validation:
------------------------------------------------------------------------------

According to javadoc,

/**
 * This function validates if a string is valid a valid name. The
honorific is allowed if user defined.
 * @param name String that needs to be validated.
 * @param checkHonorific True if name includes a honorific else false.
 * @param isAllUpperCase True is all the letters are in uppercase, else
false. Then it checks if the first letter of every starting word is in
uppercase.
 *
 * @return True if the String is a valid word, else false.
 */
 
String name=”Paul Scholes”;

boolean check=SimpleCipher.Strings.Validate.isName(userInputName, false, false);



Custom password validation:
---------------------------------------------------------------------------------
Allows developers to decide their own password restriction parameters. First create an object of CustomPasswordFilter with desired precision parameters. For example,

CustomPasswordFilter cpf=CustomPasswordFilter.getInstance("ABCdef", 1, null, 0,"%$", 2, 1, 0);

The above piece of code, creates a custom password precision that shall allow a password to have

Parameter 1: Alphabets “ABCdef”.

Parameter 2: Minimum 1 alphabet.

Parameter 3: Ward off numeric digit [0-9] restriction.

Parameter 4: Minimum number of numeric digit character. 0 since we already warded off digit validation.

Parameter 5: Symbols “%$”.

Parameter 6: Minimum 2 symbols.

Parameter 7: Total number of minimum characters in the password.

Parameter 8: Total number of maximum characters in the password, 0 if no restriction.

For further and better explanation refer to javadoc.

Once this is done, we can now apply for any password validation with above defined restrictions,


boolean check=SimpleCipher.Strings.Validate.isCustomPassword(userInputPassword,cpf);


This returns only true or false based on userInputPassword match, however developers may want to let their user know of individual field validation by creating progress dialog box that ticks off the validated fields. 

To allow such parametric validations, developers can use function isCustomPasswordParametric(userInputPassword, CustomPasswordFilter), that returns a boolean array of size 5. The index definitions are as follows:

Index 0 - Alphabet validation along with minimum alphabet length check.

Index 1 - Digit validation along with minimum digit length check.

Index 2 - Symbol validation along with minimum symbol length check.

Index 3 - Total minimum length check.

Index 4 - Total maximum length check.

For in depth and better explanation refer to javadoc.



Expression Evaluation:
-----------------------------------------------------------------------------------------

The sub package Expressions is responsible for evaluating expressions.

For example, say we want to solve an expression 10 + ( 2 * 5 ).
Then the equivalent code shall be as follows,
Double result=SimpleCipher.Expressions.Evaluation.solveEq("(,10,+,(,2,*,5,),)");

For in depth and better explanation refer to javadoc.
