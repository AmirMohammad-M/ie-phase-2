package ir.ac.sbu.ie.project2.Phase2.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_case")
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "case_id")
    private int id;

    @ManyToOne
    @JoinColumn(name="auth_user_id", nullable=false)
    private User user;

    @NotNull(message="Content field can't be empty.")
    @Column(name = "case_content")
    private String content;

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
