/*
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2018 Alvaro Monge <alvaro.monge@csulb.edu>
 *
 */

package csulb.cecs323.app;

// Import all of the entity classes that we have written for this application.

import csulb.cecs323.model.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * A simple application to demonstrate how to persist an object in JPA.
 * <p>
 * This is for demonstration and educational purposes only.
 * </p>
 * <p>
 *     Originally provided by Dr. Alvaro Monge of CSULB, and subsequently modified by Dave Brown.
 * </p>
 */
public class JPA_Books {
   /**
    * You will likely need the entityManager in a great many functions throughout your application.
    * Rather than make this a global variable, we will make it an instance variable within the CarClub
    * class, and create an instance of CarClub in the main.
    */
   private EntityManager entityManager;

   /**
    * The Logger can easily be configured to log to a file, rather than, or in addition to, the console.
    * We use it because it is easy to control how much or how little logging gets done without having to
    * go through the application and comment out/uncomment code and run the risk of introducing a bug.
    * Here also, we want to make sure that the one Logger instance is readily available throughout the
    * application, without resorting to creating a global variable.
    */
   private static final Logger LOGGER = Logger.getLogger(JPA_Books.class.getName());

   /**
    * The constructor for the JPA_Books class.  All that it does is stash the provided EntityManager
    * for use later in the application.
    * @param manager    The EntityManager that we will use.
    */
   public JPA_Books(EntityManager manager) {
      this.entityManager = manager;
   }

