package com.kchabin.blog.post

import com.kchabin.blog.repository.PostRepository
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/post") //URL 프리픽스
class PostController(
    @Autowired
    private val postService: PostService

) {

    @GetMapping("/list")
    fun blogPosts(model: Model): String {
        var postList: List<PostDTO> = postService.getPosts()
        model.addAttribute("postList", postList)
        return "post_list" //post_list.html
    }

    //post 상세 페이지 매핑
    @GetMapping("/detail/{id}")
    fun detail(model: Model, @PathVariable id: Long): String {
        var post: PostDTO = postService.getPost(id)
        model.addAttribute("post", post)
        return "post_detail" //post_detail.html
    }

    //post 등록
    @GetMapping("/new")
    fun createPost(): String {
        return "post_form"
    }
    //매개변수로 바인딩한 객체는 `Model` 객체로 전달하지 않아도 템플릿에서 사용 가능함


    @PostMapping("/new")
    fun createPost(@ModelAttribute @Valid postDTO: PostDTO, bindingResult: BindingResult): String {

        if (bindingResult.hasErrors()) {
            return "post_form"
        }
        postService.create(postDTO)
        //새 글 등록 후 리스트로 리다이렉트
        return "redirect:/post/list"
    }

//    @PostMapping("/save")
//    fun createPost(@RequestParam(value="title") title: String, @RequestParam(value = "content") content: String): String {
//
//        var postId = postService.create(title, content)
//        //새 글 등록 후 리스트로 리다이렉트
//        return "redirect:/post/list"
//    }





}