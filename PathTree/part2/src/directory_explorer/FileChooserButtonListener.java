package directory_explorer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;



/**
 * The listener for the button to choose a directory. This is where most of the
 * work is done.
 */
public class FileChooserButtonListener implements ActionListener {

	/** The window the button is in. */

	private JFrame directoryFrame;
	/** The label for the full path to the chosen directory. */
	private JLabel directoryLabel;
	/** The file chooser to use when the user clicks. */
	private JFileChooser fileChooser;
	/** The area to use to display the nested directory contents. */
	private JTextArea textArea;

	/**
	 * An action listener for window dirFrame, displaying a file path on
	 * dirLabel, using fileChooser to choose a file.
	 *
	 * @param dirFrame
	 *            the main window
	 * @param dirLabel
	 *            the label for the directory path
	 * @param fileChooser
	 *            the file chooser to use
	 */
	public FileChooserButtonListener(JFrame dirFrame, JLabel dirLabel, JTextArea textArea, JFileChooser fileChooser) {
		this.directoryFrame = dirFrame;
		this.directoryLabel = dirLabel;
		this.textArea = textArea;
		this.fileChooser = fileChooser;
	}

	/**
	 * Handle the user clicking on the open button.
	 *
	 * @param e
	 *            the event object
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		int returnVal = fileChooser.showOpenDialog(directoryFrame.getContentPane());

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			if (file.exists()) {
				directoryLabel.setText("Selected File" + file.getAbsolutePath());
				// Display a temporary message while the directory is
				// traversed.
				this.textArea.setText("Building file tree …");

				// Make the root.
				FileNode fileTree = new FileNode(file.getName(), null, FileType.DIRECTORY);
				FileChooserButtonListener.buildTree(file, fileTree);

				// Build the string representation and put it into the text
				// area.
				StringBuffer contents = new StringBuffer();
				buildDirectoryContents(fileTree, contents, "");
				this.textArea.setText(contents.toString());
			}
		} else {
			directoryLabel.setText("No Path Selected");
		}
	}

	/**
	 * Build the tree of nodes rooted at file in the file system; note curr is
	 * the FileNode corresponding to file, so this only adds nodes for children
	 * of file to the tree. Precondition: file represents a directory.
	 * 
	 * @param file
	 *            the file or directory we are building
	 * @param curr
	 *            the node representing file
	 */
	private static void buildTree(File file, FileNode curr) {
		// TODO: complete this method.
		
		// store all files and directories inside the directory into a new list.
		File [] contents = file.listFiles();
		// loop over each element inside the list.
		for (File content:contents){
			// get fileName of this element.
			String fileName = content.getName();
			// if current element is a file. 
			if(content.isFile()){
				// converting this file to FileNode, FileType is FILE.
				FileNode childNodeF = new FileNode(fileName, curr, FileType.FILE);
				// add this FileNode into the list of children of curr FileNode.
				curr.addChild(fileName, childNodeF);}
			// if current element is a directory.
			if(content.isDirectory()){
				// converting this directory to FileNode, FileType is DIRECTORY.
				FileNode childNodeD = new FileNode(fileName, curr, FileType.DIRECTORY);
				// add this FileNode into the list of children of curr FileNode. 
				curr.addChild(fileName, childNodeD);
				// do the recursion on it since it represents a directory. 
				buildTree(content, childNodeD); 
			}	
		}
		} 
	/**
	 * Build a string buffer representation of the contents of the tree rooted
	 * at n, prepending each file name with prefix, and adding and additional
	 * DirectoryExplorer.PREFIX for subdirectory contents.
	 *
	 * @param fileNode
	 *            the root of the subtree
	 * @param contents
	 *            the string to display
	 * @param prefix
	 *            the prefix to prepend
	 */
	
	private static void buildDirectoryContents(FileNode fileNode, StringBuffer contents, String prefix) {
		
		contents.append(prefix);
		contents.append(fileNode.getName());
		contents.append("\n");
		
		// declare a new prefix that keeps increasing accompany with recursion.
		String prefixN;
		
		// loop over all the childNode of fileNode 
		for(FileNode child : fileNode.getChildren()){
			
			// increasing the number of prefix "--" with recursion going on. "updating the prefix"
			prefixN = prefix.concat(DirectoryExplorer.PREFIX);
			
			// do the recursion step with all childNode of fileNode.
			buildDirectoryContents(child, contents, prefixN);		
		}		
		}
			
			}
		// TODO: complete this method.
	
