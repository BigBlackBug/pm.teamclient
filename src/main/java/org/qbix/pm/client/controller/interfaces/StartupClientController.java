package org.qbix.pm.client.controller.interfaces;

import org.qbix.pm.client.misc.exceptions.InvalidDTOFormatException;
import org.qbix.pm.client.model.lol.EndOfGameDTO;
import org.qbix.pm.client.model.lol.LoLGameDTO;

public interface StartupClientController {

	public void handleEndOfGameDTO(EndOfGameDTO endOfGameDTO)
			throws InvalidDTOFormatException;

	public void handleLoLGameDTO(LoLGameDTO gameDTO) throws InvalidDTOFormatException;

}
