package control;

import model.*;

import java.util.Arrays;
import java.util.Scanner;

public class Manage {
	public static void main(String[] args) {
		Material[] list = new Material[50];
		list[0] = new CrispyFlour("001", "Aji-Quick", "2024-03-04", 123, 3);
		list[1] = new CrispyFlour("002", "Meizan", "2023-04-05", 45, 4);
		list[2] = new CrispyFlour("003", "Tai Ky", "2024-05-06", 110, 5);
		list[3] = new CrispyFlour("004", "Vinh Thuan", "2022-01-03", 30, 6);
		list[4] = new CrispyFlour("005", "Beksul", "2023-12-04", 80, 7);
		list[5] = new Meat("006", "Chicken", "2024-10-15", 60, 2.5);
		list[6] = new Meat("007", "Pork", "2024-10-14", 100, 10);
		list[7] = new Meat("008", "Beef", "2024-10-08", 90, 8);
		list[8] = new Meat("009", "Fish", "2024-10-09", 120, 5);
		list[9] = new Meat("010", "Kobe beef", "2024-10-17", 300, 1);

		System.out.println("\nTotal money for materials: " + getMoneySum(list) + " VND");
		System.out.println("\nList of materials sorted descending based on cost:");
		printSortedMaterials(list);
		System.out.println("\nTotal discounted money: " + getTotalDiscount(list) + " VND");

		Scanner input = new Scanner(System.in);
		int choice;
		while(true) {
			System.out.println("""
				\nMenu:
					1. Add new material
					2. Change a material
					3. Delete a material
					4. Show all materials
					0. Exit
				Enter your choice:""");
			choice = input.nextInt();
			switch(choice) {
				case 1:
					addNewMaterial(list);
					break;
				case 2:
					changeMaterial(list);
					break;
				case 3:
					deleteMaterial(list);
					break;
				case 4:
					printMaterials(list);
					break;
				case 0:
					System.exit(0);
				default:
					System.out.println("Not a choice!");
			}
		}
	}

	private static double getMoneySum(Material[] list) {
		int n = getNumberOfElements(list);
		double sum = 0;
		for (int i = 0; i < n; i++) {
			sum += list[i].getRealMoney();
		}
		return Math.round(sum * 100000.0) / 100.0;
	}

	private static void printSortedMaterials(Material[] list) {
		int n = getNumberOfElements(list);
		Material[] no_null_list = new Material[n];
		System.arraycopy(list, 0, no_null_list, 0, n);

		Arrays.sort(no_null_list, new MaterialComparator());
		for (Material item : no_null_list) {
			System.out.println("\t" + item);
		}
	}

	private static double getTotalDiscount(Material[] list) {
		int n = getNumberOfElements(list);
		double sum = 0;
		for (int i = 0; i < n; i++) {
			sum += list[i].getAmount() - list[i].getRealMoney();
		}
		return Math.round(sum * 100000.0) / 100.0;
	}

	private static int getNumberOfElements(Material[] list) {
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

	private static void addNewMaterial(Material[] list) {
		int n = getNumberOfElements(list);
		Scanner input = new Scanner(System.in);
		System.out.print("Enter kind of material (Meat/Crispy Flour): ");
		String kind = input.nextLine();

		if (kind.equals("Meat")) {
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
			System.out.println("New meat material added!");

		} else if (kind.equals("Crispy Flour")) {
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
			System.out.println("New crispy flour material added!");
		}
	}

	private static void changeMaterial(Material[] list) {
		int n = getNumberOfElements(list);
		Scanner input = new Scanner(System.in);
		System.out.print("Enter id of material you want to change: ");
		String id = input.nextLine();

		for (int i = 0; i < n; i++) {
			if (list[i].getId().equals(id)) {
				System.out.println(list[i]);

				if (list[i] instanceof Meat) {
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
							list[i].setCost(newCost);
							s = "Cost";
							break;
						case 2:
							System.out.print("Enter new weight (kg): ");
							double newWeight = input.nextDouble();
							((Meat) list[i]).setWeight(newWeight);
							s = "Weight";
							break;
						default:
							System.out.println("Not a choice!");
					}
					System.out.println("Meet material " + s + " changed!");

				} else if (list[i] instanceof CrispyFlour) {
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
							list[i].setCost(newCost);
							s = "Cost";
							break;
						case 2:
							System.out.print("Enter new quantity: ");
							int newQuantity = input.nextInt();
							((CrispyFlour) list[i]).setQuantity(newQuantity);
							s = "Quantity";
							break;
						default:
							System.out.println("Not a choice!");
					}
					System.out.println("Crispy flour material " + s + " changed!");
				}
				break;
			}
		}
	}

	private static void deleteMaterial(Material[] list) {
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

	private static void printMaterials(Material[] list) {
		int n = getNumberOfElements(list);
		System.out.println("Materials in the list:");
		for (int i = 0; i < n; i++) {
			System.out.println("\t" + list[i]);
		}
	}
}
