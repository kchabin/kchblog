package com.kchabin.blog.comment

import com.kchabin.blog.repository.CommentRepository
import com.kchabin.blog.repository.PostRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository,
    //private val postService: PostService
) {

    //엔티티를 직접 반환하는 코드
    /*fun getComments(postId: Int): List<Comment> {
        return commentRepository.findAll()
    }*/

    //DTO 사용
    fun getComments(postId: Long): List<CommentDTO>{
        val comments = commentRepository.findByPostId(postId)
        return comments.map { comment -> convertToCommentDTO(comment)}
    }
    fun convertToCommentDTO(comment: Comment): CommentDTO {
        return CommentDTO(
            id = comment.id,
            content = comment.content,
            createdAt = comment.createdAt,
            postId = comment.post?.id // 순환 참조 방지: Post 자체가 아니라 Post의 ID만 포함
        )
    }

    fun createComment(commentDTO: CommentDTO): Long? {
        val post = postRepository.findById(commentDTO.postId ?: throw IllegalArgumentException("Post ID cannot be null") )
            .orElseThrow { IllegalArgumentException("Post ID ${commentDTO.postId} not found") }

        val comment = Comment(
            id = commentDTO.id,
            content=commentDTO.content,
            createdAt = commentDTO.createdAt,
            post = post
            )
        commentRepository.save(comment)
        return comment.id
    }
}