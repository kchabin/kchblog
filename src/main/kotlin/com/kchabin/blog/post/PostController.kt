package com.kchabin.blog.post

import com.kchabin.blog.repository.PostRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PostController(private val postRepository: PostRepository) {

    @GetMapping("/blog/posts")
    fun blogPosts(model: Model): String {
        var postList: List<Post> = postRepository.findAll().toList()
        model.addAttribute("postList", postList)
        return "post_list"
    }


}