package com.devsuperior.dslearnbds.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_content")
public class Content extends Lesson { //quando a classe é abstrata ela não pode ser instanciada
    private static final long seriaVersionUID = 1L;

    private String textContent;
    private String videoUri;

    public Content(Long id, String title, Integer position, Section section, String textContent, String videoUri) { //esse construtor recebeu os atributos de herança da classe Lesson
        super(id, title, position, section);
        this.textContent = textContent;
        this.videoUri = videoUri;
    }

    public String gettextContent() {
        return textContent;
    }

    public void settextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }

    //não precisa de equals e hashcode pq já está herdando da classe de Lesson
}
