package edu.gaTech.teamElevenProject;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

/**
 * Created by Jed on 4/4/16.
 */
public class HandleRegisterTest extends AndroidTestCase {

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

    public void testHandleRegisterRequest() throws Exception {
        setUp();
        String userNotRegistered = "name1";
        String userRegistered = "name2";
        String passNR = "pass1";
        String passR = "pass2";
        SQLiteDatabase rdb = db.getReadableDatabase();
        UserManager userManager = new UserManager(db, rdb);
        userManager.addUser(userRegistered, passR);

        //checks to make sure that a user not in the system can register
        assertFalse(userManager.handleRegisterRequest(userNotRegistered, passNR));
        System.out.println(userNotRegistered + " can register");

        //checks to make sure that someone cannot register with a username that is already taken
        assertTrue(userManager.handleRegisterRequest(userRegistered, passR));
        System.out.println(userRegistered + " is already a user");
        tearDown();
    }
}
