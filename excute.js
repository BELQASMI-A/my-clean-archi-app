/**
 * SCRIPT DE DÉCOUVERTE : Liste toutes les méthodes disponibles sur l'objet Admin
 * directement depuis l'environnement Gargantua.
 */
function discoverAdmin() {
    System.log("🔎 Analyse de l'objet Admin en cours...", "INFO");
    
    try {
        var clazz = Admin.getClass();
        var methods = clazz.getMethods();
        
        System.log("✅ Classe détectée : " + clazz.getName(), "INFO");
        System.log("📜 Liste des méthodes publiques :", "INFO");

        for (var i = 0; i < methods.length; i++) {
            var m = methods[i];
            var mName = m.getName();
            
            // Extraction des types de paramètres
            var params = [];
            var pTypes = m.getParameterTypes();
            for (var j = 0; j < pTypes.length; j++) {
                params.push(pTypes[j].getSimpleName());
            }

            // Affichage formaté
            System.log("   👉 Admin." + mName + "(" + params.join(", ") + ")", "INFO");
        }
        
        System.log("🏁 Fin de l'analyse. Nombre de méthodes : " + methods.length, "INFO");
        
    } catch (e) {
        System.log("❌ Erreur lors de l'analyse : " + e.message, "ERROR");
    }
}

// Lancement immédiat
discoverAdmin();
