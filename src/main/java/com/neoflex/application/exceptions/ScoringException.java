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

    private List<String> errors;

    public ScoringException(String message) {
        super(message);
    }

    public ScoringException(List<String> errors) {
        super(errors.toString());
        this.errors = errors;
        scoringErrors();
    }

    public ResponseEntity<String> scoringErrors() {
        return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
    }

}
