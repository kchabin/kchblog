package com.kchabin.blog.post

import com.kchabin.blog.comment.Comment
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.time.LocalDateTime

@Entity
class Post(
    @Column(length = 200)
    var title: String,

    @Column(columnDefinition = "TEXT")
    var content: String,

    @Column
    var createDate: LocalDateTime,

    @OneToMany(mappedBy = "post", cascade = [CascadeType.REMOVE])
    var commentList: MutableList<Comment> = mutableListOf(),

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
) {
    //JPA를 위한 기본 생성자
    constructor() : this("", "",LocalDateTime.now())

    //PostService를 위한 생성자
    constructor(title: String, content: String) : this(title=title, content=content, createDate=LocalDateTime.now())
}