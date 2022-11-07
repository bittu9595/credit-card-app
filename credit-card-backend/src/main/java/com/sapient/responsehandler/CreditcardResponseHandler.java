/**
 * Response handler file to generate customized response.
 */
package com.sapient.responsehandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
public class CreditcardResponseHandler {
	/**
	 * Function to generate customized success response.
	 * @param message - response message to be passed to client.
	 * @param status - response status.
	 * @param responseObj - data to be passed to client.
	 */
	public static ResponseEntity<Object> generateSuccessResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", message);
            map.put("status", status.value());
            map.put("data", responseObj);

            return new ResponseEntity<Object>(map,status);
    }
	/**
	 * Function to generate customized success/error response.
	 * @param message - response message to be passed to client.
	 * @param status - response status.
	 */
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", message);
            map.put("status", status.value());

            return new ResponseEntity<Object>(map,status);
    }
}