   public static void main(String[] args) {
      LOGGER.setLevel(Level.OFF);
      LOGGER.fine("Creating EntityManagerFactory and EntityManager");
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_Books");
      EntityManager manager = factory.createEntityManager();
      // Create an instance of CarClub and store our new EntityManager as an instance variable.
      JPA_Books books = new JPA_Books(manager);


      // Any changes to the database need to be done within a transaction.
      // See: https://en.wikibooks.org/wiki/Java_Persistence/Transactions

      LOGGER.fine("Begin of Transaction");
      EntityTransaction tx = manager.getTransaction();

      tx.begin();

      // Commit the changes so that the new data persists and is visible to other users.
      tx.commit();
      LOGGER.fine("End of Transaction");

      /**
       *   Project To-Do List:
       *       -Finish WritingGroup.java, IndividualAuthor.java, AdHocTeam.java files. ~DONE
       *       -Implement the files stated above to be subclasses of AuthorEntity.java. ~DONE
       *       -Implement all One to Many and Many to Many relationships. As seen in diagram from instructions document. ~DONE?
       *       -Complete require menu operations
       *       -Complete validation requirments (See rubric)
       *       -...For full list of requirments, see instructions document and rubric from dropbox
       */

      //Variables
      boolean valid = true; //Used to validate user input
      List <Publishers> publishers = new ArrayList<Publishers>();
      List <Books> booksList = new ArrayList<Books>();
      List <AuthoringEntities> authoringEntities = new ArrayList<AuthoringEntities>();
      List <WritingGroups> writingGroups = new ArrayList<WritingGroups>();
      List <IndividualAuthors> individualAuthors = new ArrayList<IndividualAuthors>();
      List <AdHocTeams> adHocTeams = new ArrayList<AdHocTeams>();

      ArrayList<String> publishersAttributes = new ArrayList<String>();  //will store publisher name, phone#, and email
      List <Books> newBook = new ArrayList<Books>();
      Scanner in = new Scanner( System.in );
      //End of Variables


      publishers.add(new Publishers("Penguin Random House", "800-733-3000", "consumerservices@penguinrandomhouse.com"));
      publishers.add(new Publishers("Prepper Press", "800-759-0190", "derrick@prepperpress.com"));
      publishers.add(new Publishers("Harper Collins", "800-242-7737", "harpercollins.en.cs@digitalriver.com"));
      books.createEntity(publishers);

      IndividualAuthors individual1 = new IndividualAuthors("Tim Tebow", "TimTebow@gmail.com");
      IndividualAuthors individual2 = new IndividualAuthors("Matt Larsen", "MattLarsen@gmail.com");
      individualAuthors.add(individual1);
      individualAuthors.add(individual2);
      books.createEntity(individualAuthors);

      AdHocTeams team1 = new AdHocTeams("US Army", "vapublicaffairs@va.gov");
      adHocTeams.add(team1);
      team1.addIndividualAuthor(individual2); //added an individual to an ad hoc team
      individual2.addAdHocTeam(team1); //added ad hoc team membership to an individual
      books.createEntity(adHocTeams);

      WritingGroups group1 = new WritingGroups("Erin Hunter", "ErinHunter@gmail.com", "Victoria Holmes", 2003);
      writingGroups.add(group1);
      books.createEntity(writingGroups);

      authoringEntities.add(individual1); //add all authoring entity instances to the authoring entities list
      authoringEntities.add(individual2);
      authoringEntities.add(team1);
      authoringEntities.add(group1);
      books.createEntity(authoringEntities);

      booksList.add(new Books("9780593194034", "Mission Possible", 2022, publishers.get(0), authoringEntities.get(0)));
      booksList.add(new Books("0062366963", "Warriors: Into the Wild", 2003, publishers.get(2), authoringEntities.get(3)));
      booksList.add(new Books("9781493023769", "The Official US Army Survival Manual", 2020, publishers.get(1), authoringEntities.get(2)));
      books.createEntity(booksList);


      //Main Menu Loop
      while(valid)
      {
         System.out.println("== Welcome ==\nChoose what information you would like to access: \n1. Publishers\n2. Books\n" +
                 "3. Authoring Entities\n4. Exit Program");
         int menuOption = getIntRange(1,4);

         //For use in publisher menu option
         int publisherSelection = 0;
         Publishers publisherChoice = null;

         int authorSelection = 0;
         AuthoringEntities authorChoice = null;

         int groupSelection = 0;
         WritingGroups groupChoice = null;

         switch ( menuOption ) { //TODO: Gracefully handle exceptions that occur when publishers have indentical PK
            case 1: //Publisher Menu Options
               System.out.println("\n== Publishers ==\nChoose an option:\n1. Add New Publisher\n" +
                       "2. List Publisher Information\n3. Publisher Primary Key Information\n4. Return to Main Menu");
               int publisherMenuOption = getIntRange(1,4);

               LOGGER.fine("Beginning of Transaction");
               EntityTransaction publishertx = manager.getTransaction();
               publishertx.begin();

               switch ( publisherMenuOption ) {
                  case 1: //good
                     System.out.println("\n== Add New Publisher ==");

                     System.out.println("Enter publisher name: ");
                     publishersAttributes.add(getString());

                     System.out.println("Enter publisher phone: ");
                     publishersAttributes.add(getString());

                     System.out.println("Enter publisher email: ");
                     publishersAttributes.add(getString());

                     publishers.add(new Publishers(publishersAttributes.get(0), publishersAttributes.get(1), publishersAttributes.get(2)));
                     books.createEntity(publishers);  //persist publishers right after being created
                     publishersAttributes.clear();
                     break;

                  case 2: //good
                     System.out.println("\n== List Publisher Information ==");

                     for( int i = 0; i < publishers.size(); i++ ) {
                        System.out.println((i+1) + ": " + publishers.get(i).getName());
                     }
                     System.out.println("\nPlease select one of the above publishers' information to list: ");
                     publisherSelection = getIntRange(1, publishers.size());
                     publisherChoice = publishers.get(publisherSelection-1);
                     System.out.println("Publisher name: " + publisherChoice.getName() + "\nPublisher email: "
                             + publisherChoice.getEmail() + "\nPublisher phone number: " + publisherChoice.getPhone() + "\n");
                     break;

                  case 3: //good
                     System.out.println("\n== Publisher Primary Key Information ==\n");
                     List<Publishers> publisherPKs = books.getPublisherPKs();
                     for( int i = 0; i < publisherPKs.size(); i++ ) {
                        System.out.println(publisherPKs.get(i).getName());
                     }
                     System.out.println();
                     break;

                  case 4: //Return to Main Menu
                     System.out.println("Returning to Main Menu...\n");
                     break;
               }

               // Commit the changes so that the new data persists and is visible to other users.
               publishertx.commit();
               LOGGER.fine("End of Transaction");
               break;

            case 2: //Book Menu Options
               System.out.println("\n== Books ==\nChoose an option:\n1. Add New Book\n2. Update Book\n" +
                       "3. Delete Book\n4. List Book Information\n5. Book Primary Key Information\n6. Return to Main Menu");
               int bookMenuOption = getIntRange(1,6);

               LOGGER.fine("Beginning of Transaction");
               EntityTransaction booktx = manager.getTransaction();
               booktx.begin();

               switch ( bookMenuOption ) { //TODO: Gracefully handle exceptions that occur when books have indentical PK
                  case 1: //good
                     System.out.println("\n== Add New Book ==");

                     System.out.println("Enter publisher ISBN for Book: "); // ISBN
                     String ISBN = in.next(); // Books

                     System.out.println("Enter book title: "); // title
                     String bookTitle = getString(); // Books

                     System.out.println("Enter the year the book was published: "); // Year Published
                     int yearPublished = getIntRange(1440, 2022);

                     for( int i = 0; i < publishers.size(); i++ ) {
                        System.out.println((i+1) + ": " + publishers.get(i).getName());
                     }
                     System.out.println("Select which publisher above this book was published by: "); // allows user to select publisher
                     publisherSelection = getIntRange(1, publishers.size());
                     publisherChoice = publishers.get(publisherSelection-1);

                     for( int i = 0; i < authoringEntities.size(); i++ ) {
                        System.out.println((i+1) + ": " + authoringEntities.get(i).getName());
                     }
                     System.out.println("\nSelect which authoring entity above this book was written by: "); // allows user to select author
                     authorSelection = getIntRange(1, authoringEntities.size());
                     authorChoice = authoringEntities.get(authorSelection-1);

                     booksList.add(0, new Books(ISBN, bookTitle, yearPublished, publisherChoice, authorChoice)); //creates new book based on input
                     books.createEntity(booksList); //persists new book within bookList ArrayList
                     break;

                  case 2:
                     System.out.println("\n== Update Book ==");
                     System.out.println("+========= List of All Books =========+");
                     // List all books from the database so the user can pick one to delete.
                     for (int i=0; i<booksList.size(); i++) {
                        System.out.println(i+1 + "\t" + booksList.get(i).getTitle());
                     }
                     System.out.println("\nPlease choose one book from the list above to update it: ");
                     int bookToUpdate = getIntRange(1, booksList.size());
                     Books updatedBook = booksList.get(bookToUpdate-1);

                     books.createEntity(booksList); // Not sure if I need this
                     int updateChoice = 0;

                     do {
                        System.out.println("What what you like to update?\n1. ISBN\n2. Book Title\n3. Publication Year\n4. Publisher\n" +
                                "5. Authoring Entity\n6. Exit");
                        updateChoice = getIntRange(1, 6);

                        switch (updateChoice) {
                              case 1: // Changing the ISBN throws an Exception
                                 System.out.println("Enter new ISBN: ");
                                 String newISBN = in.next();
                                 System.out.print(updatedBook.getISBN() + " has been changed to: ");
                                 updatedBook.setISBN(newISBN);
                                 System.out.println(updatedBook.getISBN() + "\n");
                                 break;
                              case 2: // Changing the Book Title throws an Exception
                                 System.out.println("Enter new Book Title: ");
                                 String newBookTitle = getString();
                                 System.out.println(updatedBook.getTitle() + " has been changed to: " + newBookTitle + "\n");
                                 updatedBook.setTitle(newBookTitle);
                                 break;
                              case 3:
                                 System.out.println("Enter new Publication Year: ");
                                 int updatedPublicationYear = getIntRange(1440, 2022);
                                 System.out.print(updatedBook.getYear_published() + " has been changed to: ");
                                 updatedBook.setYear_published(updatedPublicationYear);
                                 System.out.println(updatedBook.getYear_published() + "\n");
                                 break;
                              case 4:
                                 // Prompt user to choose from valid publishers for updating publisher.
                                 System.out.println("---- Available Publishers ----\n");
                                 for (int i = 0; i < publishers.size(); i++) {
                                    System.out.println((i + 1) + ": " + publishers.get(i).getName());
                                 }
                                 System.out.println("\nChoose one of the publishers listed above to update the current publisher: ");
                                 publisherSelection = getIntRange(1, publishers.size());
                                 publisherChoice = publishers.get(publisherSelection - 1);

                                 System.out.print(updatedBook.getPublisher().getName() + " has been changed to: ");
                                 updatedBook.setPublisher(publisherChoice);
                                 System.out.println(updatedBook.getPublisher().getName() + "\n");
                                 break;
                              case 5:
                                 System.out.println("---- Available Authoring Entities ----\n");
                                 for( int i = 0; i < authoringEntities.size(); i++ ) {
                                    System.out.println((i+1) + ": " + authoringEntities.get(i).getName());
                                 }
                                 System.out.println("\nChoose one of the authoring entities listed above to update the current authoring entity:");
                                 authorSelection = getIntRange(1, authoringEntities.size());
                                 authorChoice = authoringEntities.get(authorSelection-1);

                                 System.out.print(updatedBook.getAuthoringEntity().getName() + " has been changed to: ");
                                 updatedBook.setAuthoringEntity(authorChoice);
                                 System.out.println(updatedBook.getAuthoringEntity().getName() + "\n");
                                 break;
                              case 6:
                                 System.out.println("Exiting...");
                                 break;
                           }
                     } while (updateChoice != 6);

                     break;
                  case 3:
                     System.out.println("\n== Delete Book ==");
                     //Add Code Here
                     System.out.println("+========= List of All Books =========+");
                     // List all books from the database so the user can pick one to delete.
                     for (int i=0; i<booksList.size(); i++) {
                        System.out.println(i+1 + "\t" + booksList.get(i).getTitle());
                     }
                     System.out.println("========================================");

                     System.out.println("\nEnter sequence number of the book you wish to delete: ");
                     int bookToDelete = getIntRange(1, booksList.size());

                     System.out.println("Do you wish to delete " + booksList.get(bookToDelete-1).getTitle() + " (Y/N)?");
                     String userChoice = in.next().toUpperCase(); // Converts user input to uppercase.

                     if (userChoice.equals("Y")) {
                        // TO DO: Implement the deletion of chosen book from the database.
                        //booksList.get(bookToDelete-1);
                        manager.remove(booksList.get(bookToDelete-1));
                        booksList.remove(bookToDelete-1); // removes by index number
                        // might need to commit.
                     } else {
                        System.out.println(booksList.get(bookToDelete-1).getTitle() + " was not deleted.");
                        //test
                     }

                     break;
                  case 4: //good
                     System.out.println("\n== List Book Information ==\n");
                     System.out.println("------- Available Books: -------\n");
                     for( int i = 0; i < booksList.size(); i++ ) {
                        System.out.println((i+1) + ": " + booksList.get(i).getTitle());
                     }
                     System.out.println("\nTo list book information, please select one of the books from the above " +
                             "list [1-" + booksList.size() + "]: ");
                     int bookSelection = getIntRange(1, booksList.size());
                     Books bookChoice = booksList.get(bookSelection-1);

                     System.out.println("Retrieving information for \"" + bookChoice.getTitle() + "\"...");
                     // List information for that book.
                     System.out.println("ISBN: " + bookChoice.getISBN()
                             + "\nBook Title: " + bookChoice.getTitle()
                             + "\nYear Published: " + bookChoice.getYear_published()
                             + "\nPublisher: " + bookChoice.getPublisher().getName()
                             + "\nAuthoring Entity: " + bookChoice.getAuthoringEntity().getName() + "\n");
                     break;

                  case 5: //TODO: Newly added books are not displayed
                     System.out.println("\n== Book Primary Key Information ==\n");
                     List<Books> bookPKs = books.getBookPKs();
                     for( int i = 0; i < bookPKs.size(); i++ ) {
                        System.out.println(bookPKs.get(i).getTitle() + ", " + bookPKs.get(i).getISBN());
                     }
                     System.out.println();
                     break;
                  case 6: //Return to Main Menu
                     System.out.println("Returning to Main Menu...\n");
                     break;
               }
               // Commit the changes so that the new data persists and is visible to other users.
               booktx.commit();
               LOGGER.fine("End of Transaction");
               break;

            case 3: //Authoring Entities Options
               System.out.println("\n== Authoring Entities ==\nChoose an option:\n1. Add Writing Group\n" +
                       "2. Add Individual Author\n3. Add Ad Hoc Team\n4. Add Author to Ad Hoc Team\n" +
                       "5. List Writing Group Information\n6. Authoring Entitiy Primary Key Information" +
                       "\n7. Return to Main Menu");
               int authorMenuOption = getIntRange(1,7);

               LOGGER.fine("Beginning of Transaction");
               EntityTransaction authortx = manager.getTransaction();
               authortx.begin();

               switch ( authorMenuOption ) { //TODO: Gracefully handle exceptions that occur when authors have indeitical PK
                  case 1: //good
                     System.out.println("\n== Add Writing Group ==");

                     System.out.println("Please enter the name of the writing group:");
                     String grpName = getString();
                     System.out.println("Please enter the E-mail of the writing group:");
                     String grpEmail = getString();
                     System.out.println("Please enter the name of the head author:");
                     String grpHeadAuth = getString();
                     System.out.println("Please enter the year the writing group was formed:");
                     int grpYearFormed = getIntRange(1440,2022);

                     WritingGroups newGroup = new WritingGroups(grpName, grpEmail, grpHeadAuth, grpYearFormed); //creates new writing group based on input
                     writingGroups.add(newGroup); //add new writing group to writingGroups and authoringEntities
                     authoringEntities.add(newGroup);
                     books.createEntity(writingGroups); //persists new writing group within writingGroups and authoringEntities ArrayLists
                     books.createEntity(authoringEntities);
                     break;
                  case 2: //good
                     System.out.println("\n== Add Individual Author ==");
                     System.out.println("Enter the Author's Name:");
                     String indAuthName = getString();
                     System.out.println("Enter the Authors Email:");
                     String indAuthMail = getString();
                     IndividualAuthors newIndividual = new IndividualAuthors(indAuthName, indAuthMail); //creates new individual author based on input
                     individualAuthors.add(newIndividual);
                     authoringEntities.add(newIndividual);
                     books.createEntity(individualAuthors);
                     books.createEntity(authoringEntities);
                     break;
                  case 3: //good
                     System.out.println("\n== Add Ad Hoc Team ==");
                     System.out.println("Enter the team's name:");
                     String teamName = getString();
                     System.out.println("Enter the team's email:");
                     String teamMail = getString();
                     AdHocTeams newTeam = new AdHocTeams(teamName, teamMail); //creates new team based on input
                     adHocTeams.add(newTeam);
                     authoringEntities.add(newTeam);
                     books.createEntity(adHocTeams);
                     books.createEntity(authoringEntities);
                     break;
                  case 4:  //TODO: Gracefully handle exception that occurs when author tries to get added to team they're already on
                     System.out.println("\n== Add Author to Ad Hoc Team ==");
                     for( int i = 0; i < individualAuthors.size(); i++ ) {
                        System.out.println((i+1) + ": " + individualAuthors.get(i).getName());
                     }
                     System.out.println("\nPlease select one of the above Individual Authors to add to an Ad Hoc Team: ");
                     int individualSelection = getIntRange(1, individualAuthors.size());
                     IndividualAuthors individualChoice = individualAuthors.get(individualSelection-1);

                     for( int i = 0; i < adHocTeams.size(); i++ ) {
                        System.out.println((i+1) + ": " + adHocTeams.get(i).getName());
                     }
                     System.out.println("Please select one of the above Ad Hoc Teams to add the Individual Author to: ");
                     int teamSelection = getIntRange(1, adHocTeams.size());
                     AdHocTeams teamChoice = adHocTeams.get(teamSelection-1);

                     teamChoice.addIndividualAuthor(individualChoice);
                     System.out.println("Individual Author " + individualChoice.getName() + " has been added to the Ad Hoc Team " + teamChoice.getName());
                     break;
                  case 5: //good
                     System.out.println("\n== List Writing Group Information ==");
                     for( int i = 0; i < writingGroups.size(); i++ ) {
                        System.out.println((i+1) + ": " + writingGroups.get(i).getName());
                     }
                     System.out.println("\nPlease select one of the above writing group' information to list: ");
                     groupSelection = getIntRange(1, writingGroups.size());
                     groupChoice = writingGroups.get(groupSelection-1);
                     System.out.println("Writing group name: " + groupChoice.getName() + "\nWriting group email: " + groupChoice.getEmail()
                     + "\nWriting group head writer: " + groupChoice.getHeadWriter() + "\nWriting group year formed: " + groupChoice.getYearFormed() + "\n");
                     break;
                  case 6: //good
                     System.out.println("\n== Authoring Entity Primary Key Information ==\n");
                     for( int i = 0; i < authoringEntities.size(); i++ ) {
                        System.out.println(authoringEntities.get(i).getEmail());
                     }
                     System.out.println();
                     break;
                  case 7: //Return to Main Menu
                     System.out.println("Returning to Main Menu...\n");
                     break;
               }
               // Commit the changes so that the new data persists and is visible to other users.
               authortx.commit();
               LOGGER.fine("End of Transaction");
               break;

            case 4: //Exit program
               System.out.println("Exiting Program...");
               System.exit(0);
         }
      }
   } // End of the main method

