 package com.quiz.service;

import com.quiz.model.Question;
import com.quiz.model.QuizSession;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * =====================================================
 * Service principal du Quiz
 * =====================================================
 * Cette classe contient toute la logique métier :
 *  - la banque de questions par catégorie
 *  - la création de sessions de jeu
 *  - le traitement des réponses
 *
 * @Service indique à Spring qu'il doit gérer cette classe
 * comme un composant réutilisable (bean singleton).
 */
@Service
public class QuizService {

    /**
     * Nombre de questions posées par partie
     * (tirées aléatoirement depuis la banque)
     */
    private static final int QUESTIONS_PER_GAME = 5;

    // ─────────────────────────────────────────────
    //  BANQUE DE QUESTIONS - ANGLAIS
    // ─────────────────────────────────────────────

    /** Liste complète des questions d'anglais disponibles */
    private final List<Question> englishQuestions = new ArrayList<>(Arrays.asList(

        new Question(
            "What is the past tense of 'go'?",
            Arrays.asList("Goed", "Went", "Gone", "Going"),
            1, "english"
        ),
        new Question(
            "Which word is a synonym for 'happy'?",
            Arrays.asList("Sad", "Angry", "Joyful", "Tired"),
            2, "english"
        ),
        new Question(
            "Choose the correct sentence:",
            Arrays.asList("She don't like cats", "She doesn't likes cats",
                          "She doesn't like cats", "She not like cats"),
            2, "english"
        ),
        new Question(
            "What does 'enormous' mean?",
            Arrays.asList("Very small", "Very fast", "Very loud", "Very large"),
            3, "english"
        ),
        new Question(
            "Which is the correct plural of 'child'?",
            Arrays.asList("Childs", "Childes", "Children", "Childrens"),
            2, "english"
        ),
        new Question(
            "What part of speech is the word 'quickly'?",
            Arrays.asList("Noun", "Adjective", "Adverb", "Verb"),
            2, "english"
        ),
        new Question(
            "Which sentence is in the future tense?",
            Arrays.asList("I ate dinner", "I am eating dinner",
                          "I will eat dinner", "I eat dinner"),
            2, "english"
        ),
        new Question(
            "What is the antonym of 'ancient'?",
            Arrays.asList("Old", "Modern", "Historic", "Traditional"),
            1, "english"
        ),
        new Question(
            "Which word correctly completes: 'She is ___ honest person'?",
            Arrays.asList("a", "an", "the", "some"),
            1, "english"
        ),
        new Question(
            "What does the prefix 'un-' mean in 'unhappy'?",
            Arrays.asList("Very", "Again", "Not", "Before"),
            2, "english"
        )
    ));

    // ─────────────────────────────────────────────
    //  BANQUE DE QUESTIONS - MATHÉMATIQUES
    // ─────────────────────────────────────────────

    /** Liste complète des questions de mathématiques disponibles */
    private final List<Question> mathsQuestions = new ArrayList<>(Arrays.asList(

        new Question(
            "What is the value of π (pi) rounded to 2 decimal places?",
            Arrays.asList("3.12", "3.14", "3.16", "3.18"),
            1, "maths"
        ),
        new Question(
            "What is 12 × 12?",
            Arrays.asList("132", "140", "144", "148"),
            2, "maths"
        ),
        new Question(
            "What is the square root of 144?",
            Arrays.asList("10", "11", "12", "13"),
            2, "maths"
        ),
        new Question(
            "Solve: 2x + 6 = 14. What is x?",
            Arrays.asList("3", "4", "5", "6"),
            1, "maths"
        ),
        new Question(
            "What is 25% of 200?",
            Arrays.asList("25", "40", "50", "75"),
            2, "maths"
        ),
        new Question(
            "What is the area of a rectangle with length 8 and width 5?",
            Arrays.asList("26", "30", "38", "40"),
            3, "maths"
        ),
        new Question(
            "How many sides does a hexagon have?",
            Arrays.asList("4", "5", "6", "8"),
            2, "maths"
        ),
        new Question(
            "What is 3³ (3 to the power of 3)?",
            Arrays.asList("6", "9", "18", "27"),
            3, "maths"
        ),
        new Question(
            "What is the sum of angles in a triangle?",
            Arrays.asList("90°", "180°", "270°", "360°"),
            1, "maths"
        ),
        new Question(
            "What is the LCM of 4 and 6?",
            Arrays.asList("10", "12", "16", "24"),
            1, "maths"
        )
    ));

    // ─────────────────────────────────────────────
    //  BANQUE DE QUESTIONS - CHIMIE
    // ─────────────────────────────────────────────

