package Projet_INTIA.INTIA.response;

import java.time.LocalDateTime;

/**
 * ============================================================
 * RESPONSE GLOBALE API
 * ============================================================
 *
 * Objectif :
 * - Standardiser toutes les réponses REST
 * - Éviter les entités brutes dans les controllers
 * - Faciliter frontend + Swagger
 */
public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(LocalDateTime timestamp, int status, String message, T data) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // ================= GETTERS / SETTERS =================

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // ================= MÉTHODES UTILITAIRES =================

    /**
     * Réponse de succès
     */
    public static <T> ApiResponse<T> success(String message, T data, int status) {
        return new ApiResponse<>(
                LocalDateTime.now(),
                status,
                message,
                data
        );
    }

    /**
     * Réponse d'erreur
     */
    public static <T> ApiResponse<T> error(String message, int status) {
        return new ApiResponse<>(
                LocalDateTime.now(),
                status,
                message,
                null
        );
    }
}