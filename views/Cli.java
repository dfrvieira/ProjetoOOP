package views;

import controllers.Company;

import java.util.Scanner;

public class Cli {
    public void start() {
        Company company = null;
        Scanner scanner = new Scanner(System.in);
        String line;
        boolean endLoop = false;
        while (!endLoop) {
            line = scanner.nextLine();
            String[] command = line.split(" ");
            switch (command[0]) {
                case "RF":
                    String category = command[1];
                    String permission = command[2];
                    String name = nameConcatenation(3,command);
                    if(!company.hasCategory(category)){
                        System.out.println("Categoria inexistente.");
                    }else if(company.validPermission(permission,category)){
                        System.out.println("Permissão inexistente.");
                    }else if(company.hasProfessional(name,category)){
                    System.out.println("Funcionário existente.");
                }else{
                        company.registerEmployee(category,permission,name);
                        System.out.println("Funcionário registado com o identificador " + company.getEmployeeID(company.getProfessional(name,category)));
                }
                    break;
                case "RC":
                    //Nome contem espaços e deve ser unico no sistema
                    // IDCliente numero unico atribuido pelo sistema (starts @ 1)
                    String  employeeID = command[1];
                    String nameClient = "";
                    for(int i=2 ; i<line.length();i++){
                       nameClient += command[i];
                    }
                    if(company.hasClientName(nameClient)){
                        System.out.println("Cliente existente.");
                    }else{
                        company.registerClient(nameClient);
                        System.out.println("Cliente registado com o identificador " + company.getClientID(nameClient));
                    }
                    break;
                case "RI":
                    String clientID = command[1];
                    String itemName = command[2];
                    Boolean validInput = true;
                    if(company.hasClient(clientID)){
                        String[] itemPermissions = line.split(",");
                        if (itemPermissions.length == 0){
                            int itemID = company.registerItem(itemName,clientID,itemPermissions);
                            System.out.printf("Item registado para o client %d com o identificador %d" , clientID,itemID);
                        }else{
                            for (String itemPermission : itemPermissions){
                                if (!company.hasPermission(itemPermission)){
                                    validInput = false;
                                }
                            }
                            if(!validInput) {
                                System.out.println("Permissão inválida.");
                            }else{
                                    int itemID = company.registerItem(itemName,clientID,itemPermissions);
                                    System.out.printf("Item registado para o client %d com o identificador %d" , clientID,itemID);
                                }
                        }
                    }else{
                        System.out.println("Cliente inexistente.");
                    }
                    break;
                case "RL":
                    String placeName = command[1];
                    if(company.hasPlaceName(placeName)){
                        System.out.println("Local existente.");
                    }else{
                       int placeID =  company.registerPlace(placeName);
                        System.out.printf("Local registado com o identificador %d",placeID);
                    }
                    break;
                case "RD":
                    //Register a new item deposit for a client
                    //Takes in a list of items and quantities
                    //RD_IDClient_IDPlace_|
                    //IDEmployee_ID_Funcionario_...|
                    //IDItem_Amoutn_|
                    //IDItem_Amount_|
                    //Fails if
                    break;
                case "RE":
                    break;
                case "CC":
                    break;
                case "CI":
                    break;
                case "CE":
                    break;
                case "CF":
                    break;
                case "":
                   endLoop = true;
                   break;
                default:
                    System.out.println("Instrução inválida.");
            }
        }
        scanner.close();
    }

    String nameConcatenation(int nameStart, String[] commands){
        String fullName = "";
        for(int i = nameStart;i < commands.length; i++) {
            fullName += commands[i] + " ";
        }
        return  fullName;
    }
}