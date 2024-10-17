package model;

import java.time.LocalDate;

public abstract class Material implements Discount {
	private String id;
	private String name;
	private LocalDate manufacturingDate;
	private int cost;

	public Material(String id, String name, String manufacturingDate, int cost) {
		this.id = id;
		this.name = name;
		this.manufacturingDate = LocalDate.parse(manufacturingDate);
		this.cost = cost;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getManufacturingDate() {
		return this.manufacturingDate;
	}

	public void setManufacturingDate(String manufacturingDate) {
		this.manufacturingDate = LocalDate.parse(manufacturingDate);
	}

	public int getCost() {
		return this.cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return this.getId() + " - " + this.getName();
	}

	public abstract double getAmount();
	public abstract LocalDate getExpiryDate();
}
