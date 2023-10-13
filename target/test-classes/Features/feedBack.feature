Feature: FeedBack Page functionality

  Background: User is on Home page
    Given the user is on Home page

  @Planit
  Scenario Outline: ErrorMessage validation in FeedBack Page
    And verify home page title is displayed <title>
    Then click Contact link
    When user clicks Submit button without entering mandatory field
    Then the following error messages should be displayed
      | Forename is required |
      | Email is required    |
      | Message is required  |
    When user enters mandatory feedback information <name> <email> <message>
    Then the error messages should not display

    Examples: 
      | title        | name  | email                | message |
      | Jupiter Toys | Diana | testing123@gmail.com | Thanks  |

  @Planit
  Scenario Outline: Success message validation on submitting feedback form
    Then click Contact link
    When user enters mandatory feedback information <name> <email> <message>
    When the user clicks Submit button
    Then verify success Message is displayed on submission <submissionMessage>

    Examples: 
      | name   | email               | message | submissionMessage                           |
      | Diana  | diana123@gmail.com  | Thanks  | Thanks Diana, we appreciate your feedback.  |
      | Tom    | tom123@gmail.com    | Thanks  | Thanks Tom, we appreciate your feedback.    |
      | Jerry  | jerry123@gmail.com  | Thanks  | Thanks Jerry, we appreciate your feedback.  |
      | Popeye | popeye123@gmail.com | Thanks  | Thanks Popeye, we appreciate your feedback. |
      | Barbie | barbie123@gmail.com | Thanks  | Thanks Barbie, we appreciate your feedback. |
