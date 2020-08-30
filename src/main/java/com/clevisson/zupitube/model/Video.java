package com.clevisson.zupitube.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tumbnail_url;
    private String key;
    private String description;
    private String title;
    private Timestamp createdAt;
    private Timestamp updated_at;

    @JsonIgnore
    @OneToMany(
            mappedBy = "video", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Comment> comment;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;

    public Set<Comment> getComment() {
        return comment;
    }

    public void setComment(Set<Comment> comments) {
        this.comment = comments;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTumbnail_url() {
        return tumbnail_url;
    }

    public void setTumbnail_url(String tumbnail_url) {
        this.tumbnail_url = tumbnail_url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String processed) {
        this.description = processed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

}
