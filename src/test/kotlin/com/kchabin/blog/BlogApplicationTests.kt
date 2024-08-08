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

	@Autowired
	private lateinit var commentRepository: CommentRepository

	@Autowired
	private  lateinit var postRepository: PostRepository


	@Test
	fun testJpa() {
		var all: List<Post> = postRepository.findAll().toList()
		assertEquals(3, all.size)

		var p: Post = all.get(0)
		assertEquals("title1", p.title)
	}

	@Test
	fun `findById`() {

		var op: Optional<Post> = postRepository.findById(1)

		if(op.isPresent){
			var p = op.get()
			assertEquals("title1", p.title)
			println("title1 ${p.title}")
		}
	}

	@Test
	fun `findByTitle`() {
		var p = postRepository.findByTitle("title1")
		assertEquals(1, p?.id)
		println("title1의 id = ${p?.id}")
	}

	@Test
	fun `findByTitleAndContent`() {
		var p = postRepository.findByTitleAndContent("title1", "첫번째 포스트")
		assertEquals(1, p?.id)
	}

	@Test
	fun `findByTitleLike`() {
		var pList = postRepository.findByTitleLike("title%")
		var p: Post? = pList[0]
		assertEquals("title1", p?.title)
	}

	@Test
	fun `updatePost`() {
		var op: Optional<Post> = postRepository.findById(1)
		assertTrue(op.isPresent) //괄호 안의 값이 참인지 테스트
		var p: Post = op.get()
		p.content = "첫번째 포스트의 콘텐츠"
		postRepository.save(p)
	}

	@Test
	fun `deletePost`() {
		var op: Optional<Post> = postRepository.findById(2)
		assertTrue(op.isPresent)
		var p: Post = op.get()
		postRepository.delete(p)
		assertEquals(1, postRepository.count())
	}

	@Test
	fun `createComment`() {
		var op: Optional<Post> = postRepository.findById(3)
		assertTrue(op.isPresent)
		var p: Post = op.get()

		var c = Comment("세번째 포스트만 남았네요.", LocalDateTime.now(), p)
		commentRepository.save(c)
	}

	@Test
	fun `findCommentByTitle` () {
		var op: Optional<Post> = postRepository.findById(3)

		assertTrue(op.isPresent)
		var p: Post = op.get()

		var commentList: List<Comment> = commentRepository.findAll().toList()
		assertEquals(1, commentList.size)
		assertEquals("세번째 포스트만 남았네요.", commentList.get(0).content)
	}
}
