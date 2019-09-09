package com.Lettucechat.lettucechat.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
  public ApplicationUser findByUsername(String username);

}
//Kevin review
//we can eliminate .getId() if we don't want to see the id of the user in the chatbox.
// Or i could have write interface function in ApplicationUserRepo
