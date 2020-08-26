// The following application is a Project Management system.
// You can view, add and edit projects.
// You can view, add and edit persons.

// Import required packages
import java.util.*;
import java.io.*;
import java.text.*;

public class ProjectManager{
    public static void main(String args[]) {
        // Create a scanner object so the user can input answers
        Scanner input = new Scanner (System.in);

        // Create the required variables
        List <Project> projects = new ArrayList <Project>();

        // RUN A READ FROM FILE METHOD!!!
        readText(projects);

        // Run the program until the user chooses to exit
        System.out.println("\nWelcome to the Project Manager App!");
        while(true){
            // Print the menu options to the user
            System.out.println("\nPlease choose one of the following menu options to continue:");
            System.out.println("1 - View All Projects");
            System.out.println("2 - Add Project");
            System.out.println("3 - View Outstanding Projects");
            System.out.println("4 - View Overdue Projects");
            System.out.println("5 - Exit");


            // Get the option the user entered and based on the option,
            // run the applicable method
            try{
                int menuOption = input.nextInt();
                input.nextLine();

                if (menuOption == 1){
                    viewAllProjects(projects);
                }

                else if (menuOption == 2){
                    addProject(projects);
                }

                else if (menuOption == 3){
                    viewOutstanding(projects);
                }

                else if (menuOption == 4){
                    viewPastDue(projects);
                }

                else if (menuOption == 5){
                    writeText(projects);
                    break;
                }

                else{
                    System.out.println("\nMenu option not found. Please enter a correct menu option.");
                }
            }

            catch(Exception e){
                System.out.println("Not Recognized. Please enter a number for a menu option.");
                input.next();
            }
        }
    }

    // The following method lists all the projects that are currently saved.
    // The required parameter is the projects List.
    public static void viewAllProjects(List <Project> projects){
        // Create the necassary variables
        Scanner input = new Scanner (System.in);
        boolean projectNotFound = true;

        System.out.println("\nProjects\n");

        // Loop through the projects List and print the details
        for (int x = 0 ; x < projects.size() ; x++){
            System.out.println(projects.get(x));
            System.out.println("-----------------------------------------------\n");
        }

        // Request the user to input the number of the project they want to edit
        System.out.print("Enter the project number to edit: ");
        String projectNumber = input.nextLine();
        
        
        // Loop through the projects and find the project that specified
        for (int x = 0 ; x < projects.size() ; x++){
            if (projectNumber.equals(projects.get(x).getPNumber()))
            {
                while(true){
                    // Print the elements the user can edit and request the user to make an option
                    System.out.println("\nWhat would you like to edit?");
                    System.out.println("1 - Deadline");
                    System.out.println("2 - Paid To Date");
                    System.out.println("3 - Finalization");
                    System.out.println("4 - Architect");
                    System.out.println("5 - Contractor");
                    System.out.println("6 - Customer");
                    System.out.println("7 - Back to Main Menu");

                    try{
                        int menuOption = input.nextInt();
                        input.nextLine();

                        // Based on the users input, call the applicable set method
                        if (menuOption == 1){
                            System.out.print("Deadline (ddmmyyyy): ");
                            String deadline = input.nextLine();
                            projects.get(x).setDeadline(deadline);
                        }

                        else if (menuOption == 2){
                            System.out.print("Paid To Date: ");
                            String paidToDate = input.nextLine();
                            projects.get(x).setPaidToDate(Double.parseDouble(paidToDate));
                        }

                        else if (menuOption == 3){

                            System.out.println("\nThe project is currently set as " + projects.get(x).getFinalizedString());
                            System.out.print("Enter 1 to change: ");
                            String finalized = input.nextLine();
                            projects.get(x).setFinalized(finalized);

                            System.out.println("\nThe project has been changed to " + projects.get(x).getFinalizedString());
                        }

                        else if (menuOption == 4){
                            projects.get(x).setArchitect(editPersons(projects.get(x).getArchitect()));
                        }

                        else if (menuOption == 5){
                            projects.get(x).setContractor(editPersons(projects.get(x).getContractor()));
                        }

                        else if (menuOption == 6){
                            projects.get(x).setCustomer(editPersons(projects.get(x).getCustomer()));
                        }

                        else if (menuOption == 7){
                            break;
                        }

                        // If the user didn't enter an applicable option, let them know
                        else{
                            System.out.println("\nMenu option not found. Please enter a correct menu option.");
                        }
                    }

                    catch(Exception e){
                        System.out.println("\nNot Recognized. Please enter a number for a menu option.");
                        input.next();
                    }
                }

                // Break the loop
                projectNotFound = false;
                break;
            }
        }

        // If the project wasn't found, let the user know
        if (projectNotFound){
            System.out.println("\nProject not found. Returning to Main Menu.");
        }
    }

