package Mense;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServizioMensa {

    public static List<Mensa> getMense() {

     final   List<Mensa> mense = new ArrayList<>();

                try {
                    Document doc = Jsoup.connect("http://www.esupd.gov.it/").get();

                    ArrayList<String> downServers = new ArrayList<>();
                    Element table = doc.select("table").get(0); //select the first table.
                    Elements rows = table.select("tr");

                    for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
                        Element rowTR = rows.get(i);
                        Elements cols = rowTR.select("td > span");

                        String mensa = rowTR.select("th a").text();

                        String pranzo = cols.get(0).toString().contains("closed") ? "Chiuso" : "Aperto";
                        String cena = cols.get(1).toString().contains("closed") ? "Chiuso" : "Aperto";

                       Mensa mensaO = new Mensa(mensa, pranzo, cena);
                       mense.add(mensaO);

                    }

                }catch (IOException e) {
                    e.printStackTrace();
                }

        return mense;
    }
}
