package com.mycompany.cbc;

import java.util.Scanner;

public class Chiffrement {
    static byte[] vectInit ;

    public static String Chiffrer(String message, String cle){
        byte[] messageByte = stringToByteArray(message);
        byte[] cleByte = stringToByteArray(cle);
        byte[] messageChiffre = new byte[messageByte.length];
        byte[] vecteur = vectInit;
        for(int i = 0; i < messageByte.length; i++){
            messageChiffre[i] = (byte) (messageByte[i] ^ vecteur[i % vecteur.length] ^ cleByte[i % cleByte.length]);
            vecteur = stringToByteArray(byteArrayToString(vecteur) + messageChiffre[i]);
        }
        return byteArrayToString(messageChiffre);
    }

    public static String Dechiffrer(String message, String cle){
        byte[] messageByte = stringToByteArray(message);
        byte[] cleByte = stringToByteArray(cle);
        byte[] messageDechiffre = new byte[messageByte.length];
        byte[] vecteur = vectInit;
        for(int i = 0; i < messageByte.length; i++){
            messageDechiffre[i] = (byte) (messageByte[i] ^ vecteur[i % vecteur.length] ^ cleByte[i % cleByte.length]);
            vecteur = stringToByteArray(byteArrayToString(vecteur) + messageByte[i]);
        }
        return byteArrayToString(messageDechiffre);
    }




    private static String byteArrayToString(byte[] messageChiffre) {
        String result = "";
        for(int i = 0; i < messageChiffre.length; i++){
            result += (char) messageChiffre[i];
        }
        return result;
    }


    //string to byte array
    public static byte[] stringToByteArray(String s) {
        byte[] b = new byte[s.length()];
        for (int i = 0; i < s.length(); i++) {
            b[i] = (byte) s.charAt(i);
        }
        return b;
    }

    public static void main(String []args){
        //Main menu

        int choix;
        //Ask user if he wants to encrypt or decrypt
        do {
            System.out.println();
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("--------- Bienvenue dans le programme de chiffrement/déchiffrement ---------");
            System.out.println("----------------------------------------------------------------------------");
            System.out.println();
            System.out.print("Saisir le message a chiffrer: ");
            Scanner sc = new Scanner(System.in);
            String message = sc.nextLine();
            System.out.println();
            System.out.print("Saisir le clé: ");
            String cle = sc.nextLine();
            System.out.println();
            System.out.print("Saisir le vecteur d'initialisation: ");
            vectInit = stringToByteArray(sc.nextLine());
            System.out.println();
            System.out.println("1. Chiffrer et déchiffrer");
            System.out.println("2. Quitter");
            System.out.println();
            System.out.print("Choix: ");
            choix = sc.nextInt();
            String messageChiffre = Chiffrer(message, cle);
            String messageDechiffre = Dechiffrer(messageChiffre, cle);
            //encrypt and decrypt
            switch(choix){
                case 1:
                    System.out.println();
                    System.out.println("Le message chiffré: " + messageChiffre);
                    System.out.println("Le message dechiffré: " + messageDechiffre);
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Choix invalide");
                    break;
            }
        }while(choix != 2);
    }


}