    // The following method adds a new project to the app
    // The required paramaters are the projects and persons Lists
    public static void addProject(List <Project> projects){
        // Create the required variables
        Scanner input = new Scanner (System.in);
        
        // Request the user to enter the details of the new project
        System.out.println("Please enter the details of the new project.");
        System.out.print("Project Number: ");
        String pNumber = input.nextLine();
        System.out.print("Project Name: ");
        String pName = input.nextLine();
        System.out.print("Building Type: ");
        String bType = input.nextLine();
        System.out.print("Project Address: ");
        String pAddress = input.nextLine();
        System.out.print("Erf Number: ");
        String erfNumber = input.nextLine();
        System.out.print("Total Charge: ");
        String totalCharge = input.nextLine();
        System.out.print("Paid To Date: ");
        String paidToDate = input.nextLine();
        System.out.print("Deadline (ddmmyyyy): ");
        String deadline = input.nextLine();

        if (totalCharge.equals("")){
            totalCharge = "0.0";
        }

        if (paidToDate.equals("")){
            paidToDate = "0.0";
        }

        // Create new KeyPerson objects by calling the addPersons method
        KeyPerson architect = addPersons("Architect");
        KeyPerson contractor = addPersons("Contractor");
        KeyPerson customer = addPersons("Customer");

        // It no project name has been entered, use the building type
        // and surname of the customer as the project name
        if(pName.equals("")){
            pName = bType + " " + customer.getSurname();
        }
        
        // Create a new Project object and add the information the user provided to the projects list
        Project newProject = new Project(pNumber, pName, bType, pAddress, erfNumber, Double.parseDouble(totalCharge), Double.parseDouble(paidToDate), deadline, false, architect, contractor, customer);
        projects.add(newProject);

        System.out.println("\nNew project has been added.\nReturning to Main Menu.");
    }

    // The follwoing method edits the persons
    // The required parameters are the persons List
    public static KeyPerson editPersons(KeyPerson person){
        // Create the required variables
        Scanner input = new Scanner (System.in);

        while (true){
            System.out.println("What would you like to edit?");
            System.out.println("1 - Contact Details");
            
            try{
                int menuOption = input.nextInt();
                input.nextLine();
    
                // Request the user to enter the new details for the person
                if (menuOption == 1){
                    System.out.print("Telephone: ");
                    String telephone = input.nextLine();
                    System.out.print("Email: ");
                    String email = input.nextLine();
    
                    // Set the new elements
                    person.setTelephone(telephone);
                    person.setEmail(email);
    
                    System.out.println("\nContact details updated\n");
    
                    // Return the person object
                    return person;
                }
    
                // If the users choice is not applicable let them know
                else {
                    System.out.println("\nMenu option not found. Please enter a correct menu option.");
                }
            }

            catch(Exception e){
                System.out.println("\nNot Recognized. Please enter a number for a menu option.");
                input.next();
            }
            
        }
    }

    // The following method adds a new person to the List
    // The required parameters is the persons List and the type of additions (from menu or from project)
    public static KeyPerson addPersons(String jobDescription){
        // Create the required variables
        Scanner input = new Scanner (System.in);

        // Request the user to input the details of the new person
        System.out.println("\nPlease enter the details of the " + jobDescription);
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Surname: ");
        String surname = input.nextLine();
        System.out.print("Telephone: ");
        String telephone = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Address: ");
        String address = input.nextLine();
        
        // Create a new KeyPerson object and add the object to the persons list
        KeyPerson person = new KeyPerson(jobDescription, name, surname, telephone, email, address);
        
        return person;
    }

