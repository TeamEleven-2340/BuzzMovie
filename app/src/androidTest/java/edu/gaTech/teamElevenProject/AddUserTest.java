package edu.gaTech.teamElevenProject;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import java.util.ArrayList;

/**
 * Created by Jeung on 4/4/16.
 */
public class AddUserTest extends AndroidTestCase{

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

    public void testAddUser() throws Exception {
        setUp();
        String name = "name";
        String pass = "pass";
        SQLiteDatabase rdb = db.getReadableDatabase();
        UserManager userManager = new UserManager(db, rdb);
        userManager.addUser("name", "pass");
        ArrayList<User> userList = (ArrayList<User>) userManager.getUserList();
        for (int i = 0; i < userList.size(); i++) {
            User u = userManager.getUserList().get(i);
            if (u.getName().equals("name")) {
                assertEquals(u.getName(), name);
                assertEquals(u.getPassword(), pass);
                System.out.println(name + " exists!");
            }
        }
        tearDown();

    }
}
