/**
 * Programme client pour tester toutes les fonctions de la bibliothèque Supermatrice
 */
public class TestSupermatrice {
    
    public static void main(String[] args) {
        System.out.println("=== Test de la bibliothèque Supermatrice ===\n");
        
        try {
            // Test 1: Allocation d'une supermatrice
            testAllocation();
            
            // Test 2: Accès aux éléments
            testAcces();
            
            // Test 3: Produit matriciel
            testProduit();
            
            // Test 4: Permutation de lignes
            testPermutation();
            
            // Test 5: Sous-matrice
            testSousMatrice();
            
            // Test 6: Transformation matrice → supermatrice
            testMatSupermat();
            
            // Test 7: Transformation supermatrice → matrice
            testSupermatMat();
            
            // Test 8: Test de contiguité
            testContiguité();
            
            // Test 9: Libération
            testLiberation();
            
            System.out.println("=== Tous les tests sont définis ===");
            
        } catch (Exception e) {
            System.err.println("Erreur lors des tests: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void testAllocation() {
        System.out.println("Test 1: Allocation d'une supermatrice");
        System.out.println("allouerSupermat(3, 4) - à implémenter");
        
        try {
            Supermatrice sm = Supermatrice.allouerSupermat(3, 4);
            System.out.println("✓ Méthode appelée");
        } catch (UnsupportedOperationException e) {
            System.out.println("⚠ Méthode non implémentée: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testAcces() {
        System.out.println("Test 2: Accès aux éléments");
        System.out.println("acces(i, j) et setElement(i, j, valeur) - à implémenter");
        
        try {
            Supermatrice sm = Supermatrice.allouerSupermat(2, 2);
            sm.setElement(0, 0, 1.5);
            double val = sm.acces(0, 0);
            System.out.println("✓ Méthodes appelées");
        } catch (UnsupportedOperationException e) {
            System.out.println("⚠ Méthodes non implémentées: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testProduit() {
        System.out.println("Test 3: Produit matriciel");
        System.out.println("superProduit(a, b) - à implémenter");
        
        try {
            Supermatrice a = Supermatrice.allouerSupermat(2, 3);
            Supermatrice b = Supermatrice.allouerSupermat(3, 2);
            Supermatrice c = Supermatrice.superProduit(a, b);
            System.out.println("✓ Méthode appelée");
        } catch (UnsupportedOperationException e) {
            System.out.println("⚠ Méthode non implémentée: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testPermutation() {
        System.out.println("Test 4: Permutation de lignes");
        System.out.println("permuterLignes(i, j) - à implémenter");
        
        try {
            Supermatrice sm = Supermatrice.allouerSupermat(3, 3);
            sm.permuterLignes(0, 2);
            System.out.println("✓ Méthode appelée");
        } catch (UnsupportedOperationException e) {
            System.out.println("⚠ Méthode non implémentée: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testSousMatrice() {
        System.out.println("Test 5: Sous-matrice");
        System.out.println("sousMatrice(l1, l2, c1, c2) - à implémenter");
        
        try {
            Supermatrice sm = Supermatrice.allouerSupermat(4, 4);
            ISupermatrice sous = sm.sousMatrice(1, 2, 1, 2);
            System.out.println("✓ Méthode appelée");
        } catch (UnsupportedOperationException e) {
            System.out.println("⚠ Méthode non implémentée: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testMatSupermat() {
        System.out.println("Test 6: Transformation matrice → supermatrice");
        System.out.println("matSupermat(m, nld, ncd, nle, nce) - à implémenter");
        
        try {
            double[][] matrice = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
            Supermatrice sm = Supermatrice.matSupermat(matrice, 2, 3, 2, 3);
            System.out.println("✓ Méthode appelée");
        } catch (UnsupportedOperationException e) {
            System.out.println("⚠ Méthode non implémentée: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testSupermatMat() {
        System.out.println("Test 7: Transformation supermatrice → matrice");
        System.out.println("supermatMat(m, nld, ncd) - à implémenter");
        
        try {
            Supermatrice sm = Supermatrice.allouerSupermat(2, 2);
            double[][] matrice = new double[2][2];
            sm.supermatMat(matrice, 2, 2);
            System.out.println("✓ Méthode appelée");
        } catch (UnsupportedOperationException e) {
            System.out.println("⚠ Méthode non implémentée: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testContiguité() {
        System.out.println("Test 8: Test de contiguité");
        System.out.println("contiguité() - à implémenter");
        
        try {
            Supermatrice sm = Supermatrice.allouerSupermat(3, 3);
            int contig = sm.contiguité();
            System.out.println("✓ Méthode appelée");
        } catch (UnsupportedOperationException e) {
            System.out.println("⚠ Méthode non implémentée: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testLiberation() {
        System.out.println("Test 9: Libération de mémoire");
        System.out.println("rendreSupermat() - à implémenter");
        
        try {
            Supermatrice sm = Supermatrice.allouerSupermat(2, 2);
            sm.rendreSupermat();
            System.out.println("✓ Méthode appelée");
        } catch (UnsupportedOperationException e) {
            System.out.println("⚠ Méthode non implémentée: " + e.getMessage());
        }
        System.out.println();
    }
}
