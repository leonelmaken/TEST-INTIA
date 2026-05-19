package Projet_INTIA.INTIA.config;

/**
 * ============================================================
 * IMPORTS DES EXCEPTIONS ET OUTILS SPRING
 * ============================================================
 * On importe ici les classes nécessaires pour :
 * - gérer les exceptions (EntityNotFoundException, etc.)
 * - retourner des réponses HTTP (ResponseEntity)
 * - gérer la validation des champs (@Valid)
 * - capturer les erreurs globalement (@RestControllerAdvice)
 */

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * GESTION GLOBALE DES EXCEPTIONS DE L'APPLICATION
 * ============================================================
 *
 * Cette classe agit comme un "intercepteur central" des erreurs.
 *
 * OBJECTIF :
 * - éviter de renvoyer des erreurs techniques brutes au client
 * - standardiser les messages d’erreur JSON
 * - améliorer la lisibilité et la maintenance du backend
 *
 * Exemple :
 * au lieu d'un crash Spring Boot → on renvoie un JSON propre
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Logger utilisé pour afficher les logs dans la console
     *
     * Pourquoi ?
     * - pour suivre les erreurs
     * - pour déboguer facilement
     * - pour garder un historique des problèmes serveur
     */
    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * ============================================================
     * 404 - CAS : ENTITÉ INTROUVABLE EN BASE DE DONNÉES
     * ============================================================
     *
     * Exemple :
     * - client avec ID inexistant
     * - assurance supprimée mais encore demandée
     *
     * Quand cette exception est levée → Spring appelle cette méthode
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(EntityNotFoundException ex) {

        // Je log l'erreur côté serveur pour suivi technique
        log.warn("Ressource introuvable : {}", ex.getMessage());

        // Je renvoie une réponse HTTP 404 propre au client
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /**
     * ============================================================
     * 400 - CAS : ERREUR MÉTIER / LOGIQUE APPLICATIVE
     * ============================================================
     *
     * Exemple :
     * - email déjà utilisé
     * - suppression interdite
     * - règle métier non respectée
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(IllegalArgumentException ex) {

        // On log en WARN car c'est une erreur de logique utilisateur
        log.warn("Erreur métier : {}", ex.getMessage());

        // Retourne une erreur 400 (Bad Request)
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /**
     * ============================================================
     * 400 - CAS : ERREURS DE VALIDATION (@Valid)
     * ============================================================
     *
     * Exemple :
     * - champ email vide
     * - format email invalide
     * - téléphone incorrect
     *
     * Spring capture automatiquement ces erreurs ici
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {

        // Map qui va contenir toutes les erreurs champ par champ
        Map<String, String> erreurs = new HashMap<>();

        // Je parcours toutes les erreurs de validation
        ex.getBindingResult().getAllErrors().forEach(error -> {

            // Nom du champ qui a échoué
            String champ = ((FieldError) error).getField();

            // Message d'erreur associé
            String message = error.getDefaultMessage();

            // Je stocke sous forme clé/valeur
            erreurs.put(champ, message);
        });

        // Log des erreurs pour debugging backend
        log.warn("Erreurs validation détectées : {}", erreurs);

        // Construction de la réponse JSON structurée
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now()); // moment de l'erreur
        body.put("status", HttpStatus.BAD_REQUEST.value()); // code HTTP 400
        body.put("erreurs", erreurs); // détails des erreurs

        // Retourne la réponse au client
        return ResponseEntity.badRequest().body(body);
    }

    /**
     * ============================================================
     * 500 - CAS : ERREUR INTERNE INATTENDUE
     * ============================================================
     *
     * Exemple :
     * - NullPointerException
     * - bug système
     * - erreur non prévue
     *
     * C'est le "filet de sécurité" de l'application
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {

        // Log ERROR car c'est une erreur critique serveur
        log.error("Erreur interne serveur : {}", ex.getMessage(), ex);

        // Message générique pour ne pas exposer les détails sensibles
        return buildError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erreur interne serveur. Veuillez contacter l'administrateur."
        );
    }

    /**
     * ============================================================
     * MÉTHODE UTILITAIRE DE CONSTRUCTION D'ERREUR
     * ============================================================
     *
     * Cette méthode évite la duplication de code.
     *
     * Elle construit une réponse JSON standardisée :
     * - timestamp
     * - status HTTP
     * - message d’erreur
     */
    private ResponseEntity<Map<String, Object>> buildError(HttpStatus status, String message) {

        // Structure de réponse uniforme pour toutes les erreurs
        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now()); // heure de l'erreur
        body.put("status", status.value()); // code HTTP
        body.put("erreur", message); // message lisible

        // Retourne la réponse HTTP avec statut correct
        return ResponseEntity.status(status).body(body);
    }
}