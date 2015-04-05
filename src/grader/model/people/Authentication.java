package grader.model.people;

import java.util.Collection;

/**
 * Contains a list of authentic users and data.
 */
abstract class Authentication
{
    /**
     * All of the valid users stored on this system.
     */
    Collection<UserRecord> users;
    /**
     * Data to be retrieved when a user has authenticated.
     */
    Data data;


    /**
     * Login for access to the data available to the associated id.
     *
      pre:
       exists(UserRecord user;
           users.contains(user);
           user.id.equals(id) &&
               user.password.equals(password));
      post:
       return.equals(data);
     *
     */
    abstract Data login(String id, String password);
}
