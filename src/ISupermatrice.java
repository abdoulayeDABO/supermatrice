/**
 * Interface pour les opérations sur les supermatrices
 */
public interface ISupermatrice {
    
    /**
     * 2. Accès aux coefficients de la supermatrice
     * @param i indice de ligne
     * @param j indice de colonne
     * @return valeur à la position [i][j]
     */
    double acces(int i, int j);
    
    /**
     * Modification d'un élément
     * @param i indice de ligne
     * @param j indice de colonne
     * @param valeur nouvelle valeur
     */
    void setElement(int i, int j, double valeur);
    
    /**
     * 4. Permute deux lignes de la supermatrice
     * @param i indice de la première ligne
     * @param j indice de la deuxième ligne
     */
    void permuterLignes(int i, int j);
    
    /**
     * 5. Crée une sous-matrice
     * @param l1 ligne de début (incluse)
     * @param l2 ligne de fin (incluse)
     * @param c1 colonne de début (incluse)
     * @param c2 colonne de fin (incluse)
     * @return sous-matrice ou null si paramètres invalides
     */
    ISupermatrice sousMatrice(int l1, int l2, int c1, int c2);
    
    /**
     * 7. Transformation supermatrice → matrice
     * @param m matrice de destination
     * @param nld nombre de lignes déclarées dans m
     * @param ncd nombre de colonnes déclarées dans m
     */
    void supermatMat(double[][] m, int nld, int ncd);
    
    /**
     * 8. Analyse la disposition des lignes en mémoire
     * @return 2 si lignes contiguës et ordonnées, 1 si contiguës mais désordonnées, 0 sinon
     */
    int contiguité();
    
    /**
     * 9. Libération de la mémoire
     */
    void rendreSupermat();
    
    /**
     * Obtient le nombre de lignes
     */
    int getNombreLignes();
    
    /**
     * Obtient le nombre de colonnes
     */
    int getNombreColonnes();
    
    /**
     * Affichage de la supermatrice
     */
    void afficher();
}
