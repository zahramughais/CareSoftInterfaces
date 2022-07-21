package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

//... imports class definition...
public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {
	
    // Inside class:
    private Integer employeeID;
    private String role;
    private ArrayList<String> securityIncidents;
    
    // TO DO: Implement a constructor that takes an ID and a role
    public AdminUser(Integer id, String role) {
    	super(id);
    	this.role = role;
    	securityIncidents = new ArrayList<String>();
    }
    // TO DO: Setters & Getters
    public Integer getEmployeeID() {
    	return employeeID;
    }
    
    public void setEmployeeID(Integer employeeID) {
    	this.employeeID = employeeID;
    }
    
    public String getRole() {
    	return role;
    }
    
    public void setRole(String role) {
    	this.role = role;
    }
    
    public ArrayList<String> getSecurityIncidents() {
    	return securityIncidents;
    }
    
    public void setSecurityIncidents(ArrayList<String> securityIncidents) {
    	this.securityIncidents = securityIncidents;
    }
    
    public void newIncident(String notes) {
    	String report = String.format(
    			"Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
    			new Date(), this.id, notes
    			);
    	securityIncidents.add(report);
    }
    public void authIncident() {
    	String report = String.format(
    			"Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
    			new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
    			);
    	securityIncidents.add(report);
    }
    // TO DO: Implement HIPAACompliantAdmin!
    
    
    // TO DO: Implement HIPAACompliantUser!
    @Override
    public boolean assignPin(int pin) {
    	
    	int length = String.valueOf(pin).length();
    	
    	if (length >= 6) {
    		return true;
    	} else {
    		return false;
    	}
    	
    }
    @Override
    public boolean accessAuthorized(Integer confirmedAuthID) {
    	if (this.id == confirmedAuthID) {
    		return true;
    	} else {
    		this.authIncident();
    		return false;
    	}
    }
	@Override
	public ArrayList<String> reportSecurityIncidents() {
		return securityIncidents;
	}
    
}
