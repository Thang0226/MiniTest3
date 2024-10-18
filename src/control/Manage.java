package control;

import model.*;

import java.util.Arrays;
import java.util.Scanner;

public class Manage {
	public static double getMoneySum(Material[] list) {
		int n = getNumberOfElements(list);
		double sum = 0;
		for (int i = 0; i < n; i++) {
			sum += list[i].getRealMoney();
		}
		return Math.round(sum * 100000.0) / 100.0;
	}

	public static void printSortedMaterials(Material[] list) {
		int n = getNumberOfElements(list);
		Material[] no_null_list = new Material[n];
		System.arraycopy(list, 0, no_null_list, 0, n);

		Arrays.sort(no_null_list, new MaterialComparator());
		for (Material item : no_null_list) {
			System.out.println("\t" + item);
		}
	}

	public static double getTotalDiscount(Material[] list) {
		int n = getNumberOfElements(list);
		double sum = 0;
		for (int i = 0; i < n; i++) {
			sum += list[i].getAmount() - list[i].getRealMoney();
		}
		return Math.round(sum * 100000.0) / 100.0;
	}

	public static int getNumberOfElements(Material[] list) {
		int n = 0;
		for (Material item : list) {
			if (item != null) {
				n++;
			} else {
				break;
			}
		}
		return n;
	}

	public static void addNewMaterial(Material[] list) {
		int n = getNumberOfElements(list);
		Scanner input = new Scanner(System.in);
		System.out.print("Enter kind of material (Meat/Crispy Flour): ");
		String kind = input.nextLine();

		if (kind.equals("Meat")) {
			addNewMeatMaterial(list, n);
		} else if (kind.equals("Crispy Flour")) {
			addNewCrispyFlourMaterial(list, n);
		}
	}

	public static void addNewMeatMaterial(Material[] list, int n) {
		Scanner input = new Scanner(System.in);
		System.out.print("\tEnter id: ");
		String id = input.nextLine();
		System.out.print("\tEnter name: ");
		String name = input.nextLine();
		System.out.print("\tEnter manufacturing date (YYYY-MM-DD): ");
		String date = input.next();
		System.out.print("\tEnter cost per kg (thousand VND): ");
		int cost = input.nextInt();
		System.out.print("\tEnter weight (kg): ");
		double weight = input.nextDouble();

		list[n] = new Meat(id, name, date, cost, weight);
		System.out.println("New meat material: " + list[n].getName() + " added!");
	}

	public static void addNewCrispyFlourMaterial(Material[] list, int n) {
		Scanner input = new Scanner(System.in);
		System.out.print("\tEnter id: ");
		String id = input.nextLine();
		System.out.print("\tEnter name: ");
		String name = input.nextLine();
		System.out.print("\tEnter manufacturing date (YYYY-MM-DD): ");
		String date = input.next();
		System.out.print("\tEnter cost per pack (thousand VND): ");
		int cost = input.nextInt();
		System.out.print("\tEnter quantity: ");
		int quantity = input.nextInt();

		list[n] = new CrispyFlour(id, name, date, cost, quantity);
		System.out.println("New crispy flour material: " + list[n].getName() + " added!");
	}

	public static void changeMaterial(Material[] list) {
		int n = getNumberOfElements(list);
		Scanner input = new Scanner(System.in);
		System.out.print("Enter id of material you want to change: ");
		String id = input.nextLine();

		for (int i = 0; i < n; i++) {
			if (list[i].getId().equals(id)) {
				System.out.println(list[i]);

				if (list[i] instanceof Meat) {
					changeMeatMaterial(list[i]);
				} else if (list[i] instanceof CrispyFlour) {
					changeCrispyFlourMaterial(list[i]);
				}
				break;
			}
		}
	}

	public static void changeMeatMaterial(Material material) {
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

	public static void changeCrispyFlourMaterial(Material material) {
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

	public static void deleteMaterial(Material[] list) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter id of material you want to delete: ");
		String id = input.nextLine();

		int n = getNumberOfElements(list);
		for (int i = 0; i < n; i++) {
			if (list[i].getId().equals(id)) {
				for (int j = i; j < n; j++) {
					list[j] = list[j + 1];
				}
				break;
			}
		}
		System.out.println("Material " + id + " deleted!");
	}

	public static void printMaterials(Material[] list) {
		int n = getNumberOfElements(list);
		System.out.println("Materials in the list:");
		for (int i = 0; i < n; i++) {
			System.out.println("\t" + list[i]);
		}
	}
}
