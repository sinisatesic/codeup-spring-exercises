package com.codeup.springblogapp;

import com.codeup.springblogapp.model.Post;
import com.codeup.springblogapp.repositories.PostRepository;
import com.codeup.springblogapp.model.User;
import com.codeup.springblogapp.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import javax.servlet.http.HttpSession;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBlogAppApplication.class)
@AutoConfigureMockMvc
public class PostsIntegrationTests {

        private User testUser;
        private HttpSession httpSession;

        @Autowired
        private MockMvc mvc;

        @Autowired
        UserRepository userRepo;

        @Autowired
        PostRepository postRepo;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Before
        public void setup() throws Exception {

            testUser = userRepo.findByUsername("testUser");

            // Creates the test user if not exists
            if(testUser == null){
                User newUser = new User();
                newUser.setUsername("testUser");
                newUser.setPassword(passwordEncoder.encode("pass"));
                newUser.setEmail("testUser@codeup.com");
                testUser = userRepo.save(newUser);
            }

            // Throws a Post request to /login and expect a redirection to the Ads index page after being logged in
            httpSession = this.mvc.perform(post("/login").with(csrf())
                    .param("username", "testUser")
                    .param("password", "pass"))
                    .andExpect(status().is(HttpStatus.FOUND.value()))
                    .andExpect(redirectedUrl("/posts"))
                    .andReturn()
                    .getRequest()
                    .getSession();
        }
        @Test
        public void contextLoads() {
            // Sanity Test, just to make sure the MVC bean is working
            assertNotNull(mvc);
        }

        @Test
        public void testIfUserSessionIsActive() throws Exception {
            // It makes sure the returned session is not null
            assertNotNull(httpSession);
        }


        //start of crud tests

    //test for create
    @Test
    public void testCreatePost() throws Exception {
        // Makes a Post request to /ads/create and expect a redirection to the Ad
        this.mvc.perform(
                post("/posts/create").with(csrf())
                        .session((MockHttpSession) httpSession)
                        // Add all the required parameters to your request like this
                        .param("title", "test title")
                        .param("body", "test body"))
                .andExpect(status().is3xxRedirection());
    }


    //read test
    @Test
    public void testShowPost() throws Exception {

        Post existingPost = postRepo.findAll().get(1);

        // Makes a Get request to /ads/{id} and expect a redirection to the Ad show page
        this.mvc.perform(get("/posts/" + existingPost.getId()))
                .andExpect(status().isOk())
                // Test the dynamic content of the page
                .andExpect(content().string(containsString(existingPost.getBody())));
    }
//
//
    @Test
    public void testPostsIndex() throws Exception {
        Post existingPost = postRepo.findAll().get(0);

        // Makes a Get request to /ads and verifies that we get some of the static text of the ads/index.html template and at least the title from the first Ad is present in the template.
        this.mvc.perform(get("/posts"))
                .andExpect(status().isOk())
                // Test the static content of the page
                .andExpect(content().string(containsString("Viewing posts")))
                // Test the dynamic content of the page
                .andExpect(content().string(containsString(existingPost.getTitle())));
    }


    //update test
    @Test
    public void testEditPost() throws Exception {
        // Gets the first Ad for tests purposes
        Post existingPost = postRepo.findAll().get(0);

        // Makes a Post request to /ads/{id}/edit and expect a redirection to the Ad show page
        this.mvc.perform(
                post("/posts/edit/" + existingPost.getId()).with(csrf() )
                        .session((MockHttpSession) httpSession)
                        .param("title", "edited title")
                        .param("body", "edited body"))
                .andExpect(status().is3xxRedirection());

        // Makes a GET request to /ads/{id} and expect a redirection to the Ad show page
        this.mvc.perform(get("/posts/"))
                .andExpect(status().isOk())
                // Test the dynamic content of the page
                .andExpect(content().string(containsString("edited title")))
                .andExpect(content().string(containsString("edited body")));
                    //this shit has to match the above shit
    }

//    delete test
    @Test
    public void testDeletePost() throws Exception {
        // Creates a test Ad to be deleted
        this.mvc.perform(
                post("/posts/create").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("title", "post to be deleted")
                        .param("body", "won't last long"))
                .andExpect(status().is3xxRedirection());

        // Get the recent Ad that matches the title
        Post existingPost = postRepo.getPostByTitle("post to be deleted");

        // Makes a Post request to /ads/{id}/delete and expect a redirection to the Ads index
        this.mvc.perform(
                get("/posts/delete/" + existingPost.getId()).with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("id", String.valueOf(existingPost.getId())));
    }
}
