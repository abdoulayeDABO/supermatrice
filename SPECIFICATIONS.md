# Spécifications Techniques - Projet Supermatrice

## Vue d'ensemble

Ce document décrit les spécifications techniques pour l'implémentation des supermatrices en Java, basé sur le problème original en langage C.

## Architecture

### Interface ISupermatrice
Définit le contrat pour toutes les opérations sur les supermatrices.

### Classe Supermatrice
Implémentation concrète de l'interface, avec la structure de données optimisée.

## Structure de données

```java
public class Supermatrice {
    protected int nl;              // nombre de lignes
    protected int nc;              // nombre de colonnes  
    protected double[][] lignes;   // tableau de pointeurs vers les lignes
}
```

## Méthodes à implémenter

### 1. `allouerSupermat(int nl, int nc)` - STATIQUE
**Objectif :** Allouer une nouvelle supermatrice de taille nl × nc
**Contraintes :**
- Retourner `null` si allocation impossible ou paramètres invalides
- Initialiser correctement tous les éléments de structure
- Coefficients peuvent avoir des valeurs quelconques

### 2. `acces(int i, int j)` et `setElement(int i, int j, double valeur)`
**Objectif :** Accès en lecture/écriture aux coefficients
**Contraintes :**
- Vérification des bornes (0 ≤ i < nl, 0 ≤ j < nc)
- Performance optimale (équivalent macro en C)

### 3. `superProduit(Supermatrice a, Supermatrice b)` - STATIQUE
**Objectif :** Calculer le produit matriciel a × b
**Contraintes :**
- Vérifier compatibilité des dimensions (a.nc == b.nl)
- Retourner `null` si incompatible ou allocation impossible
- Algorithme standard de multiplication matricielle

### 4. `permuterLignes(int i, int j)`
**Objectif :** Permuter les lignes i et j
**Contraintes :**
- Maximum d'efficacité : échanger les références, pas les données
- Vérification des bornes

### 5. `sousMatrice(int l1, int l2, int c1, int c2)`
**Objectif :** Extraire une sous-matrice
**Contraintes :**
- Partager les coefficients existants (pas de copie)
- Allouer seulement descripteur et table de lignes
- Vérifier validité des paramètres (l1 ≤ l2, c1 ≤ c2)

### 6. `matSupermat(double[][] m, int nld, int ncd, int nle, int nce)` - STATIQUE
**Objectif :** Transformer matrice ordinaire → supermatrice
**Paramètres :**
- `m` : matrice source
- `nld`, `ncd` : dimensions déclarées
- `nle`, `nce` : dimensions effectives
**Contraintes :**
- Partager les coefficients (pas de copie)
- Allouer seulement descripteur et table de lignes

### 7. `supermatMat(double[][] m, int nld, int ncd)`
**Objectif :** Transformer supermatrice → matrice ordinaire
**Contraintes :**
- Copier les coefficients vers la matrice destination
- Vérifier que la destination est assez grande

### 8. `contiguité()`
**Objectif :** Analyser la disposition des lignes en mémoire
**Valeurs de retour :**
- `2` : lignes contiguës et ordonnées
- `1` : lignes contiguës mais désordonnées  
- `0` : lignes non contiguës
**Note :** En Java, simulation de la contiguité mémoire

### 9. `rendreSupermat()`
**Objectif :** Libérer la mémoire
**Note :** En Java, marquer pour garbage collection

## Considérations d'implémentation

### Gestion mémoire
- Java gère automatiquement la mémoire via GC
- `rendreSupermat()` peut mettre les références à `null`

### Performance
- Privilégier l'échange de références aux copies de données
- Optimiser les accès aux éléments

### Contiguïté
- En Java, impossible de garantir la contiguïté mémoire réelle
- Simuler le comportement via l'ordre d'allocation

### Validation
- Toujours vérifier les paramètres d'entrée
- Gérer les cas d'erreur avec `null` ou exceptions appropriées

## Tests

Le programme `TestSupermatrice` doit tester :
- Tous les cas nominaux
- Cas limites et erreurs
- Performance sur grandes matrices
- Intégrité des données après opérations

## Bonnes pratiques

1. **Documentation :** Javadoc complète pour toutes les méthodes publiques
2. **Validation :** Contrôles systématiques des paramètres
3. **Performance :** Éviter les copies inutiles
4. **Maintenance :** Code lisible et bien structuré
5. **Tests :** Couverture complète des fonctionnalités
