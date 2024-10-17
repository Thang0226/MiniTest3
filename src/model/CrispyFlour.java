package model;

import java.time.LocalDate;

public class CrispyFlour extends Material {
	private int quantity;

	public CrispyFlour(String id, String name, String manufacturingDate, int cost, int quantity) {
		super(id, name, manufacturingDate, cost);
		this.quantity = quantity;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public double getAmount() {
		return this.quantity * super.getCost();
	}

	@Override
	public LocalDate getExpiryDate() {
		return super.getManufacturingDate().plusYears(1);
	}

	@Override
	public double getRealMoney() {
		return this.getAmount() * 0.94;
	}

	@Override
	public String toString() {
		return "CrispyFlour - " + super.toString() + " - " + super.getCost() + ",000 VND/pack";
	}
}
