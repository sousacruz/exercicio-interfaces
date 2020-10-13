package com.github.sousacruz;

import java.io.FileNotFoundException;
import java.util.Optional;

import com.github.sousacruz.domain.Card;
import com.github.sousacruz.domain.Hero;
import com.github.sousacruz.exception.UnsupportedDisplacement;
import com.github.sousacruz.util.Adventures;

public class HeroAdventure {

	private static String cardFile;
	private static String adventureFile;

	public static void main(String[] args) throws IllegalArgumentException {

		if (args.length != 2) {
			throw new IllegalArgumentException("Wrong numbers of arguments. Expected two file names.");
		}

		try {
			cardFile = args[0];
			adventureFile = args[1];
			Card card = new Card(cardFile);
			Hero hero = new Hero(card, Adventures.loadAventures(adventureFile));
			String result;

			while (hero.hasAdventure()) {
				result = hero.faceNextAdventure();
				if (Optional.of(result).isPresent()) {
					System.out.println(result);
				}
			}

		} catch (FileNotFoundException | UnsupportedDisplacement e) {
			e.printStackTrace();
		} finally {
			// TODO At this moment there is no specification about writing exceptions on
			// stdout.
		}

	}

}
