/**
 * Classe pour la gestion des supermatrices
 * Une supermatrice est une structure optimisée pour représenter des matrices
 * avec des lignes potentiellement non contiguës en mémoire
 */


// Structure interne pour représenter une supermatrice
class SUPERMRT {
    public double[][] coefficients;  // Matrice des coefficients
    public int[] lignes;            // Table des indices de lignes
    public int nle;                 // Nombre effectif de lignes
    public int nce;                 // Nombre effectif de colonnes
    public int nld;                 // Nombre de lignes déclarées
    public int ncd;                 // Nombre de colonnes déclarées
    
    public SUPERMRT(int nld, int ncd, int nle, int nce) {
        this.nld = nld;
        this.ncd = ncd;
        this.nle = nle;
        this.nce = nce;
        this.coefficients = new double[nld][ncd];
        this.lignes = new int[nle];
        
        // Initialisation des indices de lignes
        for (int i = 0; i < nle; i++) {
            this.lignes[i] = i;
        }
    }
}

public class SuperMatrice {
    
    /**
     * 1. Alloue et initialise une supermatrice
     * @param nld nombre de lignes déclarées
     * @param ncd nombre de colonnes déclarées
     * @param nle nombre effectif de lignes
     * @param nce nombre effectif de colonnes
     * @return nouvelle supermatrice ou null si allocation impossible
     */
    public static SUPERMRT allouerSupermat(int nld, int ncd, int nle, int nce) {
        try {
            if (nld <= 0 || ncd <= 0 || nle <= 0 || nce <= 0 || nle > nld || nce > ncd) {
                return null;
            }
            return new SUPERMRT(nld, ncd, nle, nce);
        } catch (OutOfMemoryError e) {
            return null;
        }
    }
    
    /**
     * 2. Accède à l'élément (i,j) de la supermatrice
     * @param a supermatrice
     * @param i indice de ligne
     * @param j indice de colonne
     * @return valeur de l'élément ou 0.0 si indices invalides
     */
    public static double accesSupermat(SUPERMRT a, int i, int j) {
        if (a == null || i < 0 || i >= a.nle || j < 0 || j >= a.nce) {
            return 0.0;
        }
        int ligneReelle = a.lignes[i];
        return a.coefficients[ligneReelle][j];
    }
    
    /**
     * 3. Calcule le produit matriciel de deux supermatrices
     * @param a première supermatrice
     * @param b deuxième supermatrice
     * @return produit a * b ou null si impossible
     */
    public static SUPERMRT superProduit(SUPERMRT a, SUPERMRT b) {
        if (a == null || b == null || a.nce != b.nle) {
            return null;
        }
        
        SUPERMRT resultat = allouerSupermat(a.nle, b.nce, a.nle, b.nce);
        if (resultat == null) {
            return null;
        }
        
        // Calcul du produit matriciel
        for (int i = 0; i < a.nle; i++) {
            for (int j = 0; j < b.nce; j++) {
                double somme = 0.0;
                for (int k = 0; k < a.nce; k++) {
                    somme += accesSupermat(a, i, k) * accesSupermat(b, k, j);
                }
                int ligneReelle = resultat.lignes[i];
                resultat.coefficients[ligneReelle][j] = somme;
            }
        }
        
        return resultat;
    }
    
    /**
     * 4. Permute les lignes i et j de la supermatrice (efficacité maximale)
     * @param a supermatrice
     * @param i indice de première ligne
     * @param j indice de deuxième ligne
     */
    public static void permuterLignes(SUPERMRT a, int i, int j) {
        if (a == null || i < 0 || i >= a.nle || j < 0 || j >= a.nle || i == j) {
            return;
        }
        
        // Permutation efficace : échange seulement les indices dans la table des lignes
        int temp = a.lignes[i];
        a.lignes[i] = a.lignes[j];
        a.lignes[j] = temp;
    }
    
