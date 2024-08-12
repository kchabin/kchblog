package com.kchabin.blog.post

import com.kchabin.blog.DataNotFoundException
import com.kchabin.blog.comment.Comment
import com.kchabin.blog.comment.CommentDTO
import com.kchabin.blog.comment.CommentService
import com.kchabin.blog.repository.PostRepository
import jakarta.persistence.Entity
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*


@Service
class PostService(
    private val postRepository: PostRepository,
    private val commentService: CommentService
) {


    //entity  직접 반환
    /*fun getPosts(): List<Post> {
        return postRepository.findAll()
    }*/
    //DTO
    fun getPosts() : List<PostDTO> {
        val posts = postRepository.findAll() //모든 post 엔티티를 가져옴
        return posts.map { post -> convertToPostDTO(post)} //각 Post를 PostDTO로 변환
    }

    /*fun getPost(id: Long): Post {
        var post: Optional<Post> = postRepository.findById(id)

        if (post.isPresent) {
            return post.get()
        }
        else throw EntityNotFoundException ("Post is not found")

    }*/

    //DTO
    fun getPost(id: Long): PostDTO {
        val post = postRepository.findById(id).orElse(null) ?: throw IllegalArgumentException("Post is not found")
        val comments = post.commentList.map { commentService.convertToCommentDTO(it)}
        return PostDTO(
            id = post.id,
            title = post.title,
            content = post.content,
            createDate = post.createDate
        )
    }

    fun posting(postDTO: PostDTO): Long?{
        var post = Post(title=postDTO.title, content = postDTO.content)
        postRepository.save(post)
        return post.id
    }

    fun convertToPostDTO(post: Post): PostDTO {
        val commentDTOs = post.commentList.map { commentService.convertToCommentDTO(it) }
        return PostDTO(post.id, post.title, post.content, post.createDate)
    }




}