package emv.filesystem;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import exception.SmartCardException;
import iso7816.ShortFileIdentifier;
import utils.data.ByteUtils;

/**
 * An AEF is a logical file that contains a single, self-contained data structure, such as a single record or a set of related records.
 * It is used to store data related to the cardholder, the card issuer, the transaction, or other relevant information
 * <p>
 * or example, an AEF might contain information about the cardholder's account number, the card issuer's public key for verifying 
 * digital signatures, and the results of a recent transaction.
 * <p>
 * It enables the secure and flexible management of payment card data.  By organizing the data into self-contained and well-defined structures, 
 * the standard provides a clear and secure method for accessing and processing the data within the smart card.
 * <p>
 * Here's the decoding of AFL:
 * - First Byte: Short File Identifier
 * - Second Byte: First Record under this SFI
 * - Third Byte: Last Record 
 * - Fourth Byte: Record involved for Offline Data Authentication 
 */
public class ApplicationElementaryFile {
	
	ShortFileIdentifier shortFileIdentifier;
	private int startRecordNumber;
    private int endRecordNumber;
	private int recordsInvolvedInOfflineAuth;
	
	private Map<Integer, Record> records = new LinkedHashMap<Integer, Record>(); 
	
	public ApplicationElementaryFile(byte[] data) {
		
		if (data.length != 4) {
			throw new SmartCardException("AEF length must be equal to 4.");
		}
		
		shortFileIdentifier = new ShortFileIdentifier(data[0] >>> 3);
		startRecordNumber = ByteUtils.toUnsignedInt(data[1]);
		endRecordNumber = ByteUtils.toUnsignedInt(data[2]);
		recordsInvolvedInOfflineAuth  = ByteUtils.toUnsignedInt(data[2]);
		
		if (startRecordNumber == 0) {
			throw new SmartCardException("AEF start record number cannot be 0");
		} else if (startRecordNumber > endRecordNumber) {
			throw new SmartCardException("AEF start record number is higher than end record number");
		}
	}

	/* GETTERS and SETTERS */
	
	public void setRecord(int pos, Record record) {
		if (records.containsKey(pos)) {
			throw new IllegalArgumentException("Invalid argument \"pos\". Record number " + pos + " already added");
		}
		
		records.put(pos, record);
	}
	
	public Record getRecord(int pos) {
		return records.get(pos);
	}
	
	public Collection<Record> getRecords() {
		return Collections.unmodifiableCollection(records.values());
	}
	
	public ShortFileIdentifier getShortFileIdentifier() {
		return shortFileIdentifier;
	}

	public int getStartRecordNumber() {
		return startRecordNumber;
	}

	public int getEndRecordNumber() {
		return endRecordNumber;
	}

	public int getRecordsInvolvedInOfflineAuthSize() {
		return recordsInvolvedInOfflineAuth;
	}
}
