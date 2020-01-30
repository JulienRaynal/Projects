import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        char[][] table = new char[26][26];

        int userChoice = getUserChoice();
        while (true) {
            if (userChoice == 0) {
                break;
            } else if (userChoice == 1) {
                creerTableau(table);
                afficherTableau(table);
                codeMsg(table);
            } else if (userChoice == 2) {
                creerTableau(table);
                afficherTableau(table);
                decMsg(table);
            } else {
                System.out.println("Entree inconnue, veuillez entrer une autre valeur");
            }
            userChoice = getUserChoice();
        }

        // Au revoir !
    }

    private static int getUserChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Voulez vous coder un message (1)");
        System.out.println("Voulez vous decoder un message (2)");
        System.out.println("Voulez vous sortir du programme (0)");

        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            return -1;
        }
    }

    public static void creerTableau(char[][] P_table) {
        Scanner sc = new Scanner(System.in);
        int L_indiceLigne = 0;
        int L_indiceColonne = 0;
        System.out.print("Entrez votre graine :" + "\n");
        char L_graine = sc.next().charAt(0);
        char L_graine2 = L_graine;
        char L_maj = ' ';

        for (L_indiceLigne = 0; L_indiceLigne < 26; L_indiceLigne++) {
            for (L_indiceColonne = 0; L_indiceColonne < 26; L_indiceColonne++) {
                if (L_graine >= 'a' && L_graine <= 'z') {
                    L_maj = Character.toUpperCase(L_graine);
                    P_table[L_indiceLigne][L_indiceColonne] = L_maj;

                } else {
                    L_graine = 'a';
                    L_maj = Character.toUpperCase(L_graine);
                    P_table[L_indiceLigne][L_indiceColonne] = L_maj;
                }
                L_graine++;
            }
            if (L_graine2 >= 'a' && L_graine2 <= 'z') {
                L_graine2++;
            } else {
                L_graine2 = 'a';
                L_graine2++;
            }
            L_graine = L_graine2;
        }
    }

    public static void afficherTableau(char[][] P_table) {
        int L_indiceLigne = 0;
        int L_indiceColonne = 0;
        System.out.print("votre table est :" + "\n");
        for (L_indiceLigne = 0; L_indiceLigne < 26; L_indiceLigne++) {
            for (L_indiceColonne = 0; L_indiceColonne < 26; L_indiceColonne++) {
                System.out.print(P_table[L_indiceLigne][L_indiceColonne]);
            }
            System.out.printf("\n");
        }
    }

    public static void codeMsg(char[][] P_table) {
        int L_indice = 0;
        int L_coordx = 0;
        int L_coordy = 0;
        int L_repeat = 0;
        String L_code = " ";

        Scanner sc = new Scanner(System.in);
        System.out.print("Entrez votre phrase a crypte :" + "\n");
        String L_msg = sc.nextLine().toUpperCase();

        if (L_msg.length() == 0){
            do{
                System.out.print("Entrez une phrase comportant au moins un caractere :" + "\n");
                L_msg = sc.nextLine().toUpperCase();
            }while (L_msg.length() == 0);
        }
        
        System.out.print("Entrez votre cle de cryptage :" + "\n");
        String L_cle = sc.nextLine().toUpperCase();
        L_cle = L_cle.replaceAll("[^a-zA-Z0-9\\s+]", "");
        L_cle = L_cle.replaceAll("\\s+","");
        
        if (L_cle.length() == 0){
            do{
                System.out.print("Entrez une cle de cryptage comportant au moins un caractere :" + "\n");
                L_cle = sc.nextLine().toUpperCase();
                L_cle = L_cle.replaceAll("[^a-zA-Z0-9\\s+]", "");
                L_cle = L_cle.replaceAll("\\s+","");
            }while (L_cle.length() == 0);
        }
        
        System.out.print("Votre message est : " + L_msg + "\n");
        System.out.print("Votre cle est : " + L_cle + "\n");

        char[] L_cleArray = new char[L_cle.length()];
        for (int i = 0; i < L_cle.length(); i++) {
            L_cleArray[i] = L_cle.charAt(i);
        }

        for (char m : L_msg.toCharArray()) {

            if (m >= 'A' && m <= 'Z') {
                while (P_table[0][L_indice] != m) {
                    L_indice++;
                }
                L_coordy = L_indice;
                L_indice = 0;

                if (L_repeat == L_cle.length()) {
                    L_repeat = 0;
                }
                while (P_table[L_indice][0] != L_cleArray[L_repeat]) {
                    L_indice++;
                }
                L_coordx = L_indice;
                L_indice = 0;
                L_code = L_code + P_table[L_coordx][L_coordy];
                L_repeat++;
            } else {
                L_code = L_code + ' ';
            }
        }
        System.out.print("Votre message crypte est :" + L_code + "\n");
    }

    public static void decMsg(char[][] P_table) {
        int L_indice = 0;
        int L_coordx = 0;
        int L_coordy = 0;
        int L_repeat = 0;
        String L_code = " ";

        Scanner sc = new Scanner(System.in);

        System.out.print("Entrez votre phrase a decrypte :" + "\n");
        String L_msg = sc.nextLine().toUpperCase();

        if (L_msg.length() == 0){
            do{
                System.out.print("Entrez une phrase comportant au moins un caractere :" + "\n");
                L_msg = sc.nextLine().toUpperCase();
            }while (L_msg.length() == 0);
        }

        System.out.print("Entrez votre cle de cryptage :" + "\n");
        String L_cle = sc.nextLine().toUpperCase();
        L_cle = L_cle.replaceAll("\\s+","");
        L_cle = L_cle.replaceAll("[^a-zA-Z0-9\\s+]", "");

        if (L_cle.length() == 0){
            do{
                System.out.print("Entrez une cle de cryptage comportant au moins un caractere :" + "\n");
                L_cle = sc.nextLine().toUpperCase();
                L_cle = L_cle.replaceAll("[^a-zA-Z0-9\\s+]", "");
                L_cle = L_cle.replaceAll("\\s+","");
            }while (L_cle.length() == 0);
        }

        System.out.print("Votre message est : " + L_msg + "\n");
        System.out.print("Votre cle est : " + L_cle + "\n");

        char[] L_clearray = new char[L_cle.length()];
        for (int i = 0; i < L_cle.length(); i++) {
            L_clearray[i] = L_cle.charAt(i);
        }

        for (char m : L_msg.toCharArray()) {

            if (m >= 'A' && m <= 'Z') {

                if (L_repeat == L_cle.length()) {
                    L_repeat = 0;
                }
                while (P_table[L_indice][0] != L_clearray[L_repeat]) {
                    L_indice++;
                }
                L_coordx = L_indice;
                L_indice = 0;
                L_repeat++;

                while (P_table[L_coordx][L_indice] != m) {
                    L_indice++;
                }
                L_coordy = L_indice;
                L_indice = 0;
                L_code = L_code + P_table[0][L_coordy];

            } else {
                L_code = L_code + ' ';
            }
        }
        System.out.print("Votre message decrypte est :" + L_code + "\n");
    }
}