/**
 * Implémentation Java des Supermatrices
 * Basée sur les spécifications du problème PB04_Supermatrices.pdf
 */

/**
 * Classe représentant une supermatrice
 * Équivalent du descripteur de supermatrice en C
 */
public class SuperMatrice {
    private int nl;           // nombre de lignes
    private int nc;           // nombre de colonnes
    private double[][] ligne; // tableau de pointeurs vers les lignes
    
    /**
     * Constructeur privé pour la création contrôlée
     */
    private SuperMatrice(int nl, int nc) {
        this.nl = nl;
        this.nc = nc;
        this.ligne = new double[nl][];
    }
    
    /**
     * Accesseurs
     */
    public int getNl() { return nl; }
    public int getNc() { return nc; }
    
    /**
     * 1. Fonction allouerSupermat
     * Alloue une supermatrice entièrement nouvelle
     */
    
    public static SuperMatrice allouerSupermat(int nl, int nc) {
        if (nl <= 0 || nc <= 0) {
            return null;
        }
        
        SuperMatrice sm = new SuperMatrice(nl, nc);
        
        // Allocation contiguë des lignes
        for (int i = 0; i < nl; i++) {
            sm.ligne[i] = new double[nc];
        }
        
        return sm;
    }
    
    /**
     * 2. Macro acces (implémentée comme méthode statique)
     * Permet l'accès aux coefficients de la supermatrice
     */
    
    public static double acces(SuperMatrice sm, int i, int j) {
        if (sm == null || i < 0 || i >= sm.nl || j < 0 || j >= sm.nc) {
            throw new IndexOutOfBoundsException("Indices hors limites");
        }
        return sm.ligne[i][j];
    }
    
    /**
     * Setter pour l'accès en écriture (statique)
     */
    public static void setAcces(SuperMatrice sm, int i, int j, double valeur) {
        if (sm == null || i < 0 || i >= sm.nl || j < 0 || j >= sm.nc) {
            throw new IndexOutOfBoundsException("Indices hors limites");
        }
        sm.ligne[i][j] = valeur;
    }
    
    /**
     * 3. Fonction superProduit
     * Calcule le produit matriciel de deux supermatrices
     */
    
    public static SuperMatrice superProduit(SuperMatrice a, SuperMatrice b) {
        if (a == null || b == null || a.nc != b.nl) {
            return null;
        }
        
        SuperMatrice resultat = allouerSupermat(a.nl, b.nc);
        if (resultat == null) {
            return null;
        }
        
        // Calcul du produit matriciel
        for (int i = 0; i < a.nl; i++) {
            for (int j = 0; j < b.nc; j++) {
                double somme = 0.0;
                for (int k = 0; k < a.nc; k++) {
                    somme += SuperMatrice.acces(a, i, k) * SuperMatrice.acces(b, k, j);
                }
                SuperMatrice.setAcces(resultat, i, j, somme);
            }
        }
        
        return resultat;
    }
    
    /**
     * 4. Fonction permuterLignes (statique)
     * Permute les lignes i et j de la supermatrice (efficacité maximum)
     */
    
    public static void permuterLignes(SuperMatrice sm, int i, int j) {
        if (sm == null || i < 0 || i >= sm.nl || j < 0 || j >= sm.nl) {
            throw new IndexOutOfBoundsException("Indices de lignes hors limites");
        }
        
        if (i != j) {
            // Permutation efficace: échange des références
            double[] temp = sm.ligne[i];
            sm.ligne[i] = sm.ligne[j];
            sm.ligne[j] = temp;
        }
    }
    
    /**
     * 5. Fonction sousMatrice (statique)
     * Crée une sous-matrice utilisant les coefficients déjà alloués
     */
    
    public static SuperMatrice sousMatrice(SuperMatrice sm, int l1, int l2, int c1, int c2) {
        if (sm == null || l1 < 0 || l2 >= sm.nl || c1 < 0 || c2 >= sm.nc || l1 > l2 || c1 > c2) {
            return null;
        }
        
        int nouvNl = l2 - l1 + 1;
        int nouvNc = c2 - c1 + 1;
        
        SuperMatrice nouvSm = new SuperMatrice(nouvNl, nouvNc);
        
        // Création de nouvelles lignes pointant vers les sous-parties
        for (int i = 0; i < nouvNl; i++) {
            nouvSm.ligne[i] = new double[nouvNc];
            // Copie des éléments de la sous-matrice
            for (int j = 0; j < nouvNc; j++) {
                nouvSm.ligne[i][j] = sm.ligne[l1 + i][c1 + j];
            }
        }
        
        return nouvSm;
    }
    
    /**
     * 6. Fonction matSupermat
     * Transformation matrice ordinaire -> supermatrice
     */
    
    public static SuperMatrice matSupermat(double[][] m, int nld, int ncd, int nle, int nce) {
        if (m == null || nle <= 0 || nce <= 0 || nle > nld || nce > ncd) {
            return null;
        }
        
        SuperMatrice sm = new SuperMatrice(nle, nce);
        
        // Partage des coefficients avec la matrice initiale
        for (int i = 0; i < nle; i++) {
            sm.ligne[i] = new double[nce];
            System.arraycopy(m[i], 0, sm.ligne[i], 0, nce);
        }
        
        return sm;
    }
    
    /**
     * 7. Fonction supermatMat (statique)
     * Transformation supermatrice -> matrice ordinaire
     */
    
    public static void supermatMat(SuperMatrice sm, double[][] m, int nld, int ncd) {
        if (sm == null || m == null || nld < sm.nl || ncd < sm.nc) {
            throw new IllegalArgumentException("Matrice destination trop petite");
        }
        
        // Copie des coefficients de la supermatrice vers la matrice ordinaire
        for (int i = 0; i < sm.nl; i++) {
            for (int j = 0; j < sm.nc; j++) {
                m[i][j] = sm.ligne[i][j];
            }
        }
    }
    

    
    /**
     * 9. Fonction rendreSupermat (statique) - équivalent du free en C
     * En Java, le garbage collector s'occupe de la libération mémoire
     * Cette méthode peut être appelée pour nettoyer explicitement
     */
    /**
     * Affiche la matrice à la console
     */
    public void afficher() {
        for (int i = 0; i < nl; i++) {
            for (int j = 0; j < nc; j++) {
                System.out.printf("%8.2f ", ligne[i][j]);
            }
            System.out.println();
        }
    }
    
    public static void rendreSupermat(SuperMatrice sm) {
        if (sm == null) return;
        
        // En Java, on peut simplement mettre les références à null
        // Le garbage collector s'occupera du reste
        for (int i = 0; i < sm.nl; i++) {
            sm.ligne[i] = null;
        }
        sm.ligne = null;
    }
    
    /**
     * Méthode utilitaire pour afficher la supermatrice
     */
    // public void afficher() {
    //     System.out.println("SuperMatrice " + nl + "x" + nc + ":");
    //     for (int i = 0; i < nl; i++) {
    //         for (int j = 0; j < nc; j++) {
    //             System.out.printf("%8.2f ", ligne[i][j]);
    //         }
    //         System.out.println();
    //     }
    // }
}

