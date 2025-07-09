@echo off
echo === Script de compilation pour le projet Supermatrice ===
echo.

:: Verification de la presence de Java
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Erreur: Java n'est pas installe ou pas dans le PATH
    pause
    exit /b 1
)

:: Creation du repertoire de sortie s'il n'existe pas
if not exist "bin" mkdir bin

:: Compilation des fichiers Java
echo Compilation des fichiers Java...
javac -d bin src\*.java

if %errorlevel% equ 0 (
    echo Compilation reussie!
    echo.
    echo Pour executer le programme de test:
    echo cd bin
    echo java TestSupermatrice
    echo.
    
    :: Proposition d'executer directement
    set /p choice="Voulez-vous executer le test maintenant? (o/n): "
    if /i "%choice%"=="o" (
        echo.
        echo === Execution du programme de test ===
        cd bin
        java TestSupermatrice
        cd ..
    )
) else (
    echo Erreur de compilation!
)

echo.
pause
