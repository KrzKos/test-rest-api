package pl.juniorjavaproject.testrestapi.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.juniorjavaproject.testrestapi.dto.TweetDTO;
import pl.juniorjavaproject.testrestapi.dto.UserDTO;
import pl.juniorjavaproject.testrestapi.model.Tweet;
import pl.juniorjavaproject.testrestapi.model.User;

import javax.validation.constraints.NotNull;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Tweet Mapper")
class TweetMapperTest {
    TweetMapper tweetMapper;
    @BeforeEach
    void setup(){
        tweetMapper = new TweetMapper();

    }
    @DisplayName(" - should map TweetDTO to Tweet")
    @Test
    public void test1(){
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("Jan");
        userDTO.setLastName("Kowalski");

        TweetDTO tweetDTO = new TweetDTO();
        tweetDTO.setId(1L);
        tweetDTO.setTweetText("Tweet test");
        tweetDTO.setTweetTitle("Test title");
        tweetDTO.setUserDTO(userDTO);

        Tweet tweet = new Tweet();
        tweet.setId(tweet.getId());
        tweet.setTweetText(tweetDTO.getTweetText());
        tweet.setTweetTitle(tweetDTO.getTweetTitle());
        tweetDTO.setUserDTO(userDTO);
        //when
        Tweet result = tweetMapper.from(tweetDTO);
        //then

        assertAll(
                () -> assertThat(result).isNotNull(),
                () -> assertThat(result.getId()).isEqualTo(tweetDTO.getId()),
                () -> assertThat(result.getTweetText()).isEqualTo(tweetDTO.getTweetText()),
                () -> assertThat(result.getTweetTitle()).isEqualTo(tweetDTO.getTweetTitle()),
                () -> assertThat(result.getUser())
                        .hasFieldOrPropertyWithValue("firstName", userDTO.getFirstName())
                        .hasFieldOrPropertyWithValue("lastName", userDTO.getLastName())
        );
    }

    @DisplayName(" - should map Tweet to TweetDTO")
    @Test
    public void test2() {
        // given
        User user = new User();
        user.setFirstName("Bob");
        user.setLastName("Budowniczy");
        user.setId(2L);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());


        Tweet tweet = new Tweet();
        tweet.setId(3L);
        tweet.setUser(user);
        tweet.setTweetTitle("New Title");
        tweet.setTweetText("Another interesting tweet");

        TweetDTO tweetDTO = new TweetDTO();
        tweetDTO.setId(tweet.getId());
        tweetDTO.setUserDTO(userDTO);
        tweetDTO.setTweetTitle(tweet.getTweetTitle());
        tweetDTO.setTweetText(tweet.getTweetText());

        //when

        TweetDTO result2 = tweetMapper.from(tweet);

        assertAll(
                () -> assertThat(result2).isNotNull(),
                () -> assertThat(result2.getId()).isEqualTo(tweet.getId()),
                () -> assertThat(result2.getTweetTitle()).isEqualTo(tweet.getTweetTitle()),
                () -> assertThat(result2.getTweetText()).isEqualTo(tweet.getTweetText()),
                () -> assertThat(result2.getUserDTO())
                        .hasFieldOrPropertyWithValue("firstName", tweet.getUser().getFirstName())
                        .hasFieldOrPropertyWithValue("lastName", tweet.getUser().getLastName())
        );

    }
}