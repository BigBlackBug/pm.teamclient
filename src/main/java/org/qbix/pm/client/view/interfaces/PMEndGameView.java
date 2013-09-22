package org.qbix.pm.client.view.interfaces;

import org.qbix.pm.client.model.lol.EndOfGameDTO;

public interface PMEndGameView extends View{

	void fill(EndOfGameDTO endOfGameDTO);
	
}
