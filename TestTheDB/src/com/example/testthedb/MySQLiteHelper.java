package com.example.testthedb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	
		// For parsing
		private static final String FILE = "/TestCSVintoDB/assets/NHPC_PHNC_TB.csv";
		private final static String TAG = "ReadCVS";
		private final static String NULLV = "<Null>";

		 // Table
		public static final String TABLE_POI = "poi";
		  public static final String COLUMN_ID = "_id";
		  public static final String COLUMN_NAME = "name";

		  // Database
		  private static final String DATABASE_NAME = "poi.db";
		  private static final int DATABASE_VERSION = 1;

		  // Database creation sql statement
		  private static final String DATABASE_CREATE = "create table "
		      + TABLE_POI + "(" + COLUMN_ID
		      + " integer primary key autoincrement, " + COLUMN_NAME
		      + " text not null);";

		  public MySQLiteHelper(Context context) {
		    super(context, DATABASE_NAME, null, DATABASE_VERSION);
		  }

		  @Override
		  public void onCreate(SQLiteDatabase database) {
		    database.execSQL(DATABASE_CREATE);
			
		    // For reading the file
			String csvFile = FILE;
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
		    
			// Parse csv into database
			try {
				 
				br = new BufferedReader(new FileReader(csvFile));
				Log.i(TAG, "Opened file");
				
				while ((line = br.readLine()) != null) {
		 
				    // use comma as separator
					String[] poiLine = line.split(cvsSplitBy);
					
					
					
					POI poi = new POI();
					poi.setSiteID(Integer.parseInt(poiLine[0]));
					poi.setName(poiLine[1]);
//					if (!NULLV.equals(poiLine[2]))
//						poi.setStreet(poiLine[2]);
//					if (!NULLV.equals(poiLine[3]))
//						poi.setPlaqueLocation(poiLine[3]);
//					poi.setTown(poiLine[4]);
//					poi.setProvince(poiLine[5]);
//					poi.setDesignation(poiLine[6]);
//					poi.setLatitude(Double.parseDouble(poiLine[7]));
//					poi.setLongitude(Double.parseDouble(poiLine[8]));
					
					addPOI(poi);
					
					Log.i(TAG, "added " + poi.getName());
				}
		 
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		  }

		  @Override
		  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		    Log.w(MySQLiteHelper.class.getName(),
		        "Upgrading database from version " + oldVersion + " to "
		            + newVersion + ", which will destroy all old data");
		    db.execSQL("DROP TABLE IF EXISTS " + TABLE_POI);
		    onCreate(db);
		  }
		  
		// Adding new POI
		  public void addPOI(POI poi) {
			    SQLiteDatabase db = this.getWritableDatabase();
			    
			    ContentValues values = new ContentValues();
			    values.put(COLUMN_ID, poi.getSiteID());
			    values.put(COLUMN_NAME, poi.getName());

			 
			    // Inserting Row
			    db.insert(TABLE_POI, null, values);
			    db.close(); // Closing database connection
		  }
		   
		  // Getting single poi
		  public POI getPOI(int id) {
			    SQLiteDatabase db = this.getReadableDatabase();
			    
			    Cursor cursor = db.query(TABLE_POI, new String[] { COLUMN_ID,
			            COLUMN_NAME}, COLUMN_ID + "=?",
			            new String[] { String.valueOf(id) }, null, null, null, null);
			    if (cursor != null)
			        cursor.moveToFirst();
			 
			    POI poi = new POI();
			    poi.setSiteID(Integer.parseInt(cursor.getString(0)));
			    poi.setName(cursor.getString(1));
			    // return poi
			    return poi;

			}
		   
		  // Getting All Contacts
		  public List<POI> getAllPOI() {
			    List<POI> contactList = new ArrayList<POI>();
			    // Select All Query
			    String selectQuery = "SELECT  * FROM " + TABLE_POI;
			 
			    SQLiteDatabase db = this.getWritableDatabase();
			    Cursor cursor = db.rawQuery(selectQuery, null);
			 
			    // looping through all rows and adding to list
			    if (cursor.moveToFirst()) {
			        do {
			            POI poi = new POI();
			            poi.setSiteID(Integer.parseInt(cursor.getString(0)));
			            poi.setName(cursor.getString(1));
			            // Adding poi to list
			            contactList.add(poi);
			        } while (cursor.moveToNext());
			    }
			 
			    // return contact list
			    return contactList;
			}
		   
		  // Getting contacts Count
		  public int getPOICount() {
		        SQLiteDatabase db = this.getReadableDatabase();
		        String countQuery = "SELECT  * FROM " + TABLE_POI;
		        Cursor cursor = db.rawQuery(countQuery, null);
		        int count = cursor.getCount();
		        cursor.close();
		        db.close();
		        // return count
		        return count;
			}
		 
		  // Updating single contact
		  public int updateContact(POI poi) {
			    SQLiteDatabase db = this.getWritableDatabase();
			    
			    ContentValues values = new ContentValues();
			    values.put(COLUMN_NAME, poi.getName());

			 
			    // updating row
			    return db.update(TABLE_POI, values, COLUMN_ID + " = ?",
			            new String[] { String.valueOf(poi.getSiteID()) });
		  }
		   
		  // Deleting single contact
		  public void deleteContact(POI poi) {
			    SQLiteDatabase db = this.getWritableDatabase();
			    db.delete(TABLE_POI, COLUMN_ID + " = ?",
			            new String[] { String.valueOf(poi.getSiteID()) });
			    db.close();
		  }

			public ArrayList<String> getEntry(String sqlArg) {
				// Create the string to be returned
				ArrayList<String> nameList = new ArrayList<String>();
				// Make an SQL statement to be used
				String selectQuery = "SELECT  " + sqlArg + " FROM " + TABLE_POI;


				// Create a database to use
				SQLiteDatabase database = this.getReadableDatabase();
				// Create a cursor (in the database)
				// rawQuery is used to give SQL and returns a cursor (position with data)
				Cursor cursor = database.rawQuery(selectQuery, null);
				// Loop to get each item. Goes to next after every pass
				if (cursor.moveToFirst()) {
					do {
						nameList.add(cursor.getString(0));
					} while (cursor.moveToNext());
				}
				database.close();
				return nameList;
			}
			
			public ArrayList<String> getEntryFromNameContains(String sqlArg, String contains) {
				// Create the string to be returned
				ArrayList<String> nameList = new ArrayList<String>();
				
				// Make an SQL statement to be used
				String selectQuery = "SELECT  " + sqlArg + " FROM " + TABLE_POI + 
						" where name LIKE '%" + contains + "%'";
				
				// Create a database to use
				SQLiteDatabase database = this.getReadableDatabase();
				
				// Create a cursor (in the database)
				// rawQuery is used to give SQL and returns a cursor (position with data)
				Cursor cursor = database.rawQuery(selectQuery, null);
				
				// Loop to get each item. Goes to next after every pass
				if (cursor.moveToFirst()) {
					do {
						nameList.add(cursor.getString(0));
					} while (cursor.moveToNext());
				}
				database.close();
				return nameList;
			}
	

}
