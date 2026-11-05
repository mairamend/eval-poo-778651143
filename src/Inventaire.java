public class Inventaire {

    public static long valeurAssuree(Object[] objets) {
        long somme = 0;
        for (Object o : objets) {
            if (o instanceof ColisFragile) {   // false pour null
                somme += ((ColisFragile) o).getValeurDeclaree();
            }
        }
        return somme;
    }

    public static int[] compterParType(Object[] objets) {
        int[] r = new int[4];   // {standard, express, fragile, autres}
        for (Object o : objets) {
            if (o == null) {
                continue;                       // null ignoré
            } else if (o instanceof ColisFragile) {
                r[2]++;                         // fragile testé AVANT standard
            } else if (o instanceof ColisStandard) {
                r[0]++;
            } else if (o instanceof ColisExpress) {
                r[1]++;
            } else {
                r[3]++;
            }
        }
        return r;
    }
}