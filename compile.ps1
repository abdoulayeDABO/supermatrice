# Script de compilation pour le projet Supermatrice
Write-Host "=== Script de compilation pour le projet Supermatrice ===" -ForegroundColor Green
Write-Host ""

# Verification de la presence de Java
try {
    $javaVersion = java -version 2>&1
    Write-Host "Java detecte: $($javaVersion[0])" -ForegroundColor Green
}
catch {
    Write-Host "Erreur: Java n'est pas installe ou pas dans le PATH" -ForegroundColor Red
    Read-Host "Appuyez sur Entree pour quitter"
    exit 1
}

# Creation du repertoire de sortie s'il n'existe pas
if (-not (Test-Path "bin")) {
    New-Item -ItemType Directory -Path "bin" | Out-Null
    Write-Host "Repertoire 'bin' cre" -ForegroundColor Yellow
}

# Compilation des fichiers Java
Write-Host "Compilation des fichiers Java..." -ForegroundColor Yellow
try {
    javac -d bin src\*.java
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Compilation reussie!" -ForegroundColor Green
        Write-Host ""
        Write-Host "Pour executer le programme de test:" -ForegroundColor Cyan
        Write-Host "  cd bin" -ForegroundColor White
        Write-Host "  java TestSupermatrice" -ForegroundColor White
        Write-Host ""
        
        # Proposition d'executer directement
        $choice = Read-Host "Voulez-vous executer le test maintenant? (o/n)"
        if ($choice -eq "o" -or $choice -eq "O") {
            Write-Host ""
            Write-Host "=== Execution du programme de test ===" -ForegroundColor Green
            Set-Location bin
            java TestSuperMatrice
            Set-Location ..
        }
    }
    else {
        Write-Host "Erreur de compilation!" -ForegroundColor Red
    }
}
catch {
    Write-Host "Erreur lors de la compilation: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host ""
