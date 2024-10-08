package com.kchabin.blog

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class MainController {

    /*@GetMapping("/")
    fun index() : Unit {
        println("Hello World!")
    }*/

    @GetMapping("/blog")
    @ResponseBody
    fun index(): String {
        return "Kchabin's Blog"
    }

    //포스트 목록이 있는 페이지로 바로 리다이렉트
    @GetMapping("/")
    fun root() : String {
        return "redirect:/blog/posts"
    }
}