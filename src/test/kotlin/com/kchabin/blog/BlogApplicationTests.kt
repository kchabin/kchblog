package com.kchabin.blog

import com.kchabin.blog.comment.Comment
import com.kchabin.blog.post.Post
import com.kchabin.blog.repository.CommentRepository
import com.kchabin.blog.repository.PostRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.util.*

@SpringBootTest
class BlogApplicationTests {

	//TODO : @BeforeAll, @AfterAll, @Transactional 등 사용해서 테스트 코드 다시 작성해보기

	/*
	@Autowired
	private lateinit var commentRepository: CommentRepository

	@Autowired
	private  lateinit var postRepository: PostRepository


	@Test
	fun testJpa() {
		var all: List<Post> = postRepository.findAll().toList()
		assertEquals(2, all.size)

		var p: Post = all.get(0)
		assertEquals("title3", p.title)
	}

	@Test
	fun `findById`() {

		var op: Optional<Post> = postRepository.findById(3)

		if(op.isPresent){
			var p = op.get()
			assertEquals("title3", p.title)
			println("title3 ${p.title}")
		}
	}

	@Test
	fun `findByTitle`() {
		var p = postRepository.findByTitle("title3")
		assertEquals(3, p?.id)
		println("title1의 id = ${p?.id}")
	}

	@Test
	fun `findByTitleAndContent`() {
		var p = postRepository.findByTitleAndContent("title3", "세번째 포스트")
		assertEquals(3, p?.id)
	}

	@Test
	fun `findByTitleLike`() {
		var pList = postRepository.findByTitleLike("title%")
		var p: Post? = pList[0]
		assertEquals("title3", p?.title)
	}
 */

//	@Test
//	fun `updatePost`() {
//		var op: Optional<Post> = postRepository.findById(5)
//		assertTrue(op.isPresent) //괄호 안의 값이 참인지 테스트
//		var p: Post = op.get()
//		p.content = "다섯번째 포스트의 콘텐츠"
//		postRepository.save(p)
//	}

//	@Test
//	fun `deletePost`() {
//		var op: Optional<Post> = postRepository.findById(5)
//		assertTrue(op.isPresent)
//		var p: Post = op.get()
//		postRepository.delete(p)
//		assertEquals(5, postRepository.count())
//	}

	//@Test
	//fun `createComment`() {
	//	var op: Optional<Post> = postRepository.findById(4)
	//	assertTrue(op.isPresent)
	//	var p: Post = op.get()
	//
	//	var c = Comment("4번째 포스트입니다.", LocalDateTime.now(), p)
	//	commentRepository.save(c)
	//}

	@Test
	fun `findCommentByTitle` () {
		var op: Optional<Post> = postRepository.findById(3)

		assertTrue(op.isPresent) //id가 3인 포스트가 존재하는지
		var p: Post = op.get()

		var commentList: List<Comment> = commentRepository.findAll().toList()
		assertEquals(22, commentList.size) //총 댓글 갯수는 17개여야 함
		assertEquals("ss", commentList.get(3).content) //댓글 중 4번째의 content 획득
	}
}
