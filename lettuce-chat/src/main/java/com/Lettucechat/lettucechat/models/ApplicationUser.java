package com.Lettucechat.lettucechat.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long id;

  //@Column(unique = true)
  String username;
  String password;
  String firstName;
  String lastName;
  String imgUrl;
  String bio;
  String dietaryRestriction;

  //Set chats to save all chats the user is associated with
  @ManyToMany(cascade = CascadeType.ALL)
      @JoinTable(
          name = "userChat",
          joinColumns = {@JoinColumn(name="userId")},
          inverseJoinColumns = {@JoinColumn(name="chatId")}
      )
  Set<Chat> chats;

  //Set followedUsers works like 'add friend' feature in chat apps.
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "follows",
      joinColumns = {@JoinColumn(name ="primaryUser")},
      inverseJoinColumns = {@JoinColumn(name = "followedUser")}

  )
  Set<ApplicationUser> followedUsers;

  @ManyToMany(mappedBy = "followedUsers", cascade = CascadeType.ALL)
  Set<ApplicationUser> userFollowers;

  //Getters and Setters

  public Set<Chat> getChats() {
    return chats;
  }

  public Set<ApplicationUser> getFollowedUsers() {
    return followedUsers;
  }

  public Set<ApplicationUser> getUserFollowers() {
    return userFollowers;
  }

  public void setFollowedUsers(Set<ApplicationUser> followedUsers) {
    this.followedUsers = followedUsers;
  }

  public void setUserFollowers(Set<ApplicationUser> userFollowers) {
    this.userFollowers = userFollowers;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public void setDietaryRestriction(String dietaryRestriction) {
    this.dietaryRestriction = dietaryRestriction;
  }

  //Helper method to add friend
  public void addFollowing(ApplicationUser followedUser){
    followedUsers.add(followedUser);
  }

  //Helper method to add chat
  public void addChat(Chat newChat){
    chats.add(newChat);
  }

  //Constructors
  public ApplicationUser(){}
  public ApplicationUser(String username, String password, String firstName, String lastName, String imgUrl,
                         String bio,
                         String dietaryRestriction){
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.imgUrl = imgUrl;
    this.bio = bio;
    this.dietaryRestriction = dietaryRestriction;

  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  public long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public String getBio() {
    return bio;
  }

  public String getDietaryRestriction() {
    return dietaryRestriction;
  }

  public void setChats(Set<Chat> chats) {
    this.chats = chats;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

