package com.kchabin.blog.comment

import com.kchabin.blog.post.Post
import com.kchabin.blog.post.PostDTO
import com.kchabin.blog.post.PostService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDateTime

@RequestMapping("/comment")
@Controller
class CommentController(
    private val commentService: CommentService,
    private val postService: PostService
){

    //답글 작성
    @PostMapping("/create/{id}")
    fun createComment(model: Model, @PathVariable id: Long, @RequestParam(value="content") content: String): ResponseEntity<String> {
        val postDTO: PostDTO = postService.getPost(id) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found")

        // CommentDTO 생성
        val commentDTO = CommentDTO(
            content = content,
            createdAt = LocalDateTime.now(),
            postId = id //postId로 현재 Post의 id 사용
        )

        commentService.createComment(commentDTO)

        //리다이렉트 URL 설정
        val redirectUrl = String.format("redirect:/post/detail/%s", id)
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl).body("Post is found")
    }
}