    /**
     * 5. Crée une sous-matrice à partir d'une supermatrice
     * @param a supermatrice source
     * @param l1 ligne de début
     * @param l2 ligne de fin
     * @param c1 colonne de début
     * @param c2 colonne de fin
     * @return sous-matrice ou null si paramètres invalides
     */
    public static SUPERMRT sousMatrice(SUPERMRT a, int l1, int l2, int c1, int c2) {
        if (a == null || l1 < 0 || l2 >= a.nle || c1 < 0 || c2 >= a.nce || 
            l1 > l2 || c1 > c2) {
            return null;
        }
        
        int nouvNle = l2 - l1 + 1;
        int nouvNce = c2 - c1 + 1;
        
        try {
            SUPERMRT sousmat = new SUPERMRT(nouvNle, nouvNce, nouvNle, nouvNce);
            
            // Partage des coefficients avec la matrice originale
            sousmat.coefficients = a.coefficients;
            
            // Création de la nouvelle table des lignes
            for (int i = 0; i < nouvNle; i++) {
                sousmat.lignes[i] = a.lignes[l1 + i];
            }
            
            return sousmat;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }
    
    /**
     * 6. Transforme une matrice ordinaire en supermatrice
     * @param m matrice ordinaire (tableau 2D)
     * @param nld nombre de lignes déclarées
     * @param ncd nombre de colonnes déclarées
     * @param nle nombre effectif de lignes
     * @param nce nombre effectif de colonnes
     * @return supermatrice créée ou null si impossible
     */
    public static SUPERMRT matSupermat(double[][] m, int nld, int ncd, int nle, int nce) {
        if (m == null || nld <= 0 || ncd <= 0 || nle <= 0 || nce <= 0 || 
            nle > nld || nce > ncd || m.length < nld) {
            return null;
        }
        
        try {
            SUPERMRT supermat = new SUPERMRT(nld, ncd, nle, nce);
            
            // Partage des coefficients avec la matrice originale
            supermat.coefficients = m;
            
            return supermat;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }
    
    /**
     * 7. Transforme une supermatrice en matrice ordinaire
     * @param sm supermatrice source
     * @param m matrice destination (tableau 2D)
     * @param nld nombre de lignes déclarées pour m
     * @param ncd nombre de colonnes déclarées pour m
     */
    public static void supermatMat(SUPERMRT sm, double[][] m, int nld, int ncd) {
        if (sm == null || m == null || nld < sm.nle || ncd < sm.nce || 
            m.length < nld) {
            return;
        }
        
        // Copie des coefficients de la supermatrice vers la matrice ordinaire
        for (int i = 0; i < sm.nle; i++) {
            if (m[i].length < ncd) continue;
            
            for (int j = 0; j < sm.nce; j++) {
                m[i][j] = accesSupermat(sm, i, j);
            }
        }
    }
    
    /**
     * 8. Analyse la contiguïté des lignes dans une supermatrice
     * @param a supermatrice à analyser
     * @return 2 si lignes contiguës et ordonnées, 1 si contiguës mais désordonnées, 0 sinon
     */
    public static int contiguité(SUPERMRT a) {
        if (a == null || a.nle <= 1) {
            return 2; // Une seule ligne est toujours contiguë et ordonnée
        }
        
        boolean contigue = true;
        boolean ordonnee = true;
        
        // Vérification de la contiguïté et de l'ordre
        for (int i = 0; i < a.nle - 1; i++) {
            int ligneActuelle = a.lignes[i];
            int ligneSuivante = a.lignes[i + 1];
            
            // Vérification de la contiguïté
            if (Math.abs(ligneActuelle - ligneSuivante) != 1) {
                contigue = false;
            }
            
            // Vérification de l'ordre
            if (ligneActuelle >= ligneSuivante) {
                ordonnee = false;
            }
        }
        
        if (contigue && ordonnee) {
            return 2;
        } else if (contigue) {
            return 1;
        } else {
            return 0;
        }
    }
    
    /**
     * 9. Libère l'espace occupé par une supermatrice
     * @param sm supermatrice à libérer
     */
    public static void rendreSupermat(SUPERMRT sm) {
        if (sm != null) {
            sm.coefficients = null;
            sm.lignes = null;
        }
        // Note: En Java, le garbage collector se chargera de libérer la mémoire
        // Cette méthode marque simplement les références comme null
    }
    
    // Méthodes utilitaires pour les tests
    
    /**
     * Méthode utilitaire pour afficher une supermatrice
     */
    public static void afficherSupermatrice(SUPERMRT a) {
        if (a == null) {
            System.out.println("Supermatrice null");
            return;
        }
        
        System.out.println("Supermatrice " + a.nle + "x" + a.nce + ":");
        for (int i = 0; i < a.nle; i++) {
            for (int j = 0; j < a.nce; j++) {
                System.out.printf("%.2f ", accesSupermat(a, i, j));
            }
            System.out.println();
        }
    }
}