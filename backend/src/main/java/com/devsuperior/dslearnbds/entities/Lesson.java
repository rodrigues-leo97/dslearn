package com.devsuperior.dslearnbds.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/*
* @Inheritance(strategy = InheritanceType.JOINED) //anotação para quando a classe tem herança, o InheritanceType.JOINED serve para dizer que quero que crie tabelas separadas para cada classe que herdar dela
* se usar outro tipo de strategy então quando eu instanciar uma classe que herda dela ela trará todos os elementos da classe pai e as que as herdam em uma única tabela, e os que não forem preenchidos virão como nullos
* */

@Entity
@Table(name = "tb_lesson")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Lesson implements Serializable { //quando a classe é abstrata ela não pode ser instanciada
    private static final long seriaVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer position;

    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @ManyToMany //sem o fetchType.EAGER eu não vou carregar automaticamente as informações de quem terminou a lição, eu terei que dar um .get()
    @JoinTable(name = "tb_lessons_done",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = { //precisa ser feito dessa forma pq nesse caso na classe Enrollment temos uma chave composta, portanto, precisamos especificar as duas
                @JoinColumn(name = "user_id"),
                @JoinColumn(name = "offer_id")
            })
    private Set<Enrollment> enrollmentsDone = new HashSet<>();

    @OneToMany(mappedBy = "lesson")
    private List<Deliver> deliveries = new ArrayList<>();

    @OneToMany(mappedBy = "lesson")
    private List<Topic> topics = new ArrayList<>();


    public Lesson(Long id, String title, Integer position, Section section) {
        this.id = id;
        this.title = title;
        this.position = position;
        this.section = section;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Set<Enrollment> getEnrollmentsDone() {
        return enrollmentsDone;
    }

    public List<Deliver> getDeliveries() {
        return deliveries;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    //não se coloca o set de coleção


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lesson)) return false;

        Lesson lesson = (Lesson) o;

        return Objects.equals(id, lesson.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
