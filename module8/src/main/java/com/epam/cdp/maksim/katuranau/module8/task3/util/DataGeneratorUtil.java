package com.epam.cdp.maksim.katuranau.module8.task3.util;

import com.epam.cdp.maksim.katuranau.module8.task3.model.Friendship;
import com.epam.cdp.maksim.katuranau.module8.task3.model.Like;
import com.epam.cdp.maksim.katuranau.module8.task3.model.Post;
import com.epam.cdp.maksim.katuranau.module8.task3.model.User;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DataGeneratorUtil {

    private Faker faker;

    public DataGeneratorUtil() {
        this.faker = new Faker();
    }

    public List<User> generateUsers(int amountOfUsers) {
        List<User> users = new ArrayList<>(amountOfUsers);
        for (int i = 0; i < amountOfUsers; i++) {
            users.add(generateUser());
        }
        return users;
    }

    public List<Post> generatePosts(int amountOfPosts, int amountOfUsers) {
        List<Post> posts = new ArrayList<>(amountOfPosts);
        for (int i = 0; i < amountOfPosts; i++) {
            posts.add(generatePost(amountOfUsers));
        }
        return posts;
    }

    public List<Like> generateLikes(int amountOfLikes, int amountOfUsers) {
        List<Like> likes = new ArrayList<>();
        for (int i = 0; i < amountOfLikes; i++) {
            Like like = generateLike(amountOfUsers);
            if (!likes.contains(like)) {
                likes.add(like);
            }
        }
        return likes;
    }

    public List<Friendship> generateFriendships(int amountOfFriendShips, int amountOfUsers) {
        List<Friendship> friendships = new ArrayList<>(amountOfFriendShips);
        int i = 0;
        while (i < amountOfFriendShips) {
            Friendship fs = generateFriendship(amountOfUsers);
            Friendship fss = new Friendship();
            fss.setUserId1(fs.getUserId2());
            fss.setUserId2(fs.getUserId1());
            if (!friendships.contains(fs) && !friendships.contains(fss) && !fs.getUserId1().equals(fs.getUserId2())) {
                friendships.add(fs);
                i++;
            }
        }
        return friendships;
    }

    public User generateUser() {
        User user = new User();
        user.setName(faker.name().firstName());
        user.setSurname(faker.name().lastName());
        user.setBirthDate(LocalDate.of(getYear(), getMonth(), getDay()));
        return user;
    }

    private Post generatePost(int amountOfPosts) {
        Post post = new Post();
        post.setUserId(ThreadLocalRandom.current().nextInt(1, amountOfPosts));
        post.setText(faker.lorem().paragraph(1));
        post.setTimestamp(LocalDateTime.of(getYear(), getMonth(), getDay(), getHours(), getMinutes(), getSeconds()));
        return post;
    }

    private Like generateLike(int amountOfLikes) {
        Like like = new Like();
        like.setUserId(ThreadLocalRandom.current().nextInt(1, amountOfLikes));
        like.setPostId(ThreadLocalRandom.current().nextInt(1, amountOfLikes));
        like.setTimestamp(LocalDateTime.of(getYear(), getMonth(), getDay(), getHours(), getMinutes(), getSeconds()));
        return like;
    }

    private Friendship generateFriendship(int amountOfUsers) {
        Friendship friendship = new Friendship();
        friendship.setUserId1(ThreadLocalRandom.current().nextInt(1, amountOfUsers + 1));
        friendship.setUserId2(ThreadLocalRandom.current().nextInt(1, amountOfUsers + 1));
        friendship.setTimestamp(LocalDateTime.of(getYear(), getMonth(), getDay(), getHours(), getMinutes(),
                getSeconds()));
        return friendship;
    }

    private Integer getDay() {
        return ThreadLocalRandom.current().nextInt(1, 28);
    }

    private Integer getMonth() {
        return ThreadLocalRandom.current().nextInt(1, 12);
    }

    private Integer getYear() {
        return ThreadLocalRandom.current().nextInt(2000, 2030);
    }

    private Integer getHours() {
        return ThreadLocalRandom.current().nextInt(1, 23);
    }

    private Integer getMinutes() {
        return ThreadLocalRandom.current().nextInt(1, 59);
    }

    private Integer getSeconds() {
        return ThreadLocalRandom.current().nextInt(1, 59);
    }

}
