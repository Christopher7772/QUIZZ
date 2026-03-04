package com.quiz.model;

import java.util.List;

/**
 * =====================================================
 * Modèle représentant une session de quiz en cours
 * =====================================================
 * Cette classe est stockée en session HTTP pour conserver
 * l'état de la partie d'un joueur :
 *  - son pseudo
 *  - la catégorie choisie
 *  - la liste des questions
 *  - son score actuel
 *  - l'index de la question courante
 */
public class QuizSession {

    /** Pseudo choisi par le joueur */
    private String pseudo;

    /** Catégorie du quiz ("english", "maths", "chemistry") */
    private String category;

    /** Liste des questions pour cette session */
    private List<Question> questions;

    /** Nombre de bonnes réponses accumulées */
    private int score;

    /** Index de la question actuellement affichée (commence à 0) */
    private int currentQuestionIndex;

    // ─────────────────────────────────────────────
    //  Constructeur
    // ─────────────────────────────────────────────

    public QuizSession(String pseudo, String category, List<Question> questions) {
        this.pseudo = pseudo;
        this.category = category;
        this.questions = questions;
        this.score = 0;                  // Score initial : 0
        this.currentQuestionIndex = 0;   // On commence à la première question
    }

    // ─────────────────────────────────────────────
    //  Méthodes utilitaires
    // ─────────────────────────────────────────────

    /**
     * @return la question actuellement en cours
     */
    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    /**
     * @return true si toutes les questions ont été répondues
     */
    public boolean isFinished() {
        return currentQuestionIndex >= questions.size();
    }

    /**
     * Passe à la question suivante
     */
    public void nextQuestion() {
        currentQuestionIndex++;
    }

    /**
     * Incrémente le score d'un point (bonne réponse)
     */
    public void incrementScore() {
        score++;
    }

    /**
     * @return le nombre total de questions dans cette session
     */
    public int getTotalQuestions() {
        return questions.size();
    }

    /**
     * @return le pourcentage de réussite (0 à 100)
     */
    public int getScorePercentage() {
        if (questions.isEmpty()) return 0;
        return (score * 100) / questions.size();
    }

    // ─────────────────────────────────────────────
    //  Getters & Setters
    // ─────────────────────────────────────────────

    public String getPseudo() { return pseudo; }
    public void setPseudo(String pseudo) { this.pseudo = pseudo; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public int getCurrentQuestionIndex() { return currentQuestionIndex; }
    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex;
    }
}
