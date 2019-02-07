package model.services;

import model.entities.Vehicle;

public interface TaxService {
	
	double tax(double amount);
	double insurance(Vehicle car);
	
}
