package mypackage;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeManager {
    Employee[] emp = new Employee[1000];
    int count = 0;
    Scanner input = new Scanner(System.in);
    public Pattern patternString = Pattern.compile("^[a-zA-Z]+$");
    public Pattern patternNumber = Pattern.compile("[+-]?([0-9]*[.])?[0-9]");
    private boolean bool;

    public void meNu() {
        System.out.println("Welcome to Employee Salaries Management Software \nMain Menu");
        System.out.println("1) Input a number of the employee into a list\n" +
                "2) display all the employee salaries from a list\n" +
                "3) Calculate the total of the employee salaries\n" +
                "4) Calculate the average salaries\n" +
                "5) Find the highest salaries and lowest salaries.\n" +
                "6) Search a specific employee salaries\n" +
                "7) provide an option for a user to arrange the employee salaries\n" +
                "8) Update an employee salary with a new salary inputted by a user\n" +
                "9) Delete an employee salary from the list\n" +
                "10) count a number of the employees that has salary less than 500.00\n" +
                "11) Exit Software");
        opTion();
    }

    private void opTion() {
        int menu;
        menu = input.nextInt();
        switch (menu) {
            case 1:
                addEmployee();
                break;
            case 2:
                showAllEmployee();
                break;
            case 3:
                showTotalSalary();
                break;
            case 4:
                avgSalary();
                break;
            case 5:
                Highest_Lowest_Salary();
                break;
            case 6:
                searchSalary();
                break;
            case 7:
                sortEmployee();
                break;
            case 8:
                updateEmployee();
                break;
            case 9:
                deleteEmployee();
                break;
            case 10:
                countEmployee();
                break;
            case 11:
                System.exit(0);
                break;

        }
    }

    private void stringMessage() {
        System.out.println("Input only String");
    }

    private void addEmployee() {
        String fname, lname, name, sal, tempSal;
        double salary;
        char choice;


        do {
            System.out.print("Input Employee first Name: ");
            fname = input.next();
            Matcher matcherFname = patternString.matcher(fname);

            try {
                bool = false;
                if (!matcherFname.find()) {
                    throw new StringMessage();
                }
            } catch (StringMessage e) {
                System.out.println("Values you input is: " + fname);
                bool = true;
            }

        } while (bool);


        do {
            System.out.print("Input employee last name: ");
            lname = input.next();
            Matcher matcherLname = patternString.matcher(lname);

            try {
                bool = false;
                if (!matcherLname.find()) {
                    throw new StringMessage();
                }
            } catch (StringMessage e) {
                System.out.println("The Value You Input is: " + lname);
                bool = true;
            }
        } while (bool);


        do {
            System.out.print("Input employee salary: $");
            sal = input.next();
            Matcher matcherNumber = patternNumber.matcher(sal);
            try {
                bool = false;
                if (!matcherNumber.find()) {
                    throw new DoubleMessage();
                }
            } catch (DoubleMessage e) {
                System.out.println("The Value You Input is: " + sal);
                bool = true;
            } finally {
                tempSal = sal;
            }
        } while (bool);
        salary = Double.parseDouble(tempSal);

        name = fname + " " + lname;
        System.out.print("Do you want to Input another Salary (Y/N): ");
        choice = input.next().charAt(0);
        emp[count] = new Employee(name, salary);
        count++;
        while (choice != 'y' && choice != 'Y' && choice != 'n' && choice != 'N') {
            System.out.println("Input only Y and N");
            choice = input.next().charAt(0);
        }
        if (choice == 'Y' || choice == 'y') {
            addEmployee();
        } else {
            meNu();
        }
    }

    private void showAllEmployee() {
        if (count == 0) {
            System.out.println("\nThere is no employee in the list");
        } else {
            System.out.println("\nTotal employees: ");
            for (int k = 0; k < count; k++) {
                emp[k].display();
            }
            meNu();
        }
    }

    private void showTotalSalary() {
        double totalSal = 0;
        for (int j = 0; j < count; j++) {
            totalSal += emp[j].getSalary();
        }
        System.out.println("Total salary of all employees is: $" + totalSal + "\n\n");
        meNu();
    }

    private void avgSalary() {
        int empCount = 0;
        double totalSal = 0, avgSal;

        for (int j = 0; j < count; j++) {
            totalSal += emp[j].getSalary();
            empCount++;
        }
        avgSal = totalSal / empCount;
        System.out.println("The average salary of all employees is: $" + avgSal + "\n\n");
        meNu();
    }

    private void Highest_Lowest_Salary() {
        double highest = 0;
        double lowest = emp[0].getSalary();
        int tempElement_High = 0, tempElement_Low = 0;
        for (int j = 0; j < count; j++) {
            if (highest <= emp[j].getSalary()) {
                highest = emp[j].getSalary();
                tempElement_High = j;
            }
            if (lowest > emp[j].getSalary()) {
                lowest = emp[j].getSalary();
                tempElement_Low = j;
            }
        }
        System.out.println("The highest salary is: ");
        emp[tempElement_High].display();
        System.out.println("The lowest salary is: ");
        emp[tempElement_Low].display();
        meNu();
    }

    private void searchSalary() {
        double searchSal;

        String sSal, tempSSal;
        do {
            System.out.println("Enter employee salary: $");
            sSal = input.next();
            Matcher matcherSSal = patternNumber.matcher(sSal);
            try {
                bool = false;
                if (!matcherSSal.find()) {
                    throw new DoubleMessage();
                }
            } catch (DoubleMessage e) {
                System.out.println("The Value you input is: " + sSal);
                bool = true;
            } finally {
                tempSSal = sSal;
            }
        } while (bool);
        searchSal = Double.parseDouble(tempSSal);

        for (int j = 0; j < count; j++) {
            if (searchSal == emp[j].getSalary()) {
                System.out.print("Employee that has salary $" + searchSal + " is ");
                System.out.println("\"" + emp[j].getName() + "\"");
                meNu();
            } else {
                System.out.println("Invalid! input other salary");
                searchSalary();
                meNu();
            }
        }
    }

    private void sortEmployee() {
        String option;

        System.out.println(" 1.in ascending order (from lowest to highest salary)");
        System.out.println(" 2.in descending order (from highest to lowest salary)");
        System.out.println(" 0.Go back to main menu");
        System.out.print("Enter Option:");
        option = input.next();
        while (!option.equals("0") && !option.equals("1") && !option.equals("2")) {
            System.out.print("Enter From 0-2 only:");
            option = input.next();
        }

        switch (option) {
            case "1" -> {
                double[] arr = new double[count];
                for (int k = 0; k < count; k++) {
                    arr[k] = emp[k].getSalary();
                }
                Arrays.sort(arr);
                for (int k = 0; k < count; k++) {
                    for (int j = 0; j < count; j++) {
                        if (arr[k] == emp[j].getSalary()) {
                            emp[j].display();
                        }
                    }
                }
                meNu();
            }
            case "2" -> {
                Double[] arra = new Double[count];
                for (int k = 0; k < count; k++) {
                    arra[k] = emp[k].getSalary();
                }
                Arrays.sort(arra, Collections.reverseOrder());
                for (int k = 0; k < count; k++) {
                    for (int j = 0; j < count; j++) {
                        if (arra[k] == emp[j].getSalary()) {
                            emp[j].display();
                        }
                    }
                }
                meNu();
            }
            case "0" -> meNu();
        }
    }

    private void updateEmployee() {
        int updateEmp = 0;
        String upEmp, tempUpEmp;
        double sal = 0;
        System.out.println("\nList all employees: \n");
        for (int j = 0; j < count; j++) {
            System.out.println((j + 1) + ": " + emp[j].getName() + "\n\n");
        }
        do {
            System.out.print("Select Employee: ");
            upEmp = input.next();
            Matcher matcherUpEmp = patternNumber.matcher(upEmp);
            try {
                bool = false;
                if (!matcherUpEmp.find()) {
                    throw new DoubleMessage();
                }
            } catch (DoubleMessage e) {
                System.out.println("The Value You Input is: " + sal);
                bool = true;
            } finally {
                tempUpEmp = upEmp;
            }
        } while (bool);
        updateEmp = Integer.parseInt(tempUpEmp);
        System.out.print("Change " + emp[updateEmp - 1].getName() + "'s Salary to: $");
        sal = input.nextDouble();
        emp[updateEmp - 1].setSalary(sal);
        meNu();
    }

    private void deleteEmployee() {
        int deleteEmployee = 0;
        System.out.println("List all employees: ");
        for (int j = 0; j < count; j++) {
            System.out.println((j + 1) + emp[j].getName());
        }
        System.out.print("Enter an employee you want to delete: ");
        deleteEmployee = input.nextInt();
        for (int j = deleteEmployee - 1; j < count - 1; j++) {
            emp[j] = emp[j + 1];
        }
        count--;
        meNu();
    }

    private void countEmployee() {
        int empCount = 0;
        for (int j = 0; j < count; j++) {
            if (emp[j].getSalary() < 500) {
                empCount++;
            }
        }
        System.out.println("Total employees has salary less than $500 is: " + empCount);
        meNu();
    }
}
