package photomanager;

import java.io.*;
import java.util.*;

/**
 * The PhotoManager class keeps track of Photos by using an ArrayList of Photo
 * references. The class relies heavily on ArrayList methods. At least you will
 * be using the following ArrayList methods: add, get, remove, clear. Check the
 * Java API ArrayList entry for information about each of these methods.
 * 
 * @author UMCP CS Department
 *
 */
public class PhotoManager {
	private ArrayList<Photo> allPhotos;

	/**
	 * Assigns to the allPhotos instance variable an ArrayList of Photos.
	 */
	public PhotoManager() {
		allPhotos = new ArrayList<Photo>();
	}

	/**
	 * Creates a Photo based on the provided parameters and adds the photo to the
	 * allPhotos ArrayList. The photo will be added if it does not already exist in
	 * the ArrayList (hint: use the findPhoto method). The method must handle
	 * (try/catch block) any exception thrown by creating a Photo (remember that a
	 * Photo constructor can throw an IllegalArgumentException). If an exception is
	 * thrown by the Photo constructor, the photo will not be added; in addition the
	 * message "addPhoto: Invalid arguments" will be printed to standard error (that
	 * means using System.err.println (notice the err)) and false will be returned.
	 * If a photo is added the method will return true.
	 * 
	 * @param photoSource Photo's url or filename.
	 * @param width       Photo's width in pixels.
	 * @param height      Photo's height in pixels.
	 * @param date        Date the photo was taken.
	 * @return true if photo added; false otherwise.
	 */
	public boolean addPhoto(String photoSource, int width, int height, String date) {
		// Returns false if photo already exists according to findPhoto
		if (findPhoto(photoSource) >= 0) {
			return false;
		}
		
		// Attempts to add new photo to ArrayList and return true
		try {
			allPhotos.add(new Photo(photoSource, width, height, date));
			return true;
		}
		// If failed proper message is displayed and method returns false
		catch (IllegalArgumentException e) {
			System.err.println("addPhoto: Invalid arguments");
			return false;
		}
		
		
	}

	/**
	 * Returns a string where each Photo is printed on a line by itself.
	 * 
	 * @return String with information about all photos.
	 */
	public String toString() {
		// Creating StringBuffer for return of all photo toString()s
		StringBuffer photos = new StringBuffer();
		
		/* For each loop appends the toString of each photo to the StringBuffer 
		   with a newline so each photo toString is on a separate line */
		for (Photo photo : allPhotos) {
			photos.append(photo.toString());
			photos.append("\n");
		}
		
		// StringBuffer return
		return photos.toString();
	}

	/**
	 * Returns the index in the ArrayList associated with the Photo that
	 * has a photoSource corresponding to the parameter. The method will
	 * return -1 if no photo is found or if the parameter is null.
	 * 
	 * @param photoSource Photo's photoSource.
	 * @return Index in the array or -1 (photo not found).
	 */
	public int findPhoto(String photoSource) {
		// Returns -1 for invalid photoSource
		if (photoSource == null) {
			return -1;
		}
		/* Iterates through ArrayList and checks to see if the photo object's
		   photoSource is the same as the one provided*/
		int i = 0;
		for (Photo photo : allPhotos) {
			// Returns index of object if photoSources are the same
			if (photo.getPhotoSource().equals(photoSource)) {
				return i;
			}
			i++;
		}
		// Returns -1 if the photoSource is not found
		return -1;
	}

	/**
	 * Adds the specified comment to the photo with the specified photoSource (if
	 * such photo is present in allPhotos). It returns true if the comments are
	 * added and false if the photo could not be found, or if the parameters are
	 * invalid. A parameter is invalid if it is null or if the newComment string is
	 * blank (according to the String method isBlank()).
	 * 
	 * @param photoSource PhotoSource of photo we would like to add the comment.
	 * @param newComment  Comment to add.
	 * @return true if comment added; false otherwise.
	 */
	public boolean addComment(String photoSource, String newComment) {
		// Returns false if any parameters are invalid
		if (photoSource == null || newComment == null || newComment.isBlank()) {
			return false;
		}
		
		// If photoSource is valid, attempts to find photoSource index
		int photoIndex = findPhoto(photoSource);
		
		// If the photo does not exist, returns false
		if (photoIndex == -1) {
			return false;
		}
		
		// Otherwise proceeds to add comment with help of photoIndex variable
		allPhotos.get(photoIndex).addComments(newComment);
		return true;
	}

