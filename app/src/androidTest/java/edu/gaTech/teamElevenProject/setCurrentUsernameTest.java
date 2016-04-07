package edu.gaTech.teamElevenProject;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import java.util.ArrayList;

/**
 * Created by Ben on 4/4/16.
 */
public class setCurrentUsernameTest extends AndroidTestCase{

    private DatabaseWrapper db;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        db = new DatabaseWrapper(context, DatabaseWrapper.DATABASENAME);
    }

    @Override
    public void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }

    public void testSetCurrentUsername() throws Exception {
        setUp();
        String name = "user";
        String pass = "pass";
        String name2 = "user2";
        String pass2 = "pass2";

        SQLiteDatabase rdb = db.getReadableDatabase();
        UserManager userManager = new UserManager(db, rdb);
        userManager.addUser("user", "pass");
        userManager.addUser("user2", "pass2");
        User wrongtest = userManager.findUserById("user2");
        User test = userManager.findUserById("user");
        userManager.setCurrentUsername(test);
        //Tests if set current username correctly sets username
        assertEquals(userManager.getCurrentUsername().getName(), test.getName());
        assertNotSame(userManager.getCurrentUsername().getName(), wrongtest.getName());
        User test2 = new User("user3", "pass3");
        userManager.setCurrentUsername(test2);
        //Tests if set current username does not change username if user doesnt
        // exists in the database
        assertNotSame(userManager.getCurrentUsername().getName(), test2.getName());
        assertEquals(userManager.getCurrentUsername().getName(), test.getName());
        tearDown();

    }
}
