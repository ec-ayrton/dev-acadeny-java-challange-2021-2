package br.com.cm.workshop.apicrud.controllers.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable{
    
    
	private static final long serialVersionUID = 1L;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")	
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;


	public StandardError(Instant now, int value, String error2, Map<String, String> errors, String requestURI) {
		
	}

}
