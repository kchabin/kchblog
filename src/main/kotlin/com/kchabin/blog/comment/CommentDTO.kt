package com.kchabin.blog.comment

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDateTime


data class CommentDTO(
    val id: Long? = null,

    @NotBlank(message = "내용은 필수항목입니다.")
    @field:Size(max=500)
    val content: String,

    val postId: Long? = null,  // Post의 ID만 참조 (순환 참조 방지)
    val createdAt: LocalDateTime = LocalDateTime.now()
) {


}