package edu.gaTech.teamElevenProject;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

/**
 * Created by Jed on 4/4/16.
 */
public class HandleRegisterTest {

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

    public void testHandleRegisterRequest() throws Exception {
        setUp();
        String name = "validUser";
        String pass = "validPass";
        boolean inDatabase = false;
        SQLiteDatabase rdb = db.getReadableDatabase();
        UserManager userManager = new UserManager(db, rdb);

        assertFalse(userManager.handleRegisterRequest(name, pass));
        System.out.println(name + " can register");

        userManager.addUser(name, pass);

        assertTrue(userManager.handleRegisterRequest(name, pass));
        System.out.println(name + " is already a user");
        tearDown();
    }
}
