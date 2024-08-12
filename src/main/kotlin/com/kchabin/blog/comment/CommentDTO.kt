package com.kchabin.blog.comment

import java.time.LocalDateTime


data class CommentDTO(
    val id: Long? = null,
    val content: String,
    val postId: Long? = null,  // Post의 ID만 참조 (순환 참조 방지)
    val createdAt: LocalDateTime = LocalDateTime.now()
) {


}