package com.kchabin.blog.repository

import com.kchabin.blog.post.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {
    fun findByTitle(title: String): Post?
    fun findByTitleAndContent(title: String, content: String): Post?
    fun findByTitleLike(title: String): List<Post>
}