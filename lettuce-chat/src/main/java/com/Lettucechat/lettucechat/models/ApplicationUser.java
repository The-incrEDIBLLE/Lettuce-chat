package com.Lettucechat.lettucechat.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long id;
  String username;
  String password;
  String firstName;
  String lastName;
  String imgUrl;
  String bio;
  String dietaryRestriction;

  @ManyToMany
      @JoinTable(
          name = "userChat",
          joinColumns = {@JoinColumn(name="userId")},
          inverseJoinColumns = {@JoinColumn(name="chatId")}
      )
  Set<Chat> chats;

  @ManyToMany
  @JoinTable(
      name = "follows",
      joinColumns = {@JoinColumn(name ="primaryUser")},
      inverseJoinColumns = {@JoinColumn(name = "followedUser")}
  )
  Set<ApplicationUser> followedUsers;

  @ManyToMany(mappedBy = "followedUsers")
  Set<ApplicationUser> userFollowers;

  public Set<Chat> getChats() {
    return chats;
  }

  public Set<ApplicationUser> getFollowedUsers() {
    return followedUsers;
  }

  public Set<ApplicationUser> getUserFollowers() {
    return userFollowers;
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

  //TODO: add chat function
}

