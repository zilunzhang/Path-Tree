package directory_reader;

import java.io.File;
import javax.swing.JFileChooser;

/**
 * Select, read, and print the contents of a directory.
 */
public class DirectoryReader {

	/**
	 * Select a directory, then print the full path to the directory and its
	 * contents, one per line. Prefix the contents with a hyphen and a space.
	 *
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {	

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fileChooser.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			System.out.println(file);

			
		/* for the directory that was chosen,
		 * create an array(named "subFileanddir") of abstract pathnames denoting the files in the directory
		 * denoted by this abstract pathname. */
		File[] subFileanddir = file.listFiles();  
		// loop this array of abstract pathnames
		for (int i = 0; i < subFileanddir.length; i++) 
			// if the file denoted by this abstract pathname is a directory:
			if (subFileanddir[i].isDirectory())
				/* print the name of the directory denoted by this abstract pathname, 
				 * prefix the contents with a hyphen,
				 * a space and add '/' at the end of directory's name. */
				System.out.println("- " + subFileanddir[i].getName() + "/"); 
			// if the file denoted by this abstract pathname is not a directory:
			else
				/* print the name of the file denoted by this abstract pathname, 
				 * prefix the contents with a hyphen and a space. */
				System.out.println("- " + subFileanddir[i].getName()); 

		}
	}
}
