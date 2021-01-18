package com.fame.zbw;

import com.zbw.fame.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangbowen
 * @since 2019/6/24 18:23
 */
@Slf4j
public class ArticleServiceTests extends BaseTests {

    @Autowired
    private PostService postService;

    @Test
    public void getFrontArticle() {
        postService.getFrontArticle(100);
    }

    @Test
    public void visitPost() throws InterruptedException {
        postService.visitPost(1);
        Thread.sleep(1000); // 等待异步线程完成
    }
}
