package sebastian.devmonkey.capstoneproject.other;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "capstone.db";
    private static final String DB_TABLE = "tbl_journal";
    private static final String DB_TABLE_BOOKMARKS = "tbl_bookmaks";

    //columns

    private static final String ID = "ID";
    private static final String TITLE = "TITLE";
    private static final String CONTENT = "CONTENT";


    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT, CONTENT TEXT)";


    private static final String CREATE_TABLE_BOOKMARKS = "CREATE TABLE " + DB_TABLE_BOOKMARKS + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT, TITLEID TEXT, LEVEL TEXT)";



    public DatabaseHelper (Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE_BOOKMARKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_BOOKMARKS);

        onCreate(sqLiteDatabase);
    }


    //create method to insert data
    public boolean insertData(String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, title);
        contentValues.put(CONTENT, content);

        long result = db.insert(DB_TABLE, null, contentValues);

        return result != -1; //if result = -1 data doesn't insert
    }

    //bookmarks
    public boolean insertDataBookmarks(String title, String titleid, String level) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, title);
        contentValues.put("TITLEID", titleid);
        contentValues.put("LEVEL", level);

        long result = db.insert(DB_TABLE_BOOKMARKS, null, contentValues);

        return result != -1; //if result = -1 data doesn't insert
    }


    //create method to view data
    public Cursor viewData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT *  FROM " + DB_TABLE;

        return db.rawQuery(query, null);

    }

    public Cursor viewDataBookmarks() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT *  FROM " + DB_TABLE_BOOKMARKS;

        return db.rawQuery(query, null);

    }


    public boolean updateData(String id ,String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(TITLE, title);
        contentValues.put(CONTENT, content);
        db.update(DB_TABLE, contentValues, "ID = ?",new String[] { id });
        return true;
    }


//    public boolean updateDataBookmarks(String id ,String title, String content) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(ID, id);
//        contentValues.put(TITLE, title);
//        contentValues.put(CONTENT, content);
//        db.update(DB_TABLE, contentValues, "ID = ?",new String[] { id });
//        return true;
//    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DB_TABLE, "ID = ?",new String[] {id});
    }

    public Integer deleteDataBookmarks(String titleid) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DB_TABLE_BOOKMARKS, "TITLEID = ?",new String[] {titleid});
    }


    public boolean hasObject(String titleid) {
        SQLiteDatabase db = getWritableDatabase();
        String selectString = "SELECT * FROM " + DB_TABLE_BOOKMARKS + " WHERE TITLEID " + " = ?";

        // Add the String you are searching by here.
        // Put it in an array to avoid an unrecognized token error
        Cursor cursor = db.rawQuery(selectString, new String[] {titleid});

        boolean hasObject = false;
        if(cursor.moveToFirst()){
            hasObject = true;

            //region if you had multiple records to check for, use this region.

            int count = 0;
            while(cursor.moveToNext()){
                count++;
            }
            //here, count is records found
            Log.d("Result", String.format("%d records found", count));

            //endregion

        }

        cursor.close();          // Dont forget to close your cursor
        db.close();              //AND your Database!
        return hasObject;
    }
}