    /** Liste complète des questions de chimie disponibles */
    private final List<Question> chemistryQuestions = new ArrayList<>(Arrays.asList(

        new Question(
            "What is the chemical symbol for water?",
            Arrays.asList("WA", "H2O", "HO2", "W2O"),
            1, "chemistry"
        ),
        new Question(
            "What is the atomic number of Carbon?",
            Arrays.asList("4", "6", "8", "12"),
            1, "chemistry"
        ),
        new Question(
            "Which gas makes up most of Earth's atmosphere?",
            Arrays.asList("Oxygen", "Carbon dioxide", "Hydrogen", "Nitrogen"),
            3, "chemistry"
        ),
        new Question(
            "What is the chemical formula for table salt?",
            Arrays.asList("KCl", "NaCl", "CaCl2", "MgCl2"),
            1, "chemistry"
        ),
        new Question(
            "What is the pH of pure water?",
            Arrays.asList("0", "5", "7", "14"),
            2, "chemistry"
        ),
        new Question(
            "Which element has the chemical symbol 'Au'?",
            Arrays.asList("Silver", "Aluminum", "Gold", "Copper"),
            2, "chemistry"
        ),
        new Question(
            "What type of bond involves sharing of electrons?",
            Arrays.asList("Ionic bond", "Metallic bond", "Hydrogen bond", "Covalent bond"),
            3, "chemistry"
        ),
        new Question(
            "What is the chemical formula for carbon dioxide?",
            Arrays.asList("CO", "CO2", "C2O", "CO3"),
            1, "chemistry"
        ),
        new Question(
            "How many electrons can the first electron shell hold?",
            Arrays.asList("1", "2", "4", "8"),
            1, "chemistry"
        ),
        new Question(
            "What is the process where a solid turns directly into a gas?",
            Arrays.asList("Evaporation", "Condensation", "Sublimation", "Fusion"),
            2, "chemistry"
        )
    ));

    // ─────────────────────────────────────────────
    //  MÉTHODES PUBLIQUES
    // ─────────────────────────────────────────────

    /**
     * Crée une nouvelle session de quiz pour un joueur.
     * Les questions sont tirées aléatoirement depuis la bonne banque.
     *
     * @param pseudo   le pseudo du joueur
     * @param category la catégorie choisie ("english", "maths", "chemistry")
     * @return une nouvelle QuizSession prête à démarrer
     */
    public QuizSession createSession(String pseudo, String category) {
        // Récupère la liste de questions correspondant à la catégorie
        List<Question> allQuestions = getQuestionsByCategory(category);

        // Mélange la liste pour avoir un ordre aléatoire à chaque partie
        List<Question> shuffled = new ArrayList<>(allQuestions);
        Collections.shuffle(shuffled);

        // On ne garde que QUESTIONS_PER_GAME questions
        List<Question> selected = shuffled.subList(0, Math.min(QUESTIONS_PER_GAME, shuffled.size()));

        // Crée et retourne la session avec ces questions
        return new QuizSession(pseudo, category, new ArrayList<>(selected));
    }

    /**
     * Traite la réponse du joueur pour la question actuelle.
     * Met à jour le score si la réponse est correcte.
     *
     * @param session     la session en cours
     * @param answerIndex l'index de la réponse choisie (0-3)
     * @return true si la réponse était correcte, false sinon
     */
    public boolean submitAnswer(QuizSession session, int answerIndex) {
        // Récupère la question actuelle avant de passer à la suivante
        Question currentQuestion = session.getCurrentQuestion();

        // Vérifie si la réponse est correcte
        boolean correct = currentQuestion.isCorrect(answerIndex);

        // Incrémente le score uniquement si bonne réponse
        if (correct) {
            session.incrementScore();
        }

        // Avance à la question suivante
        session.nextQuestion();

        return correct;
    }

    /**
     * Retourne un message d'évaluation selon le score obtenu.
     *
     * @param percentage le pourcentage de bonnes réponses (0-100)
     * @return un message motivant ou félicitant le joueur
     */
    public String getScoreMessage(int percentage) {
        if (percentage == 100) return "🏆 Perfect score! You're a genius!";
        if (percentage >= 80)  return "🌟 Excellent! Very well done!";
        if (percentage >= 60)  return "👍 Good job! Keep practising!";
        if (percentage >= 40)  return "📚 Not bad, but you can do better!";
        return "💪 Keep studying, you'll improve!";
    }

    /**
     * Retourne la liste de questions correspondant à la catégorie donnée.
     *
     * @param category la catégorie ("english", "maths", "chemistry")
     * @return la liste des questions de cette catégorie
     * @throws IllegalArgumentException si la catégorie est inconnue
     */
    private List<Question> getQuestionsByCategory(String category) {
        return switch (category.toLowerCase()) {
            case "english"   -> englishQuestions;
            case "maths"     -> mathsQuestions;
            case "chemistry" -> chemistryQuestions;
            default -> throw new IllegalArgumentException("Catégorie inconnue : " + category);
        };
    }
}
