package com.kchabin.blog.post

import com.kchabin.blog.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class PostController(
    @Autowired
    private val postService: PostService
) {

    @GetMapping("/blog/posts")
    fun blogPosts(model: Model): String {
        var postList: List<Post> = postService.getPosts()
        model.addAttribute("postList", postList)
        return "post_list"
    }

    //post 상세 페이지 매핑
    @GetMapping("/blog/posts/detail/{id}")
    fun detail(model: Model, @PathVariable id: Long): String {
        var post: Post? = postService.getPost(id)
        model.addAttribute("post", post)
        return "post_detail"
    }



}