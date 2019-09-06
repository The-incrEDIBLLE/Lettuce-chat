# Lettuce Chat!

Find us at http://lettucechat-env.ipmmcgymp6.us-east-2.elasticbeanstalk.com/

## Project Summary
A chat to find a lunch buddy based on dietary restrictions.

## Conflict Plan
* What will our group do when it encounters conflict?
    * Put it to a vote
* How will you raise concerns to members who are not adequately contributing?
    * In our daily standup
    * Start from a place of support with a whole message
    * "I" statements
* What is your process to resolve conflicts?
    * Ensure members are fed and sufficiently watered
    * Allow members to take reasonably timed breaks
    * Create a safe space where everyone is able to be heard and valued
    * To change our coding environment, if needed.
* How and when will you escalate the conflict resolution?
    * Circle conflict resolution moment
    * Close laptops
    * Use "talking stick" to ensure all concerns are aired and expressed
    * When circle ends, the issue is CLOSED. Resolution complete.
    * If circle cannot solve bring issue to Michelle
* Communication Plan
    * Slack, hours are as needed.  
    * Trello board link: https://trello.com/b/CZn1Prva/lettuce-chat
    * 9:15 am team meeting
    * Check in at the end of the day
    * Timebox any issue to 30 minutes before seeking help

## MVP
    * HTML, Bootstrap, CSS, Postgres, Spring MVC, Javascript, Java
    * Home, About the Team, User Profile page, RandomUser page, Chat page, Chat History page, Sign up page, Login page
    * Create user profile, can edit and delete profile.
    * Generate a random user to connect with - Match page
    * Ability to add chats to the history 
    * Can click chat in chat history to open chat page
## Stretch Goals
    * Real-Time chatting
    * Yelp API suggestion on chatbox
    * Delete chat from chat history list

## User Stories
* USER
    * I want an intuitive UI that is easily understood (with instructions on using the app) 
    * I want a visually appealing site. 
    * I want to meet up with people and eat/drink/etc.
    * I want to be able to chat before I meet up. 
    * I want to sign up for the service.
    * I want to share information about the food and drink they like.
    * I want to be able to see my chat history and click on a chat to continue the conversation.
    * I want to be able to see someone new to chat with that has the same dietary preferences as me.
    * I want to be able to not chat with someone and search for someone else to chat with.
    * I want to see notifications when someone starts a chat with me.
* DEVELOPER
    * I want DRY and readable code.
    * I want modular code to make building the product easy.
    * I want to provide an interactive app for the user.
    * I want to encrypt user passwords.
    * I want to generate a random user to match with.
    * I want to provide the ability to real-time chat with another user.
    * I want to have a normalized database
    * I want to have well defined routes
    * As a Codefellows Instructor who has to grade several projects I want easy to read, well commented, formatted code
 and a nice readme.md
 
[Wireframes](https://docs.google.com/document/d/1OHEhdJKXkddfbMeBZ5oERfuttoAXWSy5koxSMDdyEaM/edit?usp=sharing)

## Instruction to run the app
1. clone the git repository https://github.com/The-incrEDIBLLE/Lettuce-chat.git into your local folder
2. Make sure you have PostgreSQL running on your machine. Create a database called lettuce_chat
3. application.properties file in src/main/resources folder should be updated with your database name, user name and password.

spring.datasource.url=jdbc:postgresql://localhost:5432/<DB NAME>
spring.datasource.username=<DATABASE USERNAME>
spring.datasource.password=<DATABASE PASSWORD>
#spring.jpa.hibernate.ddl-auto=create
4. Run $gradle bootRun from your local folder in terminal

## Git Process 
    * Separate dev and master branches
    * Team members will work on individual branches merging into dev branch
    * Commit to the dev branch at least once a day or when there are major changes.
    * Pull changes every morning into individual branches and start afresh.
    * Commit to master branch when a feature is completed and tested.
    * A team member other than the developer will review pull requests from the developer and merge.
 
 ## Resources
    * icons for devs - https://icon-icons.com/
    * https://www.w3schools.com/
    * https://www.geeksforgeeks.org/java/
    * https://getbootstrap.com/
    * https://pngtree.com/freepng/background-material-design-for-fruits-and-vegetables_3637134.html
    * https://codepen.io/jasondavis/pen/vyMJPp
    * https://bootstrapious.com/p/team-page
    * https://www.pinterest.com/pin/814025701369581277/?autologin=true&nic=1
    * https://www.needpix.com/photo/download/380408/lettuce-vegetables-food-organic-free-pictures-free-photos-free-images-royalty-free
    
 
## Team members
    * Nhu Trinh
    * Padmapriya Ganapathi
    * Joachen Busch
    * Roman Gebrehiwot
