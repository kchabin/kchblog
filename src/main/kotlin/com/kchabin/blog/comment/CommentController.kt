package com.kchabin.blog.comment

import com.kchabin.blog.post.Post
import com.kchabin.blog.post.PostDTO
import com.kchabin.blog.post.PostService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDateTime

@RequestMapping("/comment")
//@RequiredArgsConstructor
@Controller
class CommentController(
    private val commentService: CommentService,
    private val postService: PostService
){

    //답글 작성
//    @PostMapping("/create/{id}")
//    fun createComment(model: Model,
//                      @PathVariable id: Long,
//                      @Valid @RequestParam(value="content") content: String,
//                      bindingResult: BindingResult): Any {
//        val postDTO: PostDTO = postService.getPost(id) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found")
//
//        // CommentDTO 생성
//        val commentDTO = CommentDTO(
//            content = content,
//            createdAt = LocalDateTime.now(),
//            postId = id //postId로 현재 Post의 id 사용
//        )
//
//        if(bindingResult.hasErrors()) {
//            //content만 검증하면 될텐데..
//            return "post_detail"
//        }
//
//        //var comment: CommentDTO = commentService.getComments(postDTO.id)
//
//        commentService.createComment(commentDTO)
//
//        //리다이렉트 URL 설정
//        val redirectUrl = String.format("redirect:/post/detail/%s", id)
//        return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl).body("Post is found")
//    }

    @PostMapping("/create/{id}")
    fun createComment(
        model: Model,
        @PathVariable id: Long,
        @RequestParam(value="content") content: String, ): Any {
        val postDTO: PostDTO = postService.getPost(id) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found")

        // CommentDTO 생성
        val commentDTO = CommentDTO(
            content = content,
            createdAt = LocalDateTime.now(),
            postId = id //postId로 현재 Post의 id 사용
        )



        //var comment: CommentDTO = commentService.getComments(postDTO.id)

        commentService.createComment(commentDTO)

        //리다이렉트 URL 설정
        val redirectUrl = String.format("redirect:/post/detail/%s", id)
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl).body("Post is found")
    }
}