   /**
    * Create and persist a list of objects to the database.
    * @param entities   The list of entities to persist.  These can be any object that has been
    *                   properly annotated in JPA and marked as "persistable."  I specifically
    *                   used a Java generic so that I did not have to write this over and over.
    */
   public <E> void createEntity(List <E> entities) {
      for (E next : entities) {
         LOGGER.info("Persisting: " + next);
         // Use the CarClub entityManager instance variable to get our EntityManager.
         this.entityManager.persist(next);
      }

      // The auto generated ID (if present) is not passed in to the constructor since JPA will
      // generate a value.  So the previous for loop will not show a value for the ID.  But
      // now that the Entity has been persisted, JPA has generated the ID and filled that in.
      for (E next : entities) {
         LOGGER.info("Persisted object after flush (non-null id): " + next);
      }
   } // End of createEntity member method

   public List<Books> getBookList() { // Retrieves all books into a list
      List<Books> books = this.entityManager.createNamedQuery("ReturnBookList", Books.class).getResultList();
      return books;
   }

   public List<Books> getBookPKs() { // Retrieves all publisher PKs into a list
      List<Books> BookPKs =
              this.entityManager.createNamedQuery("ReturnBookPKs", Books.class).getResultList();
      return BookPKs;
   }

   public List<Publishers> getPublisherList() { // Retrieves all publishers into a list
      List<Publishers> publishers =
              this.entityManager.createNamedQuery("ReturnAllPublishers", Publishers.class).getResultList();
      return publishers;
   }

