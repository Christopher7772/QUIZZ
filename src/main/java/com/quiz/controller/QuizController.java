package com.quiz.controller;

import com.quiz.model.Question;
import com.quiz.model.QuizSession;
import com.quiz.service.QuizService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@Controller
public class QuizController {

    private static final String SESSION_KEY = "quizSession";

    // Lettres A B C D pour les options de réponse
    private static final List<String> LETTERS = Arrays.asList("A", "B", "C", "D");

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/start")
    public String startQuiz(@RequestParam String pseudo,
                             @RequestParam String category,
                             HttpSession httpSession) {
        if (pseudo == null || pseudo.trim().isEmpty()) {
            return "redirect:/";
        }
        QuizSession qs = quizService.createSession(pseudo.trim(), category);
        httpSession.setAttribute(SESSION_KEY, qs);
        return "redirect:/question";
    }

    @GetMapping("/question")
    public String showQuestion(Model model, HttpSession httpSession) {
        QuizSession qs = (QuizSession) httpSession.getAttribute(SESSION_KEY);
        if (qs == null) return "redirect:/";
        if (qs.isFinished()) return "redirect:/result";

        // CORRECTION : on utilise "quizSession" et non "session" (mot réservé Thymeleaf)
        model.addAttribute("quizSession", qs);
        model.addAttribute("question", qs.getCurrentQuestion());
        model.addAttribute("questionNumber", qs.getCurrentQuestionIndex() + 1);
        model.addAttribute("letters", LETTERS);
        return "quiz";
    }

    @PostMapping("/answer")
    public String submitAnswer(@RequestParam int answerIndex,
                                Model model,
                                HttpSession httpSession) {
        QuizSession qs = (QuizSession) httpSession.getAttribute(SESSION_KEY);
        if (qs == null) return "redirect:/";

        Question currentQuestion = qs.getCurrentQuestion();
        boolean correct = quizService.submitAnswer(qs, answerIndex);

        // CORRECTION : "quizSession" au lieu de "session"
        model.addAttribute("correct", correct);
        model.addAttribute("question", currentQuestion);
        model.addAttribute("chosenAnswerIndex", answerIndex);
        model.addAttribute("quizSession", qs);
        model.addAttribute("finished", qs.isFinished());
        return "feedback";
    }

    @GetMapping("/result")
    public String showResult(Model model, HttpSession httpSession) {
        QuizSession qs = (QuizSession) httpSession.getAttribute(SESSION_KEY);
        if (qs == null) return "redirect:/";

        int percentage = qs.getScorePercentage();
        String message = quizService.getScoreMessage(percentage);

        // CORRECTION : "quizSession" au lieu de "session"
        model.addAttribute("quizSession", qs);
        model.addAttribute("percentage", percentage);
        model.addAttribute("message", message);

        httpSession.removeAttribute(SESSION_KEY);
        return "result";
    }
}