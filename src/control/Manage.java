package control;

import model.*;

import java.util.Arrays;

public class Manage {
	public static void main(String[] args) {
		Material[] list = new Material[10];
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

		System.out.println("\nTotal money for materials: " + getMoneySum(list) + "VND");
		System.out.println("\nList of materials sorted descending based on cost:");
		printSortedMaterials(list);
	}

	private static double getMoneySum(Material[] list) {
		double sum = 0;
		for (Material material : list) {
			sum += material.getRealMoney();
		}
		return Math.round(sum * 100000.0) / 100.0;
	}

	private static void printSortedMaterials(Material[] list) {
		Arrays.sort(list, new MaterialComparator());
		for (Material item : list) {
			System.out.println("\t" + item);
		}
	}
}
