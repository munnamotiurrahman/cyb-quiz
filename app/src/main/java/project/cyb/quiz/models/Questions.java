package project.cyb.quiz.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty
    private String question;

    @Column
    private String option1;

    @Column
    private String option2;

    @Column
    private String option3;

    @Column
    private String option4;

    @Column
    private Integer correctOption;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return this.option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return this.option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return this.option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return this.option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public Integer getCorrectOption() {
        return this.correctOption;
    }

    public void setCorrectOption(Integer correctOption) {
        this.correctOption = correctOption;
    }
}
