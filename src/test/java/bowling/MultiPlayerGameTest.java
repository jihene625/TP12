package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MultiPlayerGameTest {

	private PartieMultiJoueurs partie;
	private String[] nomsDesJoueurs;

	@BeforeEach
	public void setUp(){
		partie = new PartieMultiJoueurs();
	}
	@Test
	void testMultiPlayerGame() {
		assertEquals("Prochain tir: joueur Pierre, tour n° 1, boule n° 1", partie.demarreNouvellePartie(new String[]{"Pierre", "Paul"}), "tour/joueur/lancer incorrect!!");
		assertEquals("Prochain tir: joueur Pierre, tour n° 1, boule n° 2", partie.enregistreLancer(5), "tour/joueur/lancer incorrect!!");
		assertEquals("Prochain tir: joueur Paul, tour n° 1, boule n° 1", partie.enregistreLancer(3), "tour/joueur/lancer incorrect!!");
		assertEquals("Prochain tir: joueur Pierre, tour n° 2, boule n° 1", partie.enregistreLancer(10), "tour/joueur/lancer incorrect!!");
		assertEquals("Prochain tir: joueur Pierre, tour n° 2, boule n° 2", partie.enregistreLancer(7), "tour/joueur/lancer incorrect!!");
		assertEquals("Prochain tir: joueur Paul, tour n° 2, boule n° 1", partie.enregistreLancer(3), "tour/joueur/lancer incorrect!!");

		assertEquals(18, partie.scorePour("Pierre"), "Le score n'est pas bon pour ce joueur");
		assertEquals(10, partie.scorePour("Paul"),"Le score n'est pas bon pour ce joueur");

		assertFalse(partie.PartieFini(),"La partie n'est pas encore términée ?!!!");
	}
}