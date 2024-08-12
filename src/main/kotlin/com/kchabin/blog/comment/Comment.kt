package com.kchabin.blog.comment

import com.kchabin.blog.post.Post
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
class Comment(
    @Column(columnDefinition = "TEXT")
    var content: String,

    @Column
    var createdAt: LocalDateTime,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    var post: Post,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
) {
    constructor() : this("", LocalDateTime.now(), Post()) {}

    constructor(content: String, createdAt: LocalDateTime) : this(content=content, createdAt = LocalDateTime.now(), post=Post()) {}
}