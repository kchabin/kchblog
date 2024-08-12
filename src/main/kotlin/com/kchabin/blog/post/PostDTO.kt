package com.kchabin.blog.post

import com.kchabin.blog.comment.Comment
import com.kchabin.blog.comment.CommentDTO
import java.time.LocalDateTime

data class PostDTO(
    val id: Long? = null,
    val title: String,
    val content: String,
    val createDate: LocalDateTime = LocalDateTime.now(),
    val comments: List<CommentDTO> = listOf()  // 연관된 CommentDTO 리스트
) {




}