    // The following method reads the details of all the projects from
    // a text file. The projects object is filled with the information.
    public static void readText (List projects){
        
        // Create the required variables
        String line;
        List <String> projectInfo = new ArrayList <String>();

        KeyPerson person1;
        KeyPerson person2;
        KeyPerson person3;
        Project project;

        // Create the File and Scanner objects to read from the text file
        try{
            File text = new File("projects.txt");
            Scanner scan = new Scanner (text);

            // While there is information left in the text file, keep on looping
            while (scan.hasNext()){

                line = scan.nextLine();

                // The projects are split by a line of dashes. Once the dashes
                // are read, we know we have reached the end of the project.
                // Now read the information of the array into each KeyPerson object
                // and Project object. Then add it to the projects array.
                if (line.equals("-----------------------------------------------"))
                {
                    person1 = new KeyPerson (projectInfo.get(9), projectInfo.get(10), projectInfo.get(11), projectInfo.get(12), projectInfo.get(13), projectInfo.get(14));
                    person2 = new KeyPerson (projectInfo.get(15), projectInfo.get(16), projectInfo.get(17), projectInfo.get(18), projectInfo.get(19), projectInfo.get(20));
                    person3 = new KeyPerson (projectInfo.get(21), projectInfo.get(22), projectInfo.get(23), projectInfo.get(24), projectInfo.get(25), projectInfo.get(26));
                    project = new Project (projectInfo.get(0), projectInfo.get(1), projectInfo.get(2), projectInfo.get(3), projectInfo.get(4), Double.parseDouble(projectInfo.get(5)), Double.parseDouble(projectInfo.get(6)), projectInfo.get(7), Boolean.parseBoolean(projectInfo.get(8)), person1, person2, person3);
                    projects.add(project);
                    projectInfo.clear();
                }

                else{
                    projectInfo.add(line);
                }
            }
        }

        catch(Exception e){
            System.out.println("Error");
        }
    }

    // The following method lists all the projects that are currently outstanding.
    // The required parameter is the projects List.
    public static void viewOutstanding(List <Project> projects){
        boolean noProjectsFound = true;
        
        System.out.println("\nProjects Not Yet Finalized\n");

        // Loop through the projects List and print the details if the
        // finalized attribute is false
        for (int x = 0 ; x < projects.size() ; x++){
            if(projects.get(x).getFinalized() == false){
                System.out.println(projects.get(x));
                System.out.println("-----------------------------------------------\n");
                noProjectsFound = false;
            }
        }

        if(noProjectsFound){
            System.out.println("All Projects Finalized.");
        }

    }

    // The following method lists all the projects that are currently overdue.
    // The required parameter is the projects List.
    public static void viewPastDue(List <Project> projects){
        
        // Create the required variables to be used
        boolean noProjectsFound = true;
        String deadline;
        Date dueDate;
        Date today = new Date();
        
        System.out.println("\nProjects Past Due\n");

        // Loop through the projects List and print the details if the
        // deadline of the project is before todays date.
        for (int x = 0 ; x < projects.size() ; x++){
            try{            
                deadline = projects.get(x).getDeadline();
                dueDate = new SimpleDateFormat("ddmmyyyy").parse(deadline);

                if(dueDate.before(today)){
                    System.out.println(projects.get(x));
                    System.out.println("-----------------------------------------------\n");
                    noProjectsFound = false;
                }
            }

            catch(ParseException e){
                e.printStackTrace();
            }
        }

        if(noProjectsFound){
            System.out.println("No projects are overdue.\nReturning to Main Menu.");
        }
    }

    // The following method writes the details of all the projects to
    // a text file. The projects object is information is used to write to file.
    public static void writeText (List <Project> projects){
        
        // Create the required variables
        String lines;

        // Create the Formatter object to write the information to
        // file. Use the write() method to return the string in the
        // correct format.
        try{
            Formatter f = new Formatter("projects.txt");
            
            for (int x = 0 ; x < projects.size() ; x++){
                f.format("%s", projects.get(x).write());
                
                if(projects.size()-1 == x){
                    f.format("%s", "\n-----------------------------------------------");
                }

                else{
                    f.format("%s", "\n-----------------------------------------------\n");
                }

            }
            f.close();
        }

        catch(Exception e){
            System.out.println("Error");
        }
    }
}