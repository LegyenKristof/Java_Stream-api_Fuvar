package hu.petrik;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    private static List<Fuvar> list;

    public static void main(String[] args) throws IOException {
        feltoltes();
        System.out.println("1: " + elso() + " fuvar");
        System.out.println("2: " + masodik());
        System.out.println("3: " + String.format("%.1f", harmadik()) + " mérföld");
        System.out.println("4: " + negyedik());
        System.out.println("5: " + otodik());
        System.out.println("6: " + String.format("%.1f", hatodik()) + " km");
    }

    private static void feltoltes() throws IOException {
        list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("fuvar.csv"));
        br.readLine();
        String s = br.readLine();
        while (s != null) {
            list.add(new Fuvar(s));
            s = br.readLine();
        }
        br.close();
    }

    private static long elso() {
        return list.stream()
                .count();
    }

    private static String masodik() {
        long fuvarokSzama = list.stream()
                .filter(t -> t.getAzonosito() == 6185)
                .count();
        double bevetel = list.stream()
                .filter(t -> t.getAzonosito() == 6185)
                .mapToDouble(t -> t.getViteldij() + t.getBorravalo())
                .sum();
        return String.format("A bevétele %.2f$, %d fuvar alatt", bevetel, fuvarokSzama);
    }

    private static double harmadik() {
        return list.stream()
                .mapToDouble(t -> t.getTavolsag())
                .sum();
    }

    public static Fuvar negyedik() {
        return list.stream()
                .max(Comparator.comparingInt(Fuvar::getIdotartam))
                .get();
    }

    public static Fuvar otodik() {
        return list.stream()
                .max(Comparator.comparingDouble(Fuvar::getBorravaloViteldijArany))
                .get();
    }

    public static double hatodik() {
        return list.stream()
                .filter(t -> t.getAzonosito() == 4261)
                .mapToDouble(t -> t.getTavolsag() * 1.6)
                .sum();
    }
}