	/**
	 * Returns the comments of the photo associated with the specified photoSource.
	 * The method will return null if no photo exists with the specified
	 * photoSource, or if the parameter is null.
	 * 
	 * @param photoSource photo to find comments for.
	 * @return Comments or null.
	 */
	public String getComments(String photoSource) {
		// If photoSource is null returns null
		if (photoSource == null) {
			return null;
		}
		
		// If photoSource is valid, attempts to find photoSource index
		int photoIndex = findPhoto(photoSource);
				
		// If the photo does not exist, returns null
		if (photoIndex == -1) {
			return null;
		}
		
		// Otherwise proceeds to return comments from the photo
		return allPhotos.get(photoIndex).getComments();
	}

	/**
	 * Removes all the photos from allPhotos.  This method only takes a single
	 * line of code.
	 */
	public void removeAllPhotos() {
		allPhotos.clear();
	}

	/**
	 * Removes the Photo with the specified photoSource (if it exists). Returns true
	 * if the photo was removed and false if the photo was not found or the
	 * parameter is null. Remember that you can remove elements from an ArrayList
	 * using the ArrayList remove method.
	 * 
	 * @param photoSource Photo we would like to remove.
	 * @return true if photo was removed; false otherwise.
	 */
	public boolean removePhoto(String photoSource) {
		// Returns false if any parameters are invalid
		if (photoSource == null) {
			return false;
		}
				
		// If photoSource is valid, attempts to find photoSource index
		int photoIndex = findPhoto(photoSource);
				
		// If the photo does not exist, returns false
		if (photoIndex == -1) {
			return false;
		}
				
		// Otherwise proceeds to remove photo
		allPhotos.remove(photoIndex);
		return true;
	}

	/**
	 * Loads the photos specified in filename to the allPhotos
	 * ArrayList. It adds to the ArrayList (it does not clear the ArrayList before
	 * adding photos). Each line of the file will have information about a photo.
	 * The information will be the photoSource, followed by the width, height and
	 * date. You can assume the file will have correct information. The following is
	 * an examples of a file entry:
	 * <br>
	 * umcp/college1.jpg 300 400 10/18/2020-17:10
	 * <br>
	 * If an error takes place while opening the file (e.g., file does not exist),
	 * an error message (any message is fine) will be printed using 
	 * System.err.println. Notice that your code will not crash when a file 
	 * could not be opened; in this case the method will print the error message 
	 * and return false.  The method will return false and perform no processing
	 * when the parameter is null.
	 * @param filename Name of file with information about photos.
	 * @return true if the data was loaded and false otherwise.
	 * 
	 */
	public boolean loadPhotos(String filename) {
		// Attempts to open file with a BufferedReader and add photos from lines
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			
			// Splits each line in to 4 parts to separate variables/strings
			while ((line = br.readLine()) != null) {
				String[] details = line.split(" ");
				String photoSource = details[0];
				int width = Integer.parseInt(details[1]);
				int height = Integer.parseInt(details[2]);
				String date = details[3];
				
				/* Attempts to add each line to the ArrayList using addPhoto 
				   method. If it fails an error is printed but the rest of the 
				   file will still be processed and added if possible*/
				if (!addPhoto(photoSource, width, height, date)) {
					System.err.println("Error adding photo from: " + line);
				}
			}
			// Returns true after file processing and all possible photos added 
			return true;
		}
		// Catch for if file is unable to opened/read 
		catch (IOException e) {
			System.err.println("Error opening file: " + filename);
			return false;
		}
		// Catch for null filename
		catch(NullPointerException e) {
			System.err.println("Null filenme");
			return false;
		}
	}

	/**
	 * Sorts the allPhotos by date. This method requires a single line of code.
	 */
	public void sortPhotosByDate() {
		Collections.sort(allPhotos);
	}

	/**
	 * This method creates an HTML file with all the photos. This method has
	 * been implemented for you and it is not used by any other method.
	 * 
	 * @param htmlFilename Web page with photos.
	 */
	public void createHTMLPage(String htmlFilename) {
		String body = "";

		for (Photo photo : allPhotos) {
			body += "<img src=\"" + photo.getPhotoSource() + "\" ";
			body += "width=\"" + photo.getWidth() + "\" ";
			body += "height=\"" + photo.getHeight() + "\" ";
			body += "alt=\"photo image\"><br>\n";
		}

		Utilities.generateHTMLPageWithBody(htmlFilename, body);
	}
}