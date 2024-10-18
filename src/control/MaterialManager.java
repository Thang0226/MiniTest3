package control;

import model.*;

import java.util.Scanner;

public class MaterialManager {
	public static double getMoneySum(Material[] list) {
		double sum = 0;
		for (Material material : list) {
			sum += material.getRealMoney();
		}
		return Math.round(sum * 100000.0) / 100.0;
	}

	public static void printSortedMaterials(Material[] list) {
		// sort list
		for (int i = 0; i < list.length - 1; i++) {
			int maxCost = list[i].getCost();
			int index = i;
			for (int j = i + 1; j < list.length; j++) {
				if (list[j].getCost() > maxCost) {
					maxCost = list[j].getCost();
					index = j;
				}
			}
			if (index != i) {
				Material temp = list[i];
				list[i] = list[index];
				list[index] = temp;
			}
		}
		// print sorted materials
		printMaterials(list);
	}

	public static double getTotalDiscount(Material[] list) {
		double sum = 0;
		for (Material material : list) {
			sum += material.getAmount() - material.getRealMoney();
		}
		return Math.round(sum * 100000.0) / 100.0;
	}

	public static Material[] addNewMaterial(Material[] list) {
		String kind = inputMaterialKind();
		if (kind.equals("Meat")) {
			return addNewMeat(list);
		} else if (kind.equals("Crispy Flour")) {
			return addNewFlour(list);
		}
		return list;
	}

	public static String inputMaterialKind() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter kind of material (Meat/Crispy Flour): ");
		return input.nextLine();
	}

	public static Material[] addNewMeat(Material[] list) {
		Meat newMeat = inputNewMeat();
		int n = list.length;
		Material[] newList = new Material[n + 1];

		for (int i = 0; i < n; i++) {
			newList[i] = list[i];
		}
		newList[n] = newMeat;

		System.out.println("New meat material: " + newList[n].getName() + " added!");
		return newList;
	}

	public static Meat inputNewMeat() {
		Scanner input = new Scanner(System.in);
		System.out.print("\tEnter ID: ");
		String id = input.nextLine();
		System.out.print("\tEnter name: ");
		String name = input.nextLine();
		System.out.print("\tEnter manufacturing date (YYYY-MM-DD): ");
		String date = input.nextLine();
		System.out.print("\tEnter cost per kg (thousand VND): ");
		int cost = input.nextInt();
		System.out.print("\tEnter weight (kg): ");
		double weight = input.nextDouble();

		return new Meat(id, name, date, cost, weight);
	}

	public static Material[] addNewFlour(Material[] list) {
		CrispyFlour flour = inputNewFlour();
		int n = list.length;
		Material[] newList = new Material[n + 1];

		for (int i = 0; i < n; i++) {
			newList[i] = list[i];
		}
		newList[n] = flour;

		System.out.println("New crispy flour material: " + newList[n].getName() + " added!");
		return newList;
	}

	public static CrispyFlour inputNewFlour() {
		Scanner input = new Scanner(System.in);
		System.out.print("\tEnter ID: ");
		String id = input.nextLine();
		System.out.print("\tEnter name: ");
		String name = input.nextLine();
		System.out.print("\tEnter manufacturing date (YYYY-MM-DD): ");
		String date = input.nextLine();
		System.out.print("\tEnter cost per pack (thousand VND): ");
		int cost = input.nextInt();
		System.out.print("\tEnter quantity: ");
		int quantity = input.nextInt();

		return new CrispyFlour(id, name, date, cost, quantity);
	}

	public static void changeMaterial(Material[] list) {
		String id = inputID();
		for (Material material : list) {
			if (material.getId().equals(id)) {
				System.out.println(material);

				if (material instanceof Meat) {
					changeMeat(material);
				} else if (material instanceof CrispyFlour) {
					changeFlour(material);
				}
				break;
			}
		}
	}

	public static String inputID() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter ID of material: ");
		return input.nextLine();
	}

	public static void changeMeat(Material material) {
		Scanner input = new Scanner(System.in);
		System.out.println("""
						Choose property to change:
						1. Cost
						2. Weight
						Enter your choice:""");
		int choice = input.nextInt();
		String s = "";
		switch (choice) {
			case 1:
				System.out.print("Enter new cost per kg (thousand VND): ");
				int newCost = input.nextInt();
				material.setCost(newCost);
				s = "Cost";
				break;
			case 2:
				System.out.print("Enter new weight (kg): ");
				double newWeight = input.nextDouble();
				((Meat) material).setWeight(newWeight);
				s = "Weight";
				break;
			default:
				System.out.println("Not a choice!");
		}
		System.out.println("Meet material " + s + " changed!");
	}

	public static void changeFlour(Material material) {
		Scanner input = new Scanner(System.in);
		System.out.println("""
						Choose property to change:
						1. Cost
						2. Quantity
						Enter your choice:""");
		int choice = input.nextInt();
		String s = "";
		switch (choice) {
			case 1:
				System.out.print("Enter new cost per pack (thousand VND): ");
				int newCost = input.nextInt();
				material.setCost(newCost);
				s = "Cost";
				break;
			case 2:
				System.out.print("Enter new quantity: ");
				int newQuantity = input.nextInt();
				((CrispyFlour) material).setQuantity(newQuantity);
				s = "Quantity";
				break;
			default:
				System.out.println("Not a choice!");
		}
		System.out.println("Crispy flour material " + s + " changed!");
	}

	public static Material[] deleteMaterial(Material[] list) {
		String id = inputID();
		int n = list.length;
		Material[] newList = new Material[n - 1];
		for (int i = 0; i < n; i++) {
			if (list[i].getId().equals(id)) {
				for (int j = 0; j < i; j++) {
					newList[j] = list[j];
				}
				for (int j = i; j < n - 1; j++) {
					newList[j] = list[j + 1];
				}
				break;
			}
		}
		System.out.println("Material " + id + " deleted!");
		return newList;
	}

	public static void printMaterials(Material[] list) {
		System.out.println("Materials in the list:");
		for (Material material : list) {
			System.out.println("\t" + material);
		}
	}
}
