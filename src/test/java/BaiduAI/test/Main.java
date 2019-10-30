package BaiduAI.test;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static class Hu {
        private int id;

        public Hu(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    static void show(Hu hu) {
        hu.id = 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Hu hu = new Hu(2);
        show(hu);
        System.out.println(hu.id);

    }


}
