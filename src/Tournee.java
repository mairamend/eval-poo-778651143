public class Tournee {

    // ---------- a) Tri fusion ----------
    public static void trierFusion(Colis[] t) {
        if (t == null || t.length < 2) {
            return;                                   // vide ou un seul élément : déjà trié
        }
        int milieu = t.length / 2;
        Colis[] gauche = new Colis[milieu];
        Colis[] droite = new Colis[t.length - milieu];

        for (int i = 0; i < gauche.length; i++) gauche[i] = t[i];
        for (int i = 0; i < droite.length; i++) droite[i] = t[milieu + i];

        trierFusion(gauche);
        trierFusion(droite);
        fusionner(t, gauche, droite);
    }

    private static void fusionner(Colis[] t, Colis[] gauche, Colis[] droite) {
        int i = 0, j = 0, k = 0;
        while (i < gauche.length && j < droite.length) {
            if (gauche[i].compareTo(droite[j]) <= 0) {   // <= : tri stable
                t[k++] = gauche[i++];
            } else {
                t[k++] = droite[j++];
            }
        }
        while (i < gauche.length) t[k++] = gauche[i++];
        while (j < droite.length) t[k++] = droite[j++];
    }

    // ---------- b) Chargement optimal (programmation dynamique) ----------
    public static Colis[] chargementOptimal(Colis[] t, int capacite) {
        int n = t.length;
        long[][] meilleur = new long[n + 1][capacite + 1];   // ligne 0 : tout à 0

        for (int i = 1; i <= n; i++) {
            int poids = t[i - 1].getPoids();
            long tarif = t[i - 1].tarif();
            for (int cap = 0; cap <= capacite; cap++) {
                if (poids > cap) {
                    meilleur[i][cap] = meilleur[i - 1][cap];
                } else {
                    meilleur[i][cap] = Math.max(meilleur[i - 1][cap],
                                                meilleur[i - 1][cap - poids] + tarif);
                }
            }
        }

        // Reconstitution : on remonte depuis meilleur[n][capacite]
        boolean[] pris = new boolean[n];
        int cap = capacite;
        int nbChoisis = 0;
        for (int i = n; i >= 1; i--) {
            if (meilleur[i][cap] != meilleur[i - 1][cap]) {   // le colis i a été pris
                pris[i - 1] = true;
                nbChoisis++;
                cap -= t[i - 1].getPoids();
            }
        }

        // Tableau de taille exacte
        Colis[] resultat = new Colis[nbChoisis];
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (pris[i]) {
                resultat[k++] = t[i];
            }
        }
        return resultat;
    }

    // ---------- c) Programme principal ----------
    public static void main(String[] args) {
        Colis[] lot = {
            new ColisStandard("C01", 4, false),
            new ColisExpress("C02", 3, true),
            new ColisFragile("C03", 5, false, 120_000),
            new ColisStandard("C04", 7, true),
            new ColisExpress("C05", 2, false),
            new ColisFragile("C06", 6, true, 45_000),
            new ColisStandard("C07", 1, true),
            new ColisExpress("C08", 8, false),
            new ColisFragile("C09", 3, false, 300_000),
            new ColisStandard("C10", 5, false),
        };

        trierFusion(lot);
        for (Colis c : lot) {
            System.out.println(c);
        }

        Colis[] choisis = chargementOptimal(lot, Parametres.CAPACITE);
        long somme = 0;
        for (Colis c : choisis) {
            somme += c.tarif();
        }
        System.out.println("Recette optimale : " + somme
                + " FCFA (capacite " + Parametres.CAPACITE + " kg)");
    }
}