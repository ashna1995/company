package com.casestudy.stockmarket.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code=HttpStatus.CONFLICT, reason="CompanyCode already exists!")
public class CompanyCodeAlreadyExistsException extends Exception
{

}
