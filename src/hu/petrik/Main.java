package hu.petrik;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

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
        System.out.println("7: " + hetedik());
        System.out.println("8: " + nyolcadik());
        System.out.println("9: ");
        for (Fuvar f : kilencedik()) {
            System.out.println(f);
        }
        System.out.println("10: " + tizedik() + " fuvar");
        System.out.println("11: " + String.format("%.2f", tizenegyedik()));
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
                .filter(f -> f.getAzonosito() == 6185)
                .count();
        double bevetel = list.stream()
                .filter(f -> f.getAzonosito() == 6185)
                .mapToDouble(f -> f.getViteldij() + f.getBorravalo())
                .sum();
        return String.format("A bevétele %.2f$, %d fuvar alatt", bevetel, fuvarokSzama);
    }

    private static double harmadik() {
        return list.stream()
                .mapToDouble(f -> f.getTavolsag())
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
                .filter(f -> f.getAzonosito() == 4261)
                .mapToDouble(f -> f.getTavolsag() * 1.6)
                .sum();
    }

    public static String hetedik() {
        long darab = list.stream()
                .filter(f -> f.getIdotartam() > 0 && f.getViteldij() > 0 && f.getTavolsag() == 0)
                .count();

        long idotartam = list.stream()
                .filter(f -> f.getIdotartam() > 0 && f.getViteldij() > 0 && f.getTavolsag() == 0)
                .mapToInt(f -> f.getIdotartam())
                .sum();

        double bevetel = list.stream()
                .filter(f -> f.getIdotartam() > 0 && f.getViteldij() > 0 && f.getTavolsag() == 0)
                .mapToDouble(f -> f.getViteldij() + f.getBorravalo())
                .sum();

        return String.format("%d fuvar, %d másodperc, %.2f$ bevétel", darab, idotartam, bevetel);
    }

    public static boolean nyolcadik() {
        return list.stream()
                .anyMatch(f -> f.getAzonosito() == 1452);
    }

    public static List<Fuvar> kilencedik() {
        return list.stream()
                .filter(f -> f.getIdotartam() > 0)
                .sorted(Comparator.comparingInt(Fuvar::getIdotartam))
                .limit(3)
                .collect(Collectors.toList());
    }

    public static long tizedik() {
        return list.stream()
                .filter(f -> f.getIndulas().getMonthValue() == 12 && f.getIndulas().getDayOfMonth() == 24)
                .count();
    }

    public static double tizenegyedik() {
        return list.stream()
                .filter(f -> f.getIndulas().getMonthValue() == 12 && f.getIndulas().getDayOfMonth() == 31)
                .mapToDouble(f -> f.getBorravaloViteldijArany())
                .average()
                .getAsDouble();
    }
}
