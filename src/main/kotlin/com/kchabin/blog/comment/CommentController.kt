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

@RequestMapping("/comment")
@Controller
class CommentController(
    private val commentService: CommentService,
    private val postService: PostService
){

    @PostMapping("/create/{id}")
    fun createComment(model: Model, @PathVariable id: Long, @RequestParam(value="content") content: String): ResponseEntity<String> {
        val post: PostDTO = postService.getPost(id)

        //if(post == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found")

        //TODO : 답변 저장


        val redirectUrl = String.format("redirect:/post/detail/%s", id)
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl).body("Post is found")
    }
}