/**************************************************************************
 * Assignment : A09- Team Project
 * Team Members: Mohamad Sheikhani, Ronald Trent Bennett, Sreedha Madhavankutty
 * 
 * 
 * 
 *************************************************************************/
package msbBank;

import java.io.File;

public interface OnFoundFolder {

	void onFoundFolder(File acctDetailsFile,File userDir,File accountFile,double accountBalance) throws Exception;
	
}