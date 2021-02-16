package com;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestScenarios {
	
	private CreateAccount ca;
	@BeforeEach                                         
    public void setUp() throws Exception {
        ca = new CreateAccount();
    }
	@Test
	void testAccountCreationDetails_isValid() {
		final String email = "test@gmail.com";
		final String password = "Passw0rd!";
		final String confirmPass = "Passw0rd!";
        List<String> errorList = new ArrayList<>();

	assertEquals(true,ca.isValid(email, password, confirmPass, errorList), "Details are all valid"); 

	}
	
	@Test
	void testAccountCreationDetails_isNotValid() {
		final String email = "test@gmail";
		final String password = "Passw0rd!";
		final String confirmPass = "Passw0rd!";
        List<String> errorList = new ArrayList<>();

	assertEquals(false,ca.isValid(email, password, confirmPass, errorList), "Email not valid"); 

	}

}
