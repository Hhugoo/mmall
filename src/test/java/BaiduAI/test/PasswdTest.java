package BaiduAI.test;

import java.util.Scanner;
import java.util.ArrayList;

public class PasswdTest{

        /**
         * @param args
         */
        static Scanner in = new Scanner(System.in);

        public static void main(String[] args){
            // TODO Auto-generated method stub
            int n;
            while(true) {
                System.out.println("******************************************");
                System.out.println("* 1.ShiftEncryption   2.ShiftDecryption  *");
                System.out.println("* 3.AffineEncryption  4.AffineDecryption *");
                System.out.println("******************************************");
                System.out.println("Please choose a option:");
//			System.out.println("***********************");
//			System.out.println("***********************");
//			System.out.println("***********************");
//			System.out.println("***********************");
                n = in.nextInt();
                switch(n) {
                    case 1:encShiftPassword();break;
                    case 2:unencShiftPassword();break;
                    case 3:encAffinePassword();break;
                    case 4:unencAffinePassword();break;
                    default:System.out.println("You choose a wrong option!");break;
                }
            }


        }

        private static void encShiftPassword(){
            System.out.println("please input k:");
            int k = in.nextInt();
            String getchar = in.nextLine();
            System.out.println("please input Plaintext:");
            String str = in.nextLine();
            System.out.println(shiftPass(str, k));
        }

        private static void unencShiftPassword(){
            System.out.println("please input k:");
            int k = in.nextInt();
            String getchar = in.nextLine();
            System.out.println("please input ciphertext:");
            String str = in.nextLine();
            System.out.println(unshiftPass(str, k));
        }

        private static void encAffinePassword(){
            System.out.println("please input a:");
            int a = in.nextInt();
            System.out.println("please input b:");
            int b = in.nextInt();
            String getchar = in.nextLine();
            System.out.println("please input Plaintext:");
            String str = in.nextLine();
            System.out.println(affinePass(str, a, b));
        }

        private static void unencAffinePassword(){
            System.out.println("please input a:");
            int a = in.nextInt();
            System.out.println("please input b:");
            int b = in.nextInt();
            String getchar = in.nextLine();
            System.out.println("please input ciphertext:");
            String str = in.nextLine();
            System.out.println(unaffinePass(str, a, b));
        }

        private static String shiftPass(String pass, int k) {
            int leng = pass.length();
            StringBuilder answer = new StringBuilder();
            for(int i=0; i<leng; i++) {
                if (pass.charAt(i) == ' ') {
                    answer.append(" ");
                }else {
                    answer.append((char)((pass.charAt(i)-97+k)%26+97));
                }
            }
            return answer.toString();
        }

        private static String unshiftPass(String pass, int k) {
            int leng = pass.length();
            StringBuilder answer = new StringBuilder();
            for(int i=0; i<leng; i++) {
                if (pass.charAt(i) == ' ') {
                    answer.append(" ");
                }else {
                    answer.append((char)((pass.charAt(i)-97-k+26000)%26+97));
                }
            }
            return answer.toString();
        }

        private static String affinePass(String pass, int a, int b) {
            int leng = pass.length();
            StringBuilder answer = new StringBuilder();
            for(int i=0; i<leng; i++) {
                if (pass.charAt(i) == ' ') {
                    answer.append(" ");
                }else {
                    answer.append((char)((a*(pass.charAt(i)-97)+b)%26+97));
                }
            }
            return answer.toString();
        }

        private static String unaffinePass(String pass, int a, int b) {
            int leng = pass.length();
            StringBuilder answer = new StringBuilder();
            int inverse = inverse(a, 26);
            for(int i=0; i<leng; i++) {
                if (pass.charAt(i) == ' ') {
                    answer.append(" ");
                }else {
                    answer.append((char)(((inverse*((pass.charAt(i)-97)-b)+26000)%26)+97));
                }
            }
            return answer.toString();
        }

        private static int inverse(int a, int mod) {
            for(int i=1; i<100; i++) {
                if((a*i+26000)%mod == 1) {
                    return i;
                }
            }
            return -1;
        }

    }
