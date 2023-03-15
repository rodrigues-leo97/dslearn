package com.devsuperior.dslearnbds.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_content")
public class Content extends Lesson { //quando a classe é abstrata ela não pode ser instanciada
    private static final long seriaVersionUID = 1L;

    private String contextContent;
    private String videoUri;

    public Content(Long id, String title, Integer position, Section section, String contextContent, String videoUri) { //esse construtor recebeu os atributos de herança da classe Lesson
        super(id, title, position, section);
        this.contextContent = contextContent;
        this.videoUri = videoUri;
    }

    public String getContextContent() {
        return contextContent;
    }

    public void setContextContent(String contextContent) {
        this.contextContent = contextContent;
    }

    public String getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }

    //não precisa de equals e hashcode pq já está herdando da classe de Lesson
}
