package model.entities;

public class Vehicle {

	private String model;
	private Double value;
	
	public Vehicle() {
	}

	public Vehicle(String model, Double value) {
		this.model = model;
		this.value = value;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}