package edu.gaTech.teamElevenProject;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.view.View;
import java.util.ArrayList;

/**
 * Created by Juliet on 04/03/16.
 */
public class HandleLoginTest extends AndroidTestCase {

    private DatabaseWrapper db;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new DatabaseWrapper(context, DatabaseWrapper.databaseName);
    }
    @Override
    public void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }

    public void testHandleLoginRequest() throws Exception {
        setUp();
        String validUser = "validUser";
        String validPass = "validPass";
        String invalidUser = "invalidUser";
        String invalidPass = "invalidPass";
        SQLiteDatabase rdb = db.getReadableDatabase();
        UserManager userManager = new UserManager(db, rdb);
        userManager.addUser(validUser, validPass);
        ArrayList<User> userList = (ArrayList<User>) userManager.getUserList();
        for (int i = 0; i < userList.size(); i++) {
            User u = userManager.getUserList().get(i);
            if (u.getName().equals(validUser) && u.getPassword().equals(validPass)) {
                assertTrue(userManager.handleLoginRequest(u.getName(), u.getPassword()));
                System.out.println(u.getName() + " with password " + u.getPassword() + " can log in!");
            }
            assertFalse(userManager.handleLoginRequest(u.getName(), invalidPass));
            System.out.println(u.getName() + " with password " + invalidPass + " can't log in!");
            assertFalse(userManager.handleLoginRequest(invalidUser, u.getPassword()));
            System.out.println(invalidUser + " with password " + u.getPassword() + " can't log in!");
            assertFalse(userManager.handleLoginRequest(invalidUser, invalidPass));
            System.out.println(invalidUser + " with password " + invalidPass + " can't log in!");
        }
        tearDown();
    }

//    public boolean handleLoginRequest(String name, String pass) {
//        final User u = findUserById(name);
//        return u != null && u.checkPassword(pass);
//    }

}


