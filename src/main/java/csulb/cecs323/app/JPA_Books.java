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

      // List of owners that I want to persist.  I could just as easily done this with the seed-data.sql
      //List <Owners> owners = new ArrayList<Owners>();
      // Load up my List with the Entities that I want to persist.  Note, this does not put them
      // into the database.
      //owners.add(new Owners("Reese", "Mike", "714-892-5544"));
      //owners.add(new Owners("Leck", "Carl", "714-321-3729"));
      //owners.add(new Owners("Guitierez", "Luis", "562-982-2899"));
      // Create the list of owners in the database.
      //carclub.createEntity (owners);

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

      IndividualAuthors individual1 = new IndividualAuthors("TimTebow@gmail.com", "Tim Tebow");
      IndividualAuthors individual2 = new IndividualAuthors("MattLarsen@gmail.com", "Matt Larsen");
      individualAuthors.add(individual1);
      individualAuthors.add(individual2);
      books.createEntity(individualAuthors);

      AdHocTeams team1 = new AdHocTeams("vapublicaffairs@va.gov", "US Army");
      adHocTeams.add(team1);
      team1.addIndividualAuthor(individual2); //added an individual to an ad hoc team
      individual2.addAdHocTeam(team1); //added ad hoc team membership to an individual
      books.createEntity(adHocTeams);

      WritingGroups group1 = new WritingGroups("ErinHunter@gmail.com", "Erin Hunter", "Victoria Holmes", 2003);
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

         switch ( menuOption ) {
            case 1: //Publisher Menu Options
               System.out.println("\n== Publishers ==\nChoose an option:\n1. Add New Publisher\n" +
                       "2. List Publisher Information\n3. Publisher Primary Key Information\n4. Return to Main Menu");
               int publisherMenuOption = getIntRange(1,4);

               LOGGER.fine("Begin of Transaction");
               EntityTransaction publishertx = manager.getTransaction();

               publishertx.begin();

               switch ( publisherMenuOption ) {
                  case 1:
                     System.out.println("\n== Add New Publisher ==");

                     System.out.println("Enter publisher name: ");
                     publishersAttributes.add(getString());

                     System.out.println("Enter publisher phone: ");
                     publishersAttributes.add(getString());

                     System.out.println("Enter publisher email: ");
                     publishersAttributes.add(getString());

                     publishers.add(new Publishers(publishersAttributes.get(0), publishersAttributes.get(1), publishersAttributes.get(2)));
                     publishersAttributes.clear();
                     break;

                  case 2:
                     System.out.println("\n== List Publisher Information ==");

                     for( int i = 0; i < publishers.size(); i++ ) {
                        System.out.println((i+1) + ": " + publishers.get(i).getName() + "\n");
                     }
                     System.out.println("Please select one of the above publishers' information to list: ");
                     int publisherSelection = getIntRange(1, publishers.size()+1);
                     Publishers publisherChoice = publishers.get(publisherSelection-1);
                     System.out.println("Publisher name: " + publisherChoice.getName() + "\nPublisher email: "
                             + publisherChoice.getEmail() + "\nPublisher phone number: " + publisherChoice.getPhone());

                     break;

                  case 3:
                     System.out.println("\n== Publisher Primary Key Information ==");
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;

                  case 4: //Return to Main Menu
                     System.out.println("Exiting Program...");
                     valid = false;
                     break;
               }
               books.createEntity(publishers);

               // Commit the changes so that the new data persists and is visible to other users.
               publishertx.commit();
               LOGGER.fine("End of Transaction");
               break;

            case 2: //Book Menu Options
               System.out.println("\n== Books ==\nChoose an option:\n1. Add New Book\n2. Update Book\n" +
                       "3. Delete Book\n4. List Book Information\n5. Book Primary Key Information\n6. Return to Main Menu");
               int bookMenuOption = getIntRange(1,6);

               switch ( bookMenuOption ) {
                  case 1:
                     System.out.println("\n== Add New Book ==");
                     //Add Code Here
                     System.out.println("Enter publisher ISBN for Book: "); // ISBN
                     String ISBN = in.next(); // Books

                     System.out.println("Enter book title: "); // title
                     String bookTitle = getString(); // Books

                     System.out.println("Enter the year the book was published: "); // Year Published
                     int yearPublished = getIntRange(1, 2022); // Not sure what the earliest year is for published books

                     System.out.println("Enter publisher name: "); // publisher
                     String publisherName = getString();

                     System.out.println("Enter publisher's phone number: ");
                     String publisherNumber = in.next();

                     System.out.println("Enter publisher's email: ");
                     String publisherEmail = in.next();

                     // Create new Publisher object
                     Publishers newPublisher = new Publishers(publisherName, publisherNumber, publisherEmail);

                     System.out.println("Enter author name: "); // authoringEntity
                     String authorName = getString();

                     System.out.println("Enter author's email: ");
                     String authorEmail = in.next();

                     // Create new AuthoringEntities Object. Not sure how to do this properly.
                     //AuthoringEntities newAuthor = new AuthoringEntities(authorName, authorEmail); // cannot instantiate
                     //Books bookToAdd = new Books(ISBN, bookTitle, yearPublished, newPublisher, newAuthor);

                     List<Books> newBookEntry = new ArrayList<Books> ();
                     //newBookEntry.add(0, new Books(ISBN, bookTitle, yearPublished, newPublisher, newAuthor));
                     books.createEntity(newBookEntry);

                     //publishersAttributes.clear();
                     break;
                  case 2:
//                     System.out.println("\n== Update Book ==");
//                     // enter book title
//                     System.out.println("Enter publisher ISBN for Book: "); // ISBN
//                     Books.add(getString());
//
//                     System.out.println("Enter title of Book: "); // title
//                     Books.add(getString()); // this should just be omitted or just enter the value of the found book
//                     (essentially overwrites the name with the same name)
//
//                     System.out.println("Enter the Year the Book was published: "); // Year Published
//                     Books.add(getString());
//
//                     System.out.println("Enter the publisher's name: "); // publisher
//                     Books.add(getString());
//
//                     System.out.println("Enter Name of the Author: "); // authoringEntity
//                     Books.add(getString());
//
//                     Books.add(new Books(Books.get(0), Books.get(1), Books.get(2)));
//                     publishersAttributes.clear();
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
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

                     System.out.println("Enter sequence number of the book you wish to delete: ");
                     int bookToDelete = getIntRange(1, booksList.size());

                     System.out.println("Do you wish to delete " + booksList.get(bookToDelete-1).getTitle() + " (Y/N)?");
                     String userChoice = in.next().toUpperCase();

                     if (userChoice.equals("Y") ||userChoice.equals("y")) {
                        // TO DO: Implement the deletion of chosen book from the database.
                     } else {
                        System.out.println(booksList.get(bookToDelete-1).getTitle() + " was not deleted.");
                     }

                     break;
                  case 4:
                     System.out.println("\n== List Book Information ==");
                     // Add core here
                     // Maybe a for loop that iterates through the amount of books
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 5:
                     System.out.println("\n== Book Primary Key Information ==");
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 6: //Return to Main Menu
                     System.out.println("Returning to Main Menu...");
                     break;
               }
               break;

            case 3: //Authoring Entities Options
               System.out.println("\n== Authoring Entities ==\nChoose an option:\n1. Add Writing Group\n" +
                       "2. Add Individual Author\n3. Add Ad Hoc Team\n4. Add Author to Ad Hoc Team\n" +
                       "5. List Writing Group Information\n6. Authoring Entitiy Primary Key Information\n7. Return to Main Menu");
               int authorMenuOption = getIntRange(1,7);

               switch ( authorMenuOption ) {
                  case 1:
                     System.out.println("\n== Add Writing Group ==");
                     //Add Code Here head writer emain name and year
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 2:
                     System.out.println("\n== Add Individual Author ==");
                     System.out.println("Enter the Author's Name:");
                     String GetIndAuthName =getString();
                     System.out.println("Enter the Authors Email:");
                     String GetIndAuthMail = getString();
                      individualAuthors.add(new IndividualAuthors (GetIndAuthMail,GetIndAuthName));
                     //IndividualAuthors New IndividualAuthors = new;
                     //System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 3:
                     System.out.println("\n== Add Ad Hoc Team ==");
                     //Add Code Here team name email
                     System.out.println("Please write the AdHoc Team Name:");
                     String ADHName = getString();
                     System.out.println("Please enter the AdHoc Email:");
                     String ADHMail = getString();
                     //System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 4:
                     System.out.println("\n== Add Author to Ad Hoc Team ==");
                     System.out.println("Please write main AdHoc Author Name:");
                     String ADH_MainName = getString();
                     //System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 5:
                     System.out.println("\n== List Writing Group Information ==");
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 6:
                     System.out.println("\n== Authoring Entity Primary Key Information ==");
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 7: //Return to Main Menu
                     System.out.println("");
                     break;
               }
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

   public List<Publishers> getPublisherList() { // Retrieves all publishers into a list
      List<Publishers> publishers = this.entityManager.createNamedQuery("ReturnAllPublishers", Publishers.class).getResultList();
      return publishers;
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
