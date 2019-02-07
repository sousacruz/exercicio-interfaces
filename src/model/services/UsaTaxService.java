package model.services;

import model.entities.Vehicle;

public class UsaTaxService implements TaxService {

	public double tax(double amount) {
		if (amount <= 100.0) {
			return amount * 0.2;
		}
		else {
			return amount * 0.15;
		}
	}
	
	public double insurance(Vehicle car) {
		return car.getValue() * 0.20;
	}
}