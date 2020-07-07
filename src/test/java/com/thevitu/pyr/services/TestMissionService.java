package com.thevitu.pyr.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.thevitu.pyr.entities.Battle;

@SpringBootTest
public class TestMissionService {
	
	@Autowired
	private MissionService missionService;

	@Test
	public void testStartMission() {
		Battle battle = this.missionService.startMission(45l, Arrays.asList(531l, 532l, 533l, 534l, 535l), 2l);
		assertTrue(battle.getEndDate() == null);
		assertTrue(battle.getExp() == 5);
		assertTrue(battle.getExpGained() == 0);
		assertTrue(battle.getHeroes().size() == 5);
		assertTrue(battle.getLastCalcDate() != null);
		assertTrue(battle.getLooses() == 0);
		assertTrue(battle.getLootItems().isEmpty());
		assertTrue(battle.getMonsters().size() == 1);
		assertTrue(battle.getStartDate() != null);
		assertTrue(battle.getUser() != null);
		assertTrue(battle.getWins() == 0);
		this.missionService.finishMission(battle.getId());
	}

	@Test
	public void testBattleReport() {
		Battle battle = this.missionService.startMission(45l, Arrays.asList(531l, 532l, 533l, 534l, 535l), 2l);		
		Battle report = this.missionService.battleReport(battle.getId());
		assertTrue(report.getEndDate() == null);
		assertTrue(report.getLastCalcDate() != report.getStartDate());	
	}

	@Test
	public void testFinishMission() {
		Battle battle = this.missionService.startMission(45l, Arrays.asList(531l, 532l, 533l, 534l, 535l), 2l);
		battle = this.missionService.finishMission(battle.getId());
		assertTrue(battle.getEndDate() != null);
		assertTrue(battle.getExpGained() == 0);
		assertTrue(battle.getLastCalcDate() != null);
		assertTrue(battle.getLooses() == 0);
		assertTrue(battle.getLootItems().isEmpty());
		assertTrue(battle.getWins() == 0);
	}

}
