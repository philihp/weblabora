package com.philihp.weblabora.model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.philihp.weblabora.model.building.Erection;
import com.philihp.weblabora.model.building.Settlement;

public class Scorecard {
	public class SettlementScore {
		private Settlement settlement;
		private int score;

		public Settlement getSettlement() {
			return settlement;
		}

		public int getScore() {
			return score;
		}

		public SettlementScore(Settlement settlement) {
			this.settlement = settlement;
			Coordinate c = settlement.findLocation().getCoordinate();
			this.score = settlement.getSettlementValue();
			this.score += getSettlementScoreAt(c.north());
			this.score += getSettlementScoreAt(c.south());
			this.score += getSettlementScoreAt(c.east());
			this.score += getSettlementScoreAt(c.west());
		}
		
		private int getSettlementScoreAt(Coordinate c) {
			Terrain spot = settlement.findLocation().getLandscape().getTerrainAt(c);
			if(spot == null) return 0;
			
			//handle mountain offsets
			if(spot.getTerrainType() == TerrainTypeEnum.MERGED_NORTH)
				spot = settlement.findLocation().getLandscape().getTerrainAt(c.north());

			Erection erection = spot.getErection();
			if(erection != null) {
				return erection.getSettlementValue();
			}
			else if(spot.getTerrainType() == TerrainTypeEnum.WATER) {
				return 3;
			}
			else {
				return 0;
			}
		}
	}

	public class PlayerScore {
		private List<SettlementScore> settlementScores = new ArrayList<SettlementScore>();
		private int settlementTotalScore = 0;
		private int shieldScore = 0;
		private int itemScore = 0;

		public PlayerScore(Player player) {
			for (Settlement settlement : player.getLandscape().getSettlements()) {
				SettlementScore score = new SettlementScore(settlement);
				settlementScores.add(score);
				settlementTotalScore += score.getScore();
			}
			for (Erection erection : player.getLandscape().getErections()) {
				shieldScore += erection.getShieldValue();
			}
			itemScore = player.getNickel() * 2
					+ player.getBook() * 2 + player.getPottery() * 3
					+ player.getOrnament() * 4 + player.getReliquary() * 8
					+ player.getWine() 
					+ player.getWonders().size() * 30;
		}

		public List<SettlementScore> getSettlementScores() {
			return settlementScores;
		}
		
		public int getSettlementTotalScore() {
			return settlementTotalScore;
		}

		public int getShieldScore() {
			return shieldScore;
		}

		public int getItemScore() {
			return itemScore;
		}
		
		public int getScore() {
			return getSettlementTotalScore() + getShieldScore() + getItemScore();
		}
	}

	private Map<Color, PlayerScore> scores = new EnumMap<Color, PlayerScore>(
			Color.class);

	public Scorecard(Board board) {
		for (Player player : board.getPlayers()) {
			scores.put(player.getColor(), new PlayerScore(player));
		}
	}
	
	public Map<Color, PlayerScore> getScores() {
		return scores;
	}
}
