package org.isilavunduk.FlowerWebsiteApplication.exception;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//If Resource Not found, return Status Not Found Message
@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)   //ensures that when this exception is thrown, HTTP response status is set to 404 Not Found


//whenever custom exception is created we should extended RuntimeException. This approach allows for unchecked exceptions to be thrown
public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String resourceName;     //have a resource name in the exception
    private String fieldName;
    private Object fieldValue;  //can be any type


    public ResourceNotFoundException(String message) {   // message is the error message I want to display
        super(message);
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue){
        //By calling super which is RuntimeException, I'm passing a formatted string message to the superclass constructor.
        //This message provides info about the resource that was not found, along with the specific field and its corresponding
        //value that led to the exception. Custom message will be like  Post not found with id: 1
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
