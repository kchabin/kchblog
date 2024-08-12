package com.kchabin.blog.post

import com.kchabin.blog.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/post") //URL 프리픽스
class PostController(
    @Autowired
    private val postService: PostService
) {

    @GetMapping("/list")
    fun blogPosts(model: Model): String {
        var postList: List<Post> = postService.getPosts()
        model.addAttribute("postList", postList)
        return "post_list"
    }

    //post 상세 페이지 매핑
    @GetMapping("/detail/{id}")
    fun detail(model: Model, @PathVariable id: Long): String {
        var post: Post? = postService.getPost(id)
        model.addAttribute("post", post)
        return "post_detail"
    }

    //@GetMapping("/posts/create")
    //fun create(){
    //    return "post_create"
    //}



}