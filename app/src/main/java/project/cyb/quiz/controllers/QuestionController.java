package project.cyb.quiz.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.cyb.quiz.models.Questions;
import project.cyb.quiz.service.QuestionService;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/addQuestion")
    public String addQuestion() {
        return "question/add-question";
    }

    @PostMapping("/addQuestion")
    public String addQuestionPost(
            @RequestParam(name = "question", required = true, defaultValue = "") String question,
            @RequestParam(name = "option1", required = true, defaultValue = "") String option1,
            @RequestParam(name = "option2", required = true, defaultValue = "") String option2,
            @RequestParam(name = "option3", required = true, defaultValue = "") String option3,
            @RequestParam(name = "option4", required = true, defaultValue = "") String option4,
            @RequestParam(name = "correctOption", required = true, defaultValue = "-1") String correctOption)

            throws IOException {

        Questions questions = new Questions();
        questions.setQuestion(question);
        questions.setOption1(option1);
        questions.setOption2(option2);
        questions.setOption3(option3);
        questions.setOption4(option4);
        questions.setCorrectOption(Integer.parseInt(correctOption));
        questionService.save(questions);
        return "redirect:/showQuestions";
    }

    @GetMapping("/showQuestions")
    public String showAllQuestions(Model model) {
        List<Questions> questions = questionService.findAll();
        model.addAttribute("questions", questions);
        return "question/show-questions";
    }

    @GetMapping("/startQuiz")
    public String startQuiz(Model model,
            @RequestParam(name = "questionNo", required = true, defaultValue = "-1") Integer questionNo) {
        List<Questions> questions = questionService.findAll();
        questionNo++;
        if (questionNo >= questions.size()) {
            return "redirect:/";
        }
        model.addAttribute("question", questions.get(questionNo));
        model.addAttribute("questionNo", questionNo);
        return "question/start-quiz";
    }

    @PostMapping("/startQuiz")
    public String startQuizPost(Model model,
            @RequestParam(name = "questionNo", required = true, defaultValue = "0") Integer questionNo,
            @RequestParam(name = "selectedOption", required = true, defaultValue = "0") Integer selectedOption) {
        List<Questions> questions = questionService.findAll();
        if (selectedOption == questions.get(questionNo).getCorrectOption()) {
            System.out.println("Your answer is right");
        } else {
            System.out.println("Your answer is wrong, you selected " + selectedOption);
            System.out.println("Question was" + questions.get(questionNo).getQuestion());
            System.out.println("Correct option was" + questions.get(questionNo).getCorrectOption());

        }
        questionNo++;
        if (questionNo >= questions.size()) {
            return "redirect:/";
        }
        model.addAttribute("question", questions.get(questionNo));
        model.addAttribute("questionNo", questionNo);
        return "question/start-quiz";
    }

    @GetMapping("editQuestion/{id}")
    public String editQuestion(@PathVariable Long id, Model model) {
        Optional<Questions> questionOptional = questionService.findById(id);
        model.addAttribute("question", questionOptional.get());
        return "question/edit-question";
    }

    @PostMapping("editQuestion/{id}")
    public String editQuestionPost(
            @RequestParam(name = "id", required = true, defaultValue = "-1") Long id,
            @RequestParam(name = "question", required = true, defaultValue = "") String questionValue,
            @RequestParam(name = "option1", required = true, defaultValue = "") String option1,
            @RequestParam(name = "option2", required = true, defaultValue = "") String option2,
            @RequestParam(name = "option3", required = true, defaultValue = "") String option3,
            @RequestParam(name = "option4", required = true, defaultValue = "") String option4,
            @RequestParam(name = "correctOption", required = true, defaultValue = "-1") String correctOption,
            Model model)
            throws IOException {

        Questions question = new Questions();
        question.setId(id);
        question.setQuestion(questionValue);
        question.setOption1(option1);
        question.setOption2(option2);
        question.setOption3(option3);
        question.setOption4(option4);
        question.setCorrectOption(Integer.parseInt(correctOption));
        questionService.save(question);

        List<Questions> questions = questionService.findAll();
        model.addAttribute("questions", questions);
        return "redirect:/showQuestions";
    }

    @GetMapping("deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable Long id, Model model) {
        questionService.deleteById(id);
        return "redirect:/showQuestions";
    }

}
