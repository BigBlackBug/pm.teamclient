package org.qbix.pm.client.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.qbix.collection.CollectionUtils;
import org.qbix.collection.returnfilters.ReturnFilter;
import org.qbix.pm.client.misc.enums.MasterServerMethod;
import org.qbix.pm.client.misc.exceptions.RequestFailedException;
import org.qbix.pm.client.model.lol.LoLGameDTO;
import org.qbix.pm.client.model.lol.PlayerParticipant;
import org.qbix.pm.client.model.pm.GameDTO;
import org.qbix.pm.client.model.pm.PlayerEntryDTO;
import org.qbix.pm.client.model.pm.Team;
import org.qbix.pm.client.model.pm.VictoryCriteriaDTO;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class HostRestController extends GeneralRestController {

	private static class ParticipantFilter implements
			ReturnFilter<PlayerParticipant, Long> {

		@Override
		public Long filter(PlayerParticipant entity) {
			return entity.getAccountId();
		}
	}
	
	//TODO handle if a player is not registered in pm
	public void updateGame(long gameID,
			LoLGameDTO lolGameDTO,
			VictoryCriteriaDTO vcDTO,
			BigDecimal stake)
			throws IOException, RequestFailedException {
		GameDTO gameDTO = new GameDTO();
		gameDTO.setID(gameID);
		gameDTO.setStake(stake);
		gameDTO.setVc(vcDTO);

		List<PlayerParticipant> teamOne = lolGameDTO.getTeamOne();
		List<PlayerParticipant> teamTwo = lolGameDTO.getTeamTwo();

		List<Long> lolAccountIDs = new ArrayList<>();
		lolAccountIDs.addAll(CollectionUtils.filterEntities(teamOne,
				new ParticipantFilter()).toList());
		lolAccountIDs.addAll(CollectionUtils.filterEntities(teamTwo,
				new ParticipantFilter()).toList());

		List<PlayerEntryDTO> entries = new ArrayList<>();
		Map<Long, Long> loltopm = getPmAccountIDs(lolAccountIDs);
		entries.addAll(getPlayerEntryDTOList(teamOne, loltopm, Team.TEAM_0));
		entries.addAll(getPlayerEntryDTOList(teamTwo, loltopm, Team.TEAM_1));
		gameDTO.setPlayerEntries(entries);

		sendRequest(MasterServerMethod.UPDATE_GAME, gson.toJson(gameDTO));
	}

	private List<PlayerEntryDTO> getPlayerEntryDTOList(List<PlayerParticipant> participants,
			Map<Long, Long> loltopm, Team team) {
		List<PlayerEntryDTO> playerDTOList = new ArrayList<>();
		for (PlayerParticipant teamOneParticipant : participants) {
			PlayerEntryDTO playerDTO = new PlayerEntryDTO();
			playerDTO.setAccountId(loltopm.get(teamOneParticipant
					.getAccountId()));
			playerDTO.setTeam(team.getCode());
			playerDTOList.add(playerDTO);
		}
		return playerDTOList;
	}
	
	private JsonElement getLolAccountIDs(List<PlayerParticipant> team) {
		List<Long> lolAccountIdList = CollectionUtils.filterEntities(team,
			new ReturnFilter<PlayerParticipant, Long>() {
				@Override
				public Long filter(PlayerParticipant entity) {
					return entity.getAccountId();
				}
			}).toList();
		return gson.toJsonTree(lolAccountIdList,
				new TypeToken<List<Long>>() {
				}.getType());
	}

	public long registerSession(BigDecimal stake,
			long hostPmAccountID) throws IOException, RequestFailedException {
		GameDTO gameDTO = new GameDTO();
		gameDTO.setStake(stake);
		gameDTO.setVc(/*TODO default VC*/null);
		
		PlayerEntryDTO playerEntry = new PlayerEntryDTO();
		playerEntry.setAccountId(hostPmAccountID);
		playerEntry.setTeam(Team.TEAM_0.getCode());
		gameDTO.setPlayerEntries(Arrays.asList(playerEntry));
		gameDTO.setType("LOL");
		
		String sessionID = sendRequest(MasterServerMethod.REGISTER_SESSION,
				gson.toJson(gameDTO));
		return Long.parseLong(sessionID);
	}

}
