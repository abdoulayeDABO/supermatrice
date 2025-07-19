# Makefile pour le projet Supermatrice Java
# Utilisation: make [cible]

# Variables
SRC_DIR = src
BIN_DIR = bin
JAVA_FILES = $(wildcard $(SRC_DIR)/*.java)
CLASS_FILES = $(JAVA_FILES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

# Cible par défaut
all: compile

# Compilation
compile: $(BIN_DIR) $(CLASS_FILES)
	@echo "Compilation terminée avec succès"

# Création du répertoire bin
$(BIN_DIR):
	mkdir $(BIN_DIR)

# Règle pour compiler les fichiers Java
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	javac -d $(BIN_DIR) -cp $(SRC_DIR) $<

# Exécution du test
test: compile
	@echo "Exécution du programme de test..."
	cd $(BIN_DIR) && java TestSuperMatrice

# Nettoyage
clean:
	@rm -rf $(BIN_DIR)
	@echo "Nettoyage terminé"

# Affichage de l'aide
help:
	@echo "Cibles disponibles:"
	@echo "  all      - Compile le projet (défaut)"
	@echo "  compile  - Compile tous les fichiers Java"
	@echo "  test     - Compile et exécute le programme de test"
	@echo "  clean    - Supprime les fichiers compilés"
	@echo "  help     - Affiche cette aide"

# Déclaration des cibles qui ne correspondent pas à des fichiers
.PHONY: all compile test clean help
