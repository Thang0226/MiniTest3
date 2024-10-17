package model;

import java.time.LocalDate;

public class Meat extends Material {
	private double weight;

	public Meat(String id, String name, String manufacturingDate, int cost, double weight) {
		super(id, name, manufacturingDate, cost);
		this.weight = weight;
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public double getAmount() {
		return this.getWeight() * super.getCost();
	}

	@Override
	public LocalDate getExpiryDate() {
		return super.getManufacturingDate().plusDays(7);
	}

	@Override
	public double getRealMoney() {
		return this.getAmount() * 0.97;
	}

	@Override
	public String toString() {
		return "Meat        - " + super.toString() + " - " + super.getCost() + ",000 VND/kg";
	}
}