   public List<Publishers> getPublisherPKs() { // Retrieves all publisher PKs into a list
      List<Publishers> publisherPKs =
              this.entityManager.createNamedQuery("ReturnPublisherPKs", Publishers.class).getResultList();
      return publisherPKs;
   }


   /**
    * Checks if the inputted value is an integer and
    * within the specified range (ex: 1-10)
    * @param low lower bound of the range.
    * @param high upper bound of the range.
    * @return the valid input.
    */
   public static int getIntRange( int low, int high ) {
      Scanner in = new Scanner( System.in );
      int input = 0;
      boolean valid = false;
      while( !valid ) {
         if( in.hasNextInt() ) {
            input = in.nextInt();
            if( input <= high && input >= low ) {
               valid = true;
            } else {
               System.out.println( "Invalid Range." );
            }
         } else {
            in.next(); //clear invalid string
            System.out.println( "Invalid Input." );
         }
      }
      return input;
   } // End of getIntRange method

   /**
    * Takes in a string from the user.
    * @return the inputted String.
    */
   public static String getString() {
      Scanner in = new Scanner( System.in );
      String input = in.nextLine();
      return input;
   }

//   /**
//    * Think of this as a simple map from a String to an instance of auto_body_styles that has the
//    * same name, as the string that you pass in.  To create a new Cars instance, you need to pass
//    * in an instance of auto_body_styles to satisfy the foreign key constraint, not just a string
//    * representing the name of the style.
//    * @param name       The name of the autobody style that you are looking for.
//    * @return           The auto_body_styles instance corresponding to that style name.
//    */
//   public auto_body_styles getStyle (String name) {
//      // Run the native query that we defined in the auto_body_styles entity to find the right style.
//      List<auto_body_styles> styles = this.entityManager.createNamedQuery("ReturnAutoBodyStyle",
//              auto_body_styles.class).setParameter(1, name).getResultList();
//      if (styles.size() == 0) {
//         // Invalid style name passed in.
//         return null;
//      } else {
//         // Return the style object that they asked for.
//         return styles.get(0);
//      }
//   }// End of the getStyle method

} // End of CarClub class
