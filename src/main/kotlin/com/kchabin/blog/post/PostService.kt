package com.kchabin.blog.post

import com.kchabin.blog.DataNotFoundException
import com.kchabin.blog.repository.PostRepository
import jakarta.persistence.Entity
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*


@Service
class PostService(
    val postRepository: PostRepository
) {
    fun getPosts(): List<Post> {
        return postRepository.findAll()
    }

    fun getPost(id: Long): Post {
        var post: Optional<Post> = postRepository.findById(id)

        if (post.isPresent) {
            return post.get()
        }
        else throw EntityNotFoundException ("Post is not found")

    }

    fun posting(postDTO: PostDTO): Long?{
        var post = Post(title=postDTO.title, content = postDTO.content)
        postRepository.save(post)
        return post.id
    }

}