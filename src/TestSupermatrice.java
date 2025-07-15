/**
 * Classe de test pour toutes les fonctions
 */
public class TestSuperMatrice {
    public static void main(String[] args) {
        System.out.println("=== Test de la bibliothèque SuperMatrice ===\n");
        
        // Test 1: Allocation
        System.out.println("1. Test d'allocation:");
        SuperMatrice sm1 = SuperMatrice.allouerSupermat(3, 3);
        if (sm1 != null) {
            System.out.println("✓ Allocation réussie pour une matrice 3x3");
        }
        
        // Initialisation avec des valeurs
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                SuperMatrice.setAcces(sm1, i, j, i * 3 + j + 1);
            }
        }
        sm1.afficher();
        
        // Test 2: Accès
        System.out.println("\n2. Test d'accès:");
        System.out.println("Élément (1,1): " + SuperMatrice.acces(sm1, 1, 1));
        
        // Test 3: Produit matriciel
        System.out.println("\n3. Test du produit matriciel:");
        SuperMatrice sm2 = SuperMatrice.allouerSupermat(3, 2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                SuperMatrice.setAcces(sm2, i, j, (i + 1) * (j + 1));
            }
        }
        
        System.out.println("Matrice A:");
        sm1.afficher();
        System.out.println("Matrice B:");
        sm2.afficher();
        
        SuperMatrice produit = SuperMatrice.superProduit(sm1, sm2);
        if (produit != null) {
            System.out.println("Produit A × B:");
            produit.afficher();
        }
        
        // Test 4: Permutation de lignes
        System.out.println("\n4. Test de permutation de lignes:");
        System.out.println("Avant permutation des lignes 0 et 2:");
        sm1.afficher();
        SuperMatrice.permuterLignes(sm1, 0, 2);
        System.out.println("Après permutation des lignes 0 et 2:");
        sm1.afficher();
        
        // Test 5: Sous-matrice
        System.out.println("\n5. Test de sous-matrice:");
        SuperMatrice sousMat = SuperMatrice.sousMatrice(sm1, 0, 1, 0, 1);
        if (sousMat != null) {
            System.out.println("Sous-matrice [0:1, 0:1]:");
            sousMat.afficher();
        }
        
        // Test 6: Transformation matrice -> supermatrice
        System.out.println("\n6. Test matrice -> supermatrice:");
        double[][] matOrdinaire = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        SuperMatrice smFromMat = SuperMatrice.matSupermat(matOrdinaire, 3, 3, 3, 3);
        if (smFromMat != null) {
            System.out.println("SuperMatrice créée depuis matrice ordinaire:");
            smFromMat.afficher();
        }
        
        // Test 7: Transformation supermatrice -> matrice
        System.out.println("\n7. Test supermatrice -> matrice:");
        double[][] matDestination = new double[3][3];
        SuperMatrice.supermatMat(smFromMat, matDestination, 3, 3);
        System.out.println("Matrice ordinaire créée depuis supermatrice:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%8.2f ", matDestination[i][j]);
            }
            System.out.println();
        }
        
        // Test 8: Libération (simulation)
        System.out.println("\n8. Test de libération:");
        SuperMatrice.rendreSupermat(sm1);
        System.out.println("✓ Supermatrice libérée");
        
        System.out.println("\n=== Fin des tests ===");
    }
}