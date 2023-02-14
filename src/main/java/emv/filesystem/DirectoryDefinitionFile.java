package emv.filesystem;

import iso7816.ShortFileIdentifier;

/**
 * The DDF is a file that provides a mapping of logical file names to the corresponding physical file names within the 
 * smart card. It provides a way for the card reader to locate and access the various files stored within the smart card, 
 * including AEFs and Binary Files.
 * <p>
 * For example, when the card reader needs to access the cardholder's account information, it can use the DDF to locate 
 * the appropriate AEF and retrieve the necessary data.
 */
public class DirectoryDefinitionFile {
	private byte[] name;
	private ShortFileIdentifier shortFileIdentifier;
}
