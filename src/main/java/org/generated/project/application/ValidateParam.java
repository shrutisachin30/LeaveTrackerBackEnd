package org.generated.project.application;

/**
 * <h2>Validate Param</h2>
 * <p>
 * This program implements validation
 * </p>
 * 
 * @author shruti karde
 * @since 2021-09-01
 */

public class ValidateParam {

	public static boolean isNull(String val) {
		if (val == null || val.trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
