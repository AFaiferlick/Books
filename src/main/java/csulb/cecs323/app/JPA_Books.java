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
//      // List of owners that I want to persist.  I could just as easily done this with the seed-data.sql
//      List <Owners> owners = new ArrayList<Owners>();
//      // Load up my List with the Entities that I want to persist.  Note, this does not put them
//      // into the database.
//      owners.add(new Owners("Reese", "Mike", "714-892-5544"));
//      owners.add(new Owners("Leck", "Carl", "714-321-3729"));
//      owners.add(new Owners("Guitierez", "Luis", "562-982-2899"));
//      // Create the list of owners in the database.
//      carclub.createEntity (owners);

      // Commit the changes so that the new data persists and is visible to other users.
      tx.commit();
      LOGGER.fine("End of Transaction");

      /**
       *   Project To-Do List:
       *       -Finish WritingGroup.java, IndividualAuthor.java, AdHocTeam.java files.
       *       -Implement the files stated above to be subclasses of AuthorEntity.java.
       *       -Implement all One to Many and Many to Many relationships. As seen in diagram from instructions document.
       *       -Complete require menu operations
       *       -Complete validation requirments (See rubric)
       *       -...For full list of requirments, see instructions document and rubric from dropbox
       */

      //Variables
      boolean valid = true; //Used to validate user input
      //End of Variables

      //Main Menu Loop
      while(valid)
      {
         System.out.println("== Welcome ==\nChoose what information you would like to access: \n1. Publishers\n2. Books\n3. Authoring Entities\n4. Exit Program");
         int menuOption = getIntRange(1,4);
         //Validate input using Cleary's methods

         switch ( menuOption ) {
            case 1: //Publisher Menu Options
               System.out.println("\n== Publishers ==\nChoose an option:\n1. Add New Publisher\n2. List Publisher Information\n3. Publisher Primary Key Information\n4. Return to Main Menu");
               int publisherMenuOption = getIntRange(1,4);

               switch ( publisherMenuOption ) {
                  case 1:
                     System.out.println("\n== Add New Publisher ==");
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;

                  case 2:
                     System.out.println("\n== List Publisher Information ==");
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;

                  case 3:
                     System.out.println("\n== Publisher Primary Key Information ==");
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;

                  case 4: //Return to Main Menu
                     System.out.println("");
                     break;
               }
               break;

            case 2: //Book Menu Options
               System.out.println("\n== Books ==\nChoose an option:\n1. Add New Book\n2. Update Book\n3. Delete Book\n4. List Book Information\n5. Book Primary Key Information\n6. Return to Main Menu");
               int bookMenuOption = getIntRange(1,6);

               switch ( bookMenuOption ) {
                  case 1:
                     System.out.println("\n== Add New Book ==");
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 2:
                     System.out.println("\n== Update Book ==");
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 3:
                     System.out.println("\n== Delete Book ==");
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 4:
                     System.out.println("\n== List Book Information ==");
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 5:
                     System.out.println("\n== Book Primary Key Information ==");
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 6: //Return to Main Menu
                     System.out.println("");
                     break;
               }
               break;

            case 3: //Authoring Entities Options
               System.out.println("\n== Authoring Entities ==\nChoose an option:\n1. Add Writing Team\n2. Add Individual Author\n3. Add Ad Hoc Team\n4. Add Author to Ad Hoc Team\n5. List Writing Group Information\n6. Authoring Entitiy Primary Key Information\n7. Return to Main Menu");
               int authorMenuOption = getIntRange(1,7);

               switch ( authorMenuOption ) {
                  case 1:
                     System.out.println("\n== Add Writing Team ==");
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 2:
                     System.out.println("\n== Add Individual Author ==");
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 3:
                     System.out.println("\n== Add Ad Hoc Team ==");
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 4:
                     System.out.println("\n== Add Author to Ad Hoc Team ==");
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 5:
                     System.out.println("\n== List Writing Group Information ==");
                     //Add Code Here
                     System.out.println("[OPTION NOT IMPLEMENTED]");
                     break;
                  case 6:
                     System.out.println("\n== Authoring Entitiy Primary Key Information ==");
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
