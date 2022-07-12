package com.neoflex.application.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Data
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ScoringException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    List<String> error;

    public ScoringException(String message) {
        super(message);
    }

    public ScoringException(List<String> error) {
        super(error.toString());
        this.error = error;
        scoringErrors();
    }

    public ResponseEntity<String> scoringErrors() {
        return new ResponseEntity<>(error.toString(), HttpStatus.BAD_REQUEST);
    }

}
