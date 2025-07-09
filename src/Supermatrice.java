/**
 * Classe Supermatrice - Structure de base pour les matrices améliorées
 * 
 * Une supermatrice est composée de trois éléments :
 * 1. Un descripteur (nl, nc, lignes)
 * 2. Un tableau de pointeurs vers les lignes
 * 3. L'espace de stockage des éléments
 */
public class Supermatrice implements ISupermatrice {
    
    // Descripteur de la supermatrice
    protected int nl;          // nombre de lignes
    protected int nc;          // nombre de colonnes
    protected double[][] lignes; // tableau des lignes (pointeurs vers les données)
    
    /**
     * Constructeur protégé
     */
    protected Supermatrice(int nl, int nc) {
        this.nl = nl;
        this.nc = nc;
        this.lignes = new double[nl][];
    }
    
    /**
     * 1. Alloue une supermatrice entièrement nouvelle
     * @param nl nombre de lignes
     * @param nc nombre de colonnes
     * @return nouvelle supermatrice ou null si allocation impossible
     */
    public static Supermatrice allouerSupermat(int nl, int nc) {
        // À implémenter
        throw new UnsupportedOperationException("Méthode à implémenter");
    }
    
    /**
     * 3. Produit matriciel de deux supermatrices
     * @param a première supermatrice
     * @param b deuxième supermatrice
     * @return résultat du produit a × b ou null si impossible
     */
    public static Supermatrice superProduit(Supermatrice a, Supermatrice b) {
        // À implémenter
        throw new UnsupportedOperationException("Méthode à implémenter");
    }
    
    /**
     * 6. Transformation matrice → supermatrice
     * @param m matrice ordinaire (tableau 2D)
     * @param nld nombre de lignes déclarées
     * @param ncd nombre de colonnes déclarées
     * @param nle nombre effectif de lignes
     * @param nce nombre effectif de colonnes
     * @return supermatrice créée ou null si erreur
     */
    public static Supermatrice matSupermat(double[][] m, int nld, int ncd, int nle, int nce) {
        // À implémenter
        throw new UnsupportedOperationException("Méthode à implémenter");
    }
    
    // Implémentation des méthodes de l'interface
    
    @Override
    public double acces(int i, int j) {
        // À implémenter
        throw new UnsupportedOperationException("Méthode à implémenter");
    }
    
    @Override
    public void setElement(int i, int j, double valeur) {
        // À implémenter
        throw new UnsupportedOperationException("Méthode à implémenter");
    }
    
    @Override
    public void permuterLignes(int i, int j) {
        // À implémenter
        throw new UnsupportedOperationException("Méthode à implémenter");
    }
    
    @Override
    public ISupermatrice sousMatrice(int l1, int l2, int c1, int c2) {
        // À implémenter
        throw new UnsupportedOperationException("Méthode à implémenter");
    }
    
    @Override
    public void supermatMat(double[][] m, int nld, int ncd) {
        // À implémenter
        throw new UnsupportedOperationException("Méthode à implémenter");
    }
    
    @Override
    public int contiguité() {
        // À implémenter
        throw new UnsupportedOperationException("Méthode à implémenter");
    }
    
    @Override
    public void rendreSupermat() {
        // À implémenter
        throw new UnsupportedOperationException("Méthode à implémenter");
    }
    
    @Override
    public int getNombreLignes() {
        return nl;
    }
    
    @Override
    public int getNombreColonnes() {
        return nc;
    }
    
    @Override
    public void afficher() {
        // À implémenter
        throw new UnsupportedOperationException("Méthode à implémenter");
    }
}
