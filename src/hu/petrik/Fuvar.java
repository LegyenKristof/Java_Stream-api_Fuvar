package hu.petrik;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Fuvar {
    private int azonosito;
    private LocalDateTime indulas;
    private int idotartam;
    private double tavolsag;
    private double viteldij;
    private double borravalo;
    private String fizetes;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Fuvar(String sor) {
        String[] adatok = sor.split(";");
        this.azonosito = Integer.parseInt(adatok[0]);
        this.indulas = LocalDateTime.parse(adatok[1], dateTimeFormatter);
        this.idotartam = Integer.parseInt(adatok[2]);
        this.tavolsag = Double.parseDouble(adatok[3].replace(',','.'));
        this.viteldij = Double.parseDouble(adatok[4].replace(',','.'));
        this.borravalo = Double.parseDouble(adatok[5].replace(',','.'));
        this.fizetes = adatok[6];
    }

    public int getAzonosito() {
        return azonosito;
    }

    public void setAzonosito(int azonosito) {
        this.azonosito = azonosito;
    }

    public LocalDateTime getIndulas() {
        return indulas;
    }

    public void setIndulas(LocalDateTime indulas) {
        this.indulas = indulas;
    }

    public int getIdotartam() {
        return idotartam;
    }

    public void setIdotartam(int idotartam) {
        this.idotartam = idotartam;
    }

    public double getTavolsag() {
        return tavolsag;
    }

    public void setTavolsag(double tavolsag) {
        this.tavolsag = tavolsag;
    }

    public double getViteldij() {
        return viteldij;
    }

    public void setViteldij(double viteldij) {
        this.viteldij = viteldij;
    }

    public double getBorravalo() {
        return borravalo;
    }

    public void setBorravalo(double borravalo) {
        this.borravalo = borravalo;
    }

    public String getFizetes() {
        return fizetes;
    }

    public void setFizetes(String fizetes) {
        this.fizetes = fizetes;
    }

    public double getBorravaloViteldijArany() {
        return borravalo / viteldij;
    }

    @Override
    public String toString() {
        return "Fuvar{" +
                "id=" + azonosito +
                ", indulas=" + indulas +
                ", idotartam=" + idotartam +
                ", tavolsag=" + tavolsag +
                ", viteldij=" + viteldij +
                ", borravalo=" + borravalo +
                ", fizetes='" + fizetes + '\'' +
                '}';
    }
}
