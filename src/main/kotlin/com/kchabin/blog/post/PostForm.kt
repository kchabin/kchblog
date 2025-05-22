package com.kchabin.blog.post

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


class PostForm(
    @Autowired
    private val postDTO: PostDTO
) {

    @field:NotBlank(message = "제목은 필수항목입니다.")
    @field:Size(max=50)
    private var title: String =""

    @field:NotBlank(message = "내용은 필수항목입니다.")
    @field:Size(max=500)
    private var content: String =""


    fun toPostDTO(): PostDTO {
        return PostDTO(title = title, content = content)
    }
}