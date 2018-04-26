/* File: ValidationException.java
 * Author: Nsang Joanne
 * Date: January 2018
 * Description: Demonstration of DAO Design Pattern with Servlet website
 */
package business;

public class ValidationException extends Exception {

    public ValidationException() {
        super("Data is not in the valid format");
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ValidationException(Throwable throwable) {
        super(throwable);
    }
}
