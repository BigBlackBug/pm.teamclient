package org.qbix.pm.client.controller.interfaces;

import org.qbix.pm.client.misc.exceptions.InvalidDTOFormatException;
import org.qbix.pm.client.model.EndOfGameDTO;
import org.qbix.pm.client.model.GameDTO;

public interface StartupClientController {

	public void handleEndOfGameDTO(EndOfGameDTO endOfGameDTO)
			throws InvalidDTOFormatException;

	public void handleGameDTO(GameDTO gameDTO) throws InvalidDTOFormatException;